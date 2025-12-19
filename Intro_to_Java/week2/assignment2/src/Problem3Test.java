import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class Problem3Test {

    @Test
    void subfactorial() {
        assertEquals(1854, Problem3.subfactorial(7));
        assertEquals(9, Problem3.subfactorialTR(4));
        assertEquals(2, Problem3.subfactorial(3));
        assertEquals(1, Problem3.subfactorial(0));
        assertEquals(0, Problem3.subfactorial(1));
    }

    @Test
    void subfactorialTR() {
        assertEquals(1854, Problem3.subfactorialTR(7));
        assertEquals(9, Problem3.subfactorialTR(4));
        assertEquals(2, Problem3.subfactorialTR(3));
        assertEquals(1, Problem3.subfactorialTR(0));
        assertEquals(0, Problem3.subfactorialTR(1));
    }

    @Test
    void subfactorialLoop() {
        assertEquals(1854, Problem3.subfactorialLoop(7));
        assertEquals(9, Problem3.subfactorialTR(4));
        assertEquals(2, Problem3.subfactorialLoop(3));
        assertEquals(1, Problem3.subfactorialLoop(0));
        assertEquals(0, Problem3.subfactorialLoop(1));
    }
}