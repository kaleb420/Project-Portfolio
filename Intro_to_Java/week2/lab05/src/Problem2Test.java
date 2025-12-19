import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void isNestedParenthesesTR() {
        assertEquals(true, Problem2.isNestedParenthesesTR("(())"));
        assertEquals(true, Problem2.isNestedParenthesesTR("((()))"));
        assertEquals(false, Problem2.isNestedParenthesesTR("))))(((("));
        assertEquals(true, Problem2.isNestedParenthesesTR(""));
        assertEquals(false, Problem2.isNestedParenthesesTR("(()))"));
        assertEquals(false, Problem2.isNestedParenthesesTR("(((()))"));
    }
}