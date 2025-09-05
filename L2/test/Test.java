import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class Test {

    @Nested
    class LinkedListTest {
        @org.junit.jupiter.api.Test
        void sizeTest() {
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            assertEquals(0, list.size());
            list.addFirst(1);
            list.addLast(2);
            assertEquals(2, list.size());
            list.removeFirst();
            assertEquals(1, list.size());
        }

        @org.junit.jupiter.api.Test
        void isEmptyTest() {
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            assertTrue(list.isEmpty());
            list.addFirst(42);
            assertFalse(list.isEmpty());
            list.removeFirst();
            assertTrue(list.isEmpty());
        }

        @org.junit.jupiter.api.Test
        void getTest() {
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            list.addLast(1);
            list.addLast(2);
            list.addLast(3);
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
        }

        @org.junit.jupiter.api.Test
        void addFirstTest() {
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            list.addFirst(1);
            list.addFirst(2);
            list.addFirst(3);
            assertEquals(3, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(1, list.get(2));

        }

        @org.junit.jupiter.api.Test
        void addLastTest() {
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            list.addLast(1);
            list.addLast(2);
            list.addLast(3);
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
        }

        @org.junit.jupiter.api.Test
        void removeFirstTest() {
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            list.addFirst(1);
            list.addFirst(2);
            list.addFirst(3);
            assertEquals(3,list.removeFirst());
            assertEquals(2,list.removeFirst());
            assertEquals(1,list.removeFirst());
        }

    }

    @Nested
    class StackTest{
        @org.junit.jupiter.api.Test
        void isEmptyTest() {
            LinkedStack<String> s = new LinkedStack<>();
            assertTrue(s.isEmpty());
            s.push("x");
            assertFalse(s.isEmpty());
            s.pop();
            assertTrue(s.isEmpty());
        }

        @org.junit.jupiter.api.Test
        void pushTest() {
            LinkedStack<String> s = new LinkedStack<>();
            s.push("1");
            s.push("2");
            s.push("3");
            assertEquals("3", s.pop());
            assertEquals("2", s.pop());
            assertEquals("1", s.pop());
        }

        @org.junit.jupiter.api.Test
        void popTest() {
            LinkedStack<String> s = new LinkedStack<>();
            s.push("1");
            s.push("2");
            s.push("3");
            assertEquals("3", s.pop());
            assertEquals("2", s.pop());
            assertEquals("1", s.pop());
        }
    }

    @Nested
    class QueueTest {
        @org.junit.jupiter.api.Test
        void isEmptyTest() {
            LinkedQueue<Integer> q = new LinkedQueue<>();
            assertTrue(q.isEmpty());
            q.enqueue(10);
            assertFalse(q.isEmpty());
            q.dequeue();
            assertTrue(q.isEmpty());
        }

        @org.junit.jupiter.api.Test
        void enqueueTest() {
            LinkedQueue<Integer> q = new LinkedQueue<>();
            q.enqueue(1);
            q.enqueue(2);
            q.enqueue(3);
            assertEquals(1,q.dequeue());
            assertEquals(2,q.dequeue());
            assertEquals(3,q.dequeue());
        }

        @org.junit.jupiter.api.Test
        void dequeueTest() {
            LinkedQueue<Integer> q = new LinkedQueue<>();
            q.enqueue(1);
            q.enqueue(2);
            q.enqueue(3);
            assertEquals(1,q.dequeue());
            assertEquals(2,q.dequeue());
            assertEquals(3,q.dequeue());
        }
    }
}