public class LinkedQueue<T> {

    private final SinglyLinkedList<T> list = new SinglyLinkedList<>();

    public void enqueue(T val) {
        list.addLast(val);
    }

    public T dequeue() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}