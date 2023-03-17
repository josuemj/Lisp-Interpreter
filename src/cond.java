import java.util.Stack;

public class cond {
    /**
     * Evalúa una expresión condicional en notación prefix
     * @param expression la expresión a evaluar
     * @return el valor resultante de la evaluación de la condición
     * @throws IllegalArgumentException si la expresión no es válida
     */
    public static int evaluateConditional(String expression) {
        String[] tokens = expression.split("\\s+");
        Stack<Integer> stack = new Stack<>();
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            } else if (isOperator(token)) {
                int a = stack.pop();
                int b = stack.pop();
                switch (token) {
                    case "<":
                        stack.push(a < b ? 1 : 0);
                        break;
                    case ">":
                        stack.push(a > b ? 1 : 0);
                        break;
                    case "EQUAL":
                        stack.push(a == b ? 1 : 0);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token);
                }
            } else if (token.equals("COND")) {
                int cases = Integer.parseInt(tokens[i + 1]);
                int j = i + 2;
                while (cases-- > 0) {
                    String cond = tokens[j++];
                    String value = tokens[j++];
                    if (evaluateConditional(cond) == 1) {
                        return Integer.parseInt(value);
                    }
                }
                throw new IllegalArgumentException("No matching case found in COND expression");
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
        return stack.pop();
    }

    /**
     * Identifica si es una operación
     * @param operator el operador a identificar
     * @return true si el operador es válido, false de lo contrario
     */
    private static boolean isOperator(String operator) {
        return operator.equals("<") || operator.equals(">") || operator.equals("EQUAL");
    }
}
