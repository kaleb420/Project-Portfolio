import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LazyListTakeTest {

    @Test
    void LazyListTakeTests() {
        LazyListTake t = new LazyListTake(new FibonacciLazyList(), 10);
        LazyListTake t2 = new LazyListTake(new FibonacciLazyList(), 0);
        assertEquals("[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]", t.getList().toString());
        assertEquals("[]", t2.getList().toString());
    }
}