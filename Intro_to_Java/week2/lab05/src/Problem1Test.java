import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void replaceAB() {
        assertEquals("Bald", Problem1.replaceAB("Aald"));
        assertEquals("BasketBall", Problem1.replaceAB("AasketAall"));
        assertEquals("Bald", Problem1.replaceAB("Bald"));
        assertEquals("B", Problem1.replaceAB("A"));
        assertEquals("", Problem1.replaceAB(""));
    }
}