import java.util.ArrayList;
import java.util.Stack;

public class Interpreter {

    public static Aritmetica Aritmetica = new Aritmetica();
    /**
     * Lee linea por linea para detectar la funcion o tarea a realizar
     * @param fullCode
     */
    private static Stack<String> stack = new Stack<String>();
    private static ArrayList<String> defunLines = new ArrayList<>();
    private static boolean defunDetected = false;
    public static void mainDecoder(ArrayList<String> fullCode){
    

        //Reading line by line
        for (int i = 0;i<fullCode.size();i++){

            //Code line is the line
            String codeLine = fullCode.get(i);
            System.out.println("Line: "+codeLine);

            //Defun line detection
            if(Defun.isDefun(codeLine) || defunDetected) {
                System.out.println("DEFUN LINE DETECTED");
                defunDetected = true;
                String[] codeChars = codeLine.split("");
                for (String st : codeChars) {
                    if (st.equals("(")){
                        stack.push(st);
                    } else if (st.equals(")")){
                        try {
                            stack.pop();
                        } catch (Exception e) {
                            System.out.println("SYNTAX ERROR INVALID ARGUMENT");
                            defunDetected = false;
                        }
                    }
                }
                if(!stack.isEmpty()){
                    defunLines.add(codeLine);
                } else{
                    defunLines.add(codeLine);
                    Defun.saveDefun(defunLines);
                    defunDetected=false;
                    System.out.println(" --FUNCTION SAVED--");
                    System.out.println("-----------------\n");
                }
            } else{
                //ALREADY DEFINED FUNCTION DETECTED
                if(Defun.isFunction(codeLine)){
                    System.out.println("ALREADY DEFINED FUNCTION DETECTED");
                    if(codeLine.split(" ").length>1){
                        Defun.runFunction(codeLine);
                    } else{
                        Defun.runFunctionNP(codeLine);
                    }
                }
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


                //Empezar el código para cond
                
            }
            //
            //Empizan las vainas de setq
            if(setq.isSetq(codeLine)){ // Setq detector
                System.out.println("SETQ DETECTADO");
                setq.runSetq(codeLine);

            }
            //termina

            //Empiezan las vainas de getVariables
            
            //termina

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
