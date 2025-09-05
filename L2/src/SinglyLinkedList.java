public class SinglyLinkedList<T> {

    private Node<T> head;
    private int size = 0;
    private Node<T> tail;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    // Add to front
    public void addFirst(T val) {
        if (head==null) {
            head = new Node<>(val);
            tail = head;
        }
        else {
            Node<T> temp = new Node<>(val);
            temp.next = head;
            head = temp;
        }
        size++;
    }

    // Add to end
    public void addLast(T val) {
        if (head==null) {
            head = new Node<>(val);
            tail = head;
        }
        else {
            tail.next = new Node<>(val);
            tail = tail.next;
        }
        size++;
    }

    // Remove first element
    public T removeFirst() {
        if (head==null)
            return null;
        else {
            T temp = head.data;
            head = head.next;
            size--;
            return temp;
        }
    }

    // Get value at index
    public T get(int index) {
        Node<T> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.data;
    }
}