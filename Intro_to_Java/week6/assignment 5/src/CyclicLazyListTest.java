import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CyclicLazyListTest<T> {

    @Test
    void CyclicLazyListTests() {
        CyclicLazyList test = new CyclicLazyList(1,2,3,4,5);
        CyclicLazyList test2 = new CyclicLazyList(1);
        assertEquals(1, test.next());
        assertEquals(2, test.next());
        assertEquals(3, test.next());
        assertEquals(4, test.next());
        assertEquals(5, test.next());
        assertEquals(1, test.next());
        assertEquals(1, test2.next());
        assertEquals(1, test2.next());
    }
}