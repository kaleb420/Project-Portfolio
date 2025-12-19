import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Problem8Test {

    @Test
    void postfixEvaluator() {
        assertEquals(17, Problem8.postfixEvaluator(List.of("5", "2", "*", "5", "+", "2", "+")));
        assertEquals(10, Problem8.postfixEvaluator(List.of("1", "2", "3", "4", "+", "+", "+")));
        assertEquals(8, Problem8.postfixEvaluator(List.of("-5", "2", "-", "3", "-5", "*", "-")));
        assertEquals(9.5, Problem8.postfixEvaluator(List.of("-5.5", "2.5", "-", "3.5", "-5", "*", "-")));
        assertEquals(4, Problem8.postfixEvaluator(List.of("12", "3", "/")));
        assertEquals(0, Problem8.postfixEvaluator(List.of("")));
        assertEquals(2, Problem8.postfixEvaluator(List.of("3", "4", "+", "2", "*", "7", "/")));
    }
}