import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {

    @org.junit.jupiter.api.Test
    void popChars() {
        assertEquals("Bld", Problem1.popChars("Bald", 'c', 'a'));
        assertEquals("ald", Problem1.popChars("Bald", 'B', 'p'));
        assertEquals("w", Problem1.popChars("wow", 'w', 'o'));
        assertEquals("", Problem1.popChars("w", 'w', 'o'));
        assertEquals("a", Problem1.popChars("a", 'w', 'a'));
        assertEquals("", Problem1.popChars("", 'w', 'o'));
        assertEquals("", Problem1.popChars("ab", 'a', 'b'));
    }
}