import java.util.ArrayList;

public class Interpreter {

    /**
     * Lee linea por linea para detectar la funcion o tarea a realizar
     * @param fullCode
     */

    public static void mainDecoder(ArrayList<String> fullCode){

        //Reading line by line
        for (int i = 0;i<fullCode.size();i++){

            //Code line is the line
            String codeLine = fullCode.get(i);
            System.out.println("Line: "+codeLine);

            //Arithmetical line detection
            if(isAritmetical(codeLine)){ //Arithmetical detector
                System.out.println("ARITMETICAL OPERATION DETECTED");
                //Function call to operate de code line
                System.out.println(Aritmetica.evaluatePrefix(codeLine.substring(1,codeLine.length()-1)));
            }

            //Predicate line detection
            if(Predicates.isPredicate(codeLine)){
                System.out.println("Predicate detected");

                Predicates.runPredicate(codeLine);
            }
            System.out.println("-----------------\n");




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
