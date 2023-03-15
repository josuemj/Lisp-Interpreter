/**
 * Testing (not unit)
 * Testing external and collections methods.
 */

public class CodeTests {
    public static void main(String[] args) {
        String word = "sdsd  dsds  sdsss ";
        System.out.println(word.replace(" ", ""));
        String prefixExpression = "/ 1 5 6";
        int result  = 30;
        int eval = Aritmetica.evaluatePrefix(prefixExpression);
        System.out.println(result);
        System.out.println(eval);

    }
}


