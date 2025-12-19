import static org.junit.jupiter.api.Assertions.*;

class FibonacciLazyListTest {
    @org.junit.jupiter.api.Test
    void FibonacciTests() {
        ILazyList<Integer> FS = new FibonacciLazyList();
        assertEquals(0, FS.next());
        assertEquals(1, FS.next());
        assertEquals(1, FS.next());
        assertEquals(2, FS.next());
        assertEquals(3, FS.next());
        assertEquals(5, FS.next());
        assertEquals(8, FS.next());
        assertEquals(13, FS.next());
    }
}