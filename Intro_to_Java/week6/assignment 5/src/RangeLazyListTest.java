import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RangeLazyListTest {

    @Test
    void RangeLazyListTests() {
        RangeLazyList test = RangeLazyList.range(6);
        assertEquals(0, test.next());
        assertEquals(1, test.next());
        assertEquals(2, test.next());
        assertEquals(3, test.next());
        assertEquals(4, test.next());
        assertEquals(5, test.next());
        assertEquals(0, test.next());
        RangeLazyList test2 = RangeLazyList.range(1,6,x -> x+1);
        assertEquals(1, test2.next());
        assertEquals(2, test2.next());
        assertEquals(3, test2.next());
        assertEquals(4, test2.next());
        assertEquals(5, test2.next());
        RangeLazyList test3 = RangeLazyList.range(0,13,x -> x+4);
        assertEquals(0, test3.next());
        assertEquals(4, test3.next());
        assertEquals(8, test3.next());
        assertEquals(12, test3.next());
    }
}