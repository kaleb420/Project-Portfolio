import exceptions.EmptyDequeueE;
import org.junit.jupiter.api.Test;
import pointers.DequeueP;

import static org.junit.jupiter.api.Assertions.*;

class DequeuePTest {
    @Test
    void testFront() throws EmptyDequeueE {
        DequeueP<Integer> q = new DequeueP<>();
        q.enqueueFront(1);
        q.enqueueFront(2);
        q.enqueueFront(3);
        assertEquals("F[3, 2, 1]B", q.toString());
        assertEquals(3, q.dequeueFront());
        assertEquals(2, q.dequeueFront());
        assertEquals(1, q.dequeueFront());
    }

    @Test
    void testBack() throws EmptyDequeueE {
        DequeueP<Integer> q = new DequeueP<>();
        q.enqueueBack(1);
        q.enqueueBack(2);
        q.enqueueBack(3);
        assertEquals("F[1, 2, 3]B", q.toString());
        assertEquals(3, q.dequeueBack());
        assertEquals(2, q.dequeueBack());
        assertEquals(1, q.dequeueBack());
    }

    @Test
    void testFrontBack() throws EmptyDequeueE {
        DequeueP<Integer> q = new DequeueP<>();
        q.enqueueFront(1);
        q.enqueueFront(2);
        q.enqueueFront(3);
        assertEquals("F[3, 2, 1]B", q.toString());
        assertEquals(1, q.dequeueBack());
        assertEquals(2, q.dequeueBack());
        assertEquals(3, q.dequeueBack());
    }

    @Test
    void testComplicated() throws EmptyDequeueE {
        DequeueP<Integer> q = new DequeueP<>();
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
        assertEquals("F[]B", q.toString());

        q.enqueueFront(10);
        assertEquals(10, q.peekFront());
        assertEquals(10, q.peekBack());
        assertEquals(1, q.size());
        assertEquals("F[10]B", q.toString());

        q.enqueueBack(20);
        assertEquals(10, q.peekFront());
        assertEquals(20, q.peekBack());
        assertEquals(2, q.size());
        assertEquals("F[10, 20]B", q.toString());

        q.enqueueFront(5);
        assertEquals(5, q.peekFront());
        assertEquals(20, q.peekBack());
        assertEquals(3, q.size());
        assertEquals("F[5, 10, 20]B", q.toString());

        assertEquals(20, q.dequeueBack());
        assertEquals(5, q.peekFront());
        assertEquals(10, q.peekBack());
        assertEquals(2, q.size());
        assertEquals("F[5, 10]B", q.toString());

        assertEquals(5, q.dequeueFront());
        assertEquals(10, q.peekFront());
        assertEquals(10, q.peekBack());
        assertEquals(1, q.size());
        assertEquals("F[10]B", q.toString());

        q.enqueueBack(15);
        q.enqueueFront(2);
        assertEquals(2, q.peekFront());
        assertEquals(15, q.peekBack());
        assertEquals(3, q.size());
        assertEquals("F[2, 10, 15]B", q.toString());

        assertEquals(2, q.dequeueFront());
        assertEquals(15, q.dequeueBack());
        assertEquals(10, q.peekFront());
        assertEquals(10, q.peekBack());
        assertEquals(1, q.size());
        assertEquals("F[10]B", q.toString());

        assertEquals(10, q.dequeueFront());
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
        assertEquals("F[]B", q.toString());
    }
}