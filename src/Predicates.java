public class Predicates {

    /**
     * Recive la linea y verifica si se trata de un predicado
     * @param codeLine
     * @return
     */
    public static boolean isPredicate(String codeLine){

        if (codeLine.contains("atom") || codeLine.contains("list") || codeLine.contains("equal") || codeLine.contains(">") || codeLine.contains("<")){
            return true;
        }
        return false;

    }

    /**
     * Una vez el interprete a detectado que se trata de un predicado
     * Detecta de cual se trata para luego hacer el retorno de la funcion
     * @param predicate
     */
    public static void runPredicate(String predicate) {

        if (predicate.contains("atom")){

            System.out.println("RUNNING ATOM FUNCTION");
            try{
                String variable = predicate.substring(6,predicate.length()-1);
                System.out.println("VARIABLE ANALYSIS:"+variable);//??????
                isAtom(variable);
            } catch (Exception e){
                System.out.println("SYNTAX ERROR");
            }
            //Run atom method

        }else if(predicate.contains("list")){
            System.out.println("LIST DETETECTED");//?????????
            isList(predicate);
            //List method to run

        } else if (predicate.contains("equal")){
            System.out.println("EQUAL EXPRESSION");//?????????
            runEqual(predicate);
            //Equal method to run

        }else if(predicate.contains(">")){
            System.out.println("> DETECTED");//?????????

            //> method

        }else if(predicate.contains("<")){
            System.out.println("< DETECTED");//?????????

            //< method
        }
    }

    /**
     * In Lisp, an atom is a single data element that represents a value.
     * An atom can be a symbol, a number, a string, a character, or a boolean value.
     * Anything that is not an atom is a list
     *
     * Samples:
     * ==================
     * ;; Boolean values
     * t lisp
     * nil
     * So atom function return T (true) if the given argument is an atom
     * and returns NIL (false) if the argument isa list
     * @param atomPredicate
     * @return
     */
    public static boolean isAtom(String atomPredicate){

        //If it's number
        try{

            double num = Double.parseDouble(atomPredicate);
            System.out.println("Number detected");//?????????
            System.out.println("T");
            return true;

        }catch (Exception num) {
        }

        try{
            //If It's string
            String initialSt = String.valueOf(atomPredicate.charAt(0));
            String finalSt = String.valueOf(atomPredicate.charAt(atomPredicate.length()-1));

            if(initialSt.equals("'") & finalSt.equals("'")){
                System.out.println("String detected");//?????????
                System.out.println("T");
                return true;
            }

        } catch (Exception st) {
        }

        //Boolean values also atom values.
        try{
            if (atomPredicate.equals("T")){
                System.out.println("Boolen detected");//?????????
                System.out.println("T");
                return true;

            }else if(atomPredicate.equals("NIL")) {
                System.out.println("Boolen detected");//?????????
                System.out.println("T");
                return true;
            }
        }catch (Exception b) {
        }

        try{

            //If it's symbol
            String first = String.valueOf(atomPredicate.charAt(0));
            String finals = String.valueOf(atomPredicate.charAt(atomPredicate.length()-1));
            if(first.equals("'") & atomPredicate.length()>=1 & !finals.equals(")")){
                System.out.println("Symbol Detected"); //?????
                System.out.println("T");
                return true;
            }
        }catch (Exception s) {
        }

        //if it's a list
        try {
            System.out.println("CHECKING LIST");//???????

            String comilla = String.valueOf(atomPredicate.charAt(0));
            String list1 = String.valueOf(atomPredicate.charAt(1));
            String list2 = String.valueOf(atomPredicate.charAt(atomPredicate.length()-1));

            if (list2.equals(")") & list1.equals("(") & atomPredicate.length() >= 4 & comilla.equals("'")){
                System.out.println("NIL");
                return false;
            }

        }catch (Exception l){
        }

        System.out.println("SYNTAX ERROR INVALID ARGUMENT");
        return false;
    }

    /**
     * In Lisp, the list function is used to create a new list.
     * It takes any number of arguments and returns a new list
     * containing those arguments.
     * @param listDeclaration
     * @return
     */

    public static boolean isList(String listDeclaration){
        System.out.println("ANALYZING LIST");//???????
        System.out.println(listDeclaration);//????????
        try{
            String postList = listDeclaration.substring(6,listDeclaration.length()-1);
            System.out.println(postList); //???????
            String[] lista = postList.split(" ");

        }catch (Exception li){
            System.out.println("LIST SYNTAX ERROR");
        }


        return false;
    }

    public static void runEqual(String codeLine){
        try{

            String expression = codeLine.substring(7,codeLine.length()-1);
            System.out.println("Expressions: "+expression);

            String[] variables = expression.split(" ");

            String cond1 = variables[0];
            String cond2 = variables[1];

            if (cond2.equals(cond1)){
                System.out.println("T");
            }else {
                System.out.println("NIL");
            }

        }catch (Exception i){
            System.out.println("INVALID COMPARATION GIVEN");
        }
    }


}
