import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void replaceABLoop() {
        assertEquals("Bald", Problem1.replaceABLoop("Aald"));
        assertEquals("BasketBall", Problem1.replaceABLoop("AasketAall"));
        assertEquals("Bald", Problem1.replaceABLoop("Bald"));
        assertEquals("B", Problem1.replaceABLoop("A"));
        assertEquals("", Problem1.replaceABLoop(""));
    }
}