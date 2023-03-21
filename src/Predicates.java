import java.util.Stack;

public class Predicates {

    Aritmetica Aritmetica = new Aritmetica();
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
            try {
                String[] values = predicate.substring(2, predicate.length() - 1).split(" ");
                double num1 = Double.parseDouble(values[0]);
                double num2 = Double.parseDouble(values[1]);
                if(num1 > num2) {
                    String action = predicate.substring(predicate.indexOf("=>") + 2).trim();
                    System.out.println("Result: " + evaluateExpression(action));
                }
            } catch(Exception e) {
                System.out.println("SYNTAX ERROR");
            }
            //> method
    
        }else if(predicate.contains("<")){
            System.out.println("< DETECTED");//?????????
            try {
                String[] values = predicate.substring(2, predicate.length() - 1).split(" ");
                double num1 = Double.parseDouble(values[0]);
                double num2 = Double.parseDouble(values[1]);
                if(num1 < num2) {
                    String action = predicate.substring(predicate.indexOf("=>") + 2).trim();
                    System.out.println("Result: " + evaluateExpression(action));
                }
            } catch(Exception e) {
                System.out.println("SYNTAX ERROR");
            }
            //< method
        }
    }

    /**
     * Evalua expresion.
     * @param expression
     * @return
     */
    public static String evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        Stack<String> stack = new Stack<>();
    
        for(String token : tokens) {
            if(token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(token);
            } else if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                if(stack.size() < 2) {
                    return "SYNTAX ERROR";
                }
                double num2 = Double.parseDouble(stack.pop());
                double num1 = Double.parseDouble(stack.pop());
                double result;
                switch(token) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if(num2 == 0) {
                            return "DIVISION BY ZERO ERROR";
                        }
                        result = num1 / num2;
                        break;
                    default:
                        return "SYNTAX ERROR";
                }
                stack.push(Double.toString(result));
            } else {
                return "SYNTAX ERROR";
            }
        }
    
        if(stack.size() == 1) {
            return stack.pop();
        } else {
            return "SYNTAX ERROR";
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

    /**
     * Detecta si es una lista.
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

    /**
     * Corre el predicado de igual o "equals".
     * @param codeLine
     */

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

    /**
     * Predicate reading.
     * @param predicate
     * @return
     */

    public static boolean evaluate(String predicate){
        String[] tokens = predicate.split(" ");
        String operator = tokens[0];
        int leftOperand = Integer.parseInt(tokens[1]);
        int rightOperand = Integer.parseInt(tokens[2]);
        
        switch(operator){
            case "<":
                return leftOperand < rightOperand;
            case ">":
                return leftOperand > rightOperand;
            case "=":
                return leftOperand == rightOperand;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }


    /**
     * Cond
     * @param codeLine
     */
    public static void runPredicateCond(String codeLine) {
        Aritmetica Aritmetica = new Aritmetica();
        // Remove the "cond" keyword and split the remaining code by "=>" operator
        String[] cases = codeLine.substring(5).split("=>");
        
        // Iterate over each case
        for (String caseLine : cases) {
            // Split each case into the predicate and the result
            String[] caseParts = caseLine.trim().split(" ");
            
            // Check if the predicate is "else" (which always evaluates to true)
            boolean isElse = caseParts[0].equals("else");
            
            // Evaluate the predicate (if it's not "else")
            boolean result = isElse ? true : Aritmetica.evaluatePrefixCond(caseParts[0]) == 1;

            
            // If the predicate is true, evaluate the result and return it
            if (result) {
                String resultLine = caseParts[1].substring(1, caseParts[1].length() - 1); // Remove the parentheses around the result
                System.out.println(Aritmetica.evaluatePrefixCond(resultLine));
                return;
            }
        }
        
        // If none of the predicates were true, print an error message
        System.out.println("ERROR: No true predicates in cond statement.");
    }


}
