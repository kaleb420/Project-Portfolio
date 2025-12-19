import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MiniStackTest {

    @org.junit.jupiter.api.Test
    void MiniStackTest() {
        MiniStack<Integer> stk = new MiniStack<>();
        stk.add(1);
        assertEquals(Optional.of(1), stk.peek());
        stk.add(2);
        assertEquals(Optional.of(2), stk.peek());
        assertEquals(2, stk.size());
        assertEquals(Optional.of(2), stk.pop());
        assertEquals(Optional.of(1), stk.pop());
        assertEquals(Optional.empty(), stk.pop());
        assertEquals(Optional.empty(), stk.peek());
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(5);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(2);
        stk.add(5);
        assertEquals(Optional.of(5),stk.peek());
    }
}