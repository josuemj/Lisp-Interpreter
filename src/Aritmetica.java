import java.util.Stack;

public class Aritmetica {

    /**
     * Metodo identifica si es una operacion
     * @param operator
     * @return
     */

    public static boolean isOperator(String operator){
        if (operator.equals("+")  || operator.equals("*") || operator.equals("/") || operator.equals("-")){
            return true;
        }
        return false;
    }

    /**
     * Evalua la expression prefix la cual usa la sintaxis de lisp
     * @param PrefixExpression
     * @return resuklt int
     */

    public static int evaluatePrefix(String PrefixExpression){
        //System.out.println("EVALUATING THE PREFIX EXPRESSION:"+PrefixExpression);
        String[] tokens = PrefixExpression.split("\\s+");

        //Stack for operands
        Stack<Integer> stack = new Stack<>();

        // Iterate through the tokens from right to left
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            //System.out.println(token);

            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            }

            else {
                int a = stack.pop();
                int b = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token);
                }
            }
        }
        return stack.pop();
        //Result
    }
}
