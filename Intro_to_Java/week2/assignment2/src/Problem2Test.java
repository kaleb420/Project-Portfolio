import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void hyperfactorial() {
        assertEquals(86400000, Problem2.hyperfactorial(5));
        assertEquals(1, Problem2.hyperfactorial(0));
        assertEquals(108, Problem2.hyperfactorial(3));
    }

    @Test
    void hyperfactorialTR() {
        assertEquals(86400000, Problem2.hyperfactorialTR(5));
        assertEquals(1, Problem2.hyperfactorialTR(0));
        assertEquals(108, Problem2.hyperfactorialTR(3));
    }

    @Test
    void hyperfactorialLoop() {
        assertEquals(86400000, Problem2.hyperfactorialLoop(5));
        assertEquals(1, Problem2.hyperfactorialLoop(0));
        assertEquals(108, Problem2.hyperfactorialLoop(3));
    }
}