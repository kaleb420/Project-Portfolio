import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void isNestedParenthesesLoop() {
        assertEquals(true, Problem2.isNestedParenthesesLoop("(())"));
        assertEquals(true, Problem2.isNestedParenthesesLoop("((()))"));
        assertEquals(false, Problem2.isNestedParenthesesLoop("))))(((("));
        assertEquals(true, Problem2.isNestedParenthesesLoop(""));
        assertEquals(false, Problem2.isNestedParenthesesLoop("(()))"));
        assertEquals(false, Problem2.isNestedParenthesesLoop("(((()))"));
    }
}