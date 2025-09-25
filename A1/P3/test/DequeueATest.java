import dynamicArray.DequeueA;
import exceptions.EmptyDequeueE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DequeueATest {

    @Test
    void testFront() throws EmptyDequeueE {
        DequeueA<Integer> q = new DequeueA<>(10);
        q.enqueueFront(1);
        q.enqueueFront(2);
        q.enqueueFront(3);
        assertEquals("[ 1 , 2 , 3 , _ , _ , _ , _ , _ , _ , _ ]", q.toString());
        assertEquals(3, q.dequeueFront());
        assertEquals(2, q.dequeueFront());
        assertEquals(1, q.dequeueFront());
    }

    @Test
    void testBack() throws EmptyDequeueE {
        DequeueA<Integer> q = new DequeueA<>(10);
        q.enqueueBack(1);
        q.enqueueBack(2);
        q.enqueueBack(3);
        assertEquals("[ _ , _ , _ , _ , _ , _ , _ , 3 , 2 , 1 ]", q.toString());
        assertEquals(3, q.dequeueBack());
        assertEquals(2, q.dequeueBack());
        assertEquals(1, q.dequeueBack());
    }

    @Test
    void testFrontBack() throws EmptyDequeueE {
        DequeueA<Integer> q = new DequeueA<>(10);
        q.enqueueFront(1);
        q.enqueueFront(2);
        q.enqueueFront(3);
        assertEquals("[ 1 , 2 , 3 , _ , _ , _ , _ , _ , _ , _ ]", q.toString());
        assertEquals(1, q.peekBack());
        assertEquals(1, q.dequeueBack());
        assertEquals(2, q.dequeueBack());
        assertEquals(3, q.dequeueBack());
    }

    @Test
    void testBackFront() throws EmptyDequeueE {
        DequeueA<Integer> q = new DequeueA<>(10);
        q.enqueueBack(1);
        q.enqueueBack(2);
        q.enqueueBack(3);
        assertEquals("[ _ , _ , _ , _ , _ , _ , _ , 3 , 2 , 1 ]", q.toString());
        assertEquals(3, q.peekBack());
        assertEquals(1, q.dequeueFront());
        assertEquals(2, q.dequeueFront());
        assertEquals(3, q.dequeueFront());
    }

    @Test
    void resize () {
        DequeueA<String> q = new DequeueA<>(5);
        assertEquals("[ _ , _ , _ , _ , _ ]", q.toString());
        q.enqueueFront("A");
        assertEquals("[ A , _ , _ , _ , _ ]", q.toString());
        q.enqueueFront("B");
        assertEquals("[ A , B , _ , _ , _ ]", q.toString());
        q.enqueueBack("C");
        assertEquals("[ A , B , _ , _ , C ]", q.toString());
        q.doubleCapacity();
        assertEquals("[ C , A , B , _ , _ , _ , _ , _ , _ , _ ]", q.toString());
    }

    @Test
    void complicated() throws EmptyDequeueE{
        DequeueA<Integer> q = new DequeueA<>(10);
        q.enqueueBack(1);
        q.enqueueBack(2);
        q.enqueueBack(3);
        assertEquals("[ _ , _ , _ , _ , _ , _ , _ , 3 , 2 , 1 ]", q.toString());
        q.enqueueFront(4);
        q.enqueueFront(5);
        q.enqueueFront(6);
        assertEquals("[ 4 , 5 , 6 , _ , _ , _ , _ , 3 , 2 , 1 ]", q.toString());
        q.doubleCapacity();
        assertEquals("[ 3 , 2 , 1 , 4 , 5 , 6 , _ , _ , _ , _ , _ , _ , _ , _ , _ , _ , _ , _ , _ , _ ]", q.toString());
        assertEquals(3, q.peekBack());
        assertEquals(3, q.dequeueBack());
        assertEquals(2, q.dequeueBack());
        assertEquals(1, q.dequeueBack());
        assertEquals(4, q.dequeueBack());
        assertEquals(5, q.dequeueBack());
        assertEquals(6, q.dequeueBack());
    }
}