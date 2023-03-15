import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class AritmeticaTest {

    @org.junit.jupiter.api.Test
    void isOperator() {

        String addition = "+";
        String noOperatorString  = "I'm not an operator";

        assertTrue(Aritmetica.isOperator(addition));
        assertFalse(Aritmetica.isOperator(noOperatorString));

    }

    @org.junit.jupiter.api.Test
    void evaluatePrefix() {

        String prefixExpression = "* 5 6";
        int result  = 30;
        int eval = Aritmetica.evaluatePrefix(prefixExpression);
        assertEquals(result,eval);




    }
}