import java.util.Stack;

public class Aritmetica {

    private static Variables Variables = new Variables();

    /**
     * Metodo identifica si es una operacion
     * @param operator
     * @return
     */
    public static boolean isOperator(String operator){
        if (operator.equals("+")  || 
        operator.equals("*") || operator.equals("/") || operator.equals("-")){
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

            else if (token.startsWith("$")) {
                Object value = Variables.getVariable(token.substring(1));
                if (value == null) {
                    throw new IllegalArgumentException("Variable not defined: " + token);
                }
                stack.push((Integer) value);
            }

            else if (isOperator(token)) {
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

            else if (token.equals("SETQ")) {
                String variableName = tokens[i - 1];
                Object value = stack.pop();
                Variables.setVariable(variableName, value);
                i--; // Skip the variable name in the next iteration
            }

            else if (token.equals("COND")) {
                int cases = Integer.parseInt(tokens[i + 1]);
                int j = i + 2;
                while (cases-- > 0) {
                    String cond = tokens[j++];
                    String value = tokens[j++];
                    if (cond.equals("T") || evaluateConditional(cond) == 1) {
                        stack.push(Integer.parseInt(value));
                        break;
                    }
                }
                i = j - 1; // Skip the cases in the next iteration
            }

            else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: " + PrefixExpression);
        }

        return stack.pop();
    }

    /**
     * Evalúa una expresión condicional en notación prefix
     * @param expression la expresión a evaluar
     * @return el valor resultante de la evaluación de la condición
     * @throws IllegalArgumentException si la expresión no es válida
     */
    public static int evaluateConditional(String expression) {
        String[] tokens = expression.split("\\s+");
        if (tokens.length < 2) {
            throw new IllegalArgumentException("Invalid conditional expression: " + expression);
        }
        String operator = tokens[0];
        if (!isOperator(operator)) {
            throw new IllegalArgumentException("Invalid operator in conditional expression: " + operator);
        }
        int a = Integer.parseInt(tokens[1]);
        int b = Integer.parseInt(tokens[2]);
        switch (operator) {
            case "=":
                return a == b ? 1 : 0;
            case "<":
                return a < b ? 1 : 0;
            case ">":
                return a > b ? 1 : 0;
            default:
                throw new IllegalArgumentException("Invalid operator in conditional expression: " + operator);
        }
    }
}

