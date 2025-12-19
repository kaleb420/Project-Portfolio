import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem4Test {

    @Test
    void collatz() {
        assertEquals("5,16,8,4,2,1", Problem4.collatz(5));
        assertEquals("4,2,1", Problem4.collatz(4));
        assertEquals("1", Problem4.collatz(1));
    }

    @Test
    void collatzTR() {
        assertEquals("5,16,8,4,2,1", Problem4.collatzTR(5));
        assertEquals("4,2,1", Problem4.collatzTR(4));
        assertEquals("1", Problem4.collatzTR(1));
    }

    @Test
    void collatzLoop() {
        assertEquals("5,16,8,4,2,1", Problem4.collatzLoop(5));
        assertEquals("4,2,1", Problem4.collatzLoop(4));
        assertEquals("1", Problem4.collatzLoop(1));
    }
}