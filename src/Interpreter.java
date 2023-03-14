import java.util.ArrayList;

public class Interpreter {

    public static void mainDecoder(ArrayList<String> fullCode){

        for (int i = 0;i<fullCode.size();i++){

            String codeLine = fullCode.get(i);
            System.out.println("Line: "+codeLine);

            if(isAritmetical(codeLine)){
                System.out.println("ARITMETICAL OPERATION DETECTED");
                //Function call to operate de code line

                System.out.println(Aritmetica.evaluatePrefix(codeLine.substring(1,codeLine.length()-1)));
            }else {
                System.out.println("NO OPERATION DETECTED");
            }


        }

    }

    /**
     *Metodo verifica si se trata de una operacion aritmetica
     *verificando si hay un operador dentro
     * @param codeLine
     * @return
     */

    public static boolean isAritmetical(String codeLine){
        String possibleOp = String.valueOf(codeLine.charAt(1));
        if(Aritmetica.isOperator(possibleOp)){
            return true;
        }
        return false;

    }

}
