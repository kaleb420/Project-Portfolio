import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PQueueTest {

    @Test
    void PQueueTests() {
        PQueue<Integer> p = PQueue.of(1,2,3);
        PQueue<Integer> p2 = PQueue.of(1,2,3);
        PQueue<Integer> p3 = PQueue.of(2,3,4);
        assertEquals(3, p.size());
        assertEquals(1, p.peek());
        assertEquals(true, p.equals(p2));
        PQueue<Integer> p4=p.dequeue(); // p4 is 2,3
        assertEquals(2, p4.peek());
        assertEquals(2, p4.size());
        PQueue<Integer> p5=p4.enqueue(4); // p5 is 2,3,4
        assertEquals(3, p5.size());
        assertEquals(false, p4.equals(p2));
        assertEquals(true, p5.equals(p3));
        PQueue<Integer> p6 = PQueue.of();
        PQueue<Integer> p7 = PQueue.of();
        assertEquals(null, p6.peek());
        assertEquals(p6, p6.dequeue());
        assertEquals(true, p6.equals(p7));
        PQueue<Integer> p8 = p6.enqueue(1); // p8 is 1
        assertEquals(1, p8.peek());
        PQueue<Integer> p9 = p8.dequeue(); // p9 is empty
        assertEquals(null, p9.peek());
        PQueue<String> p10 = PQueue.of("bald", "balding");
        assertEquals("bald", p10.peek());
        PQueue<String> p11 = p10.dequeue(); // p11 is "balding"
        assertEquals("balding", p11.peek());
        PQueue<String> p12 = p11.dequeue(); // p12 is empty
        PQueue<String> p13 = p12.enqueue("baldhaha"); // p13 is "baldhaha"
        assertEquals("baldhaha", p13.peek());
        PQueue<Double> p14 = PQueue.of(5.2);
    }
}