import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void fToC() {
        assertEquals(0, Problem1.fToC(32));
        assertEquals(100, Problem1.fToC(212));
        assertEquals(-40, Problem1.fToC(-40));
    }
}