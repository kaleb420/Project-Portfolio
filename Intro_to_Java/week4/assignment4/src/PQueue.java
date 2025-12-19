public class PQueue<T> {
    private static class Node<T>{
        private Node<T> next;
        private T value;

        /**
         * constructor to initialize value
         * @param value provided
         */
        private Node(T value){
            this.value=value;
        }

    }
    private Node<T> first;
    private Node<T> last;
    private int counter;

    /**
     * constructor to initialize the instance variables in PQueue
     */
    PQueue(){
        this.last=null;
        this.first=null;
        this.counter=0;
    }

    /**
     * create a new queue with the same elements of the current one
     * @return new priority queue of the same elements
     */
    private PQueue<T> copy(){
        PQueue<T> newPQueue= new PQueue<>();
        Node<T> newNode;
        Node<T> tempPQueueFirst=null;
        int i=0;
        if (counter==0) {
            newPQueue.first=null;
            newPQueue.last=null;
        }
        else{
            Node<T> tempFirst=this.first;
            while (i<counter) {
                newNode=new Node<>(tempFirst.value);
                if (i==0){
                    newPQueue.first=newNode;
                    tempPQueueFirst=newPQueue.first;
                }
                else{
                    tempPQueueFirst.next=newNode;
                    tempPQueueFirst=tempPQueueFirst.next;
                }
                tempFirst=tempFirst.next;
                i++;
            }
            newPQueue.last=tempPQueueFirst;
        }
        newPQueue.counter=this.counter;
        return newPQueue;
    }

    /**
     * determines if the given object is equal to the current PQueue
     * @param o object which may or may not be of the PQueue class
     * @return true if they are the same, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof PQueue){
            PQueue<T> newPQueue= (PQueue) o;
            if (counter!=newPQueue.counter)
                return false;
            Node<T> newFirst=this.first;
            Node<T> newPQueueFirst=newPQueue.first;
            for (int i = 0; i < counter; i++) {
                if (newFirst.value!=newPQueueFirst.value)
                    return false;
                newFirst=newFirst.next;
                newPQueueFirst=newPQueueFirst.next;
            }
            return true;
        }
        return false;
    }

    /**
     * adds a new element to the end of a new PQueue
     * @param t value to be added
     * @return new PQueue with added element
     */
    PQueue<T> enqueue(T t){
        Node<T> newNode = new Node<>(t);
        PQueue<T> newPQueue = this.copy();
        if (newPQueue.counter==0) {
            newPQueue.first=newNode;
            newPQueue.last=newNode;
        }
        else{
            newPQueue.last.next=newNode;
            newPQueue.last=newPQueue.last.next;
        }
        newPQueue.counter++;
        return newPQueue;
    }

    /**
     * removes the first element of PQueue
     * @return new PQueue with removed element
     */
    PQueue<T> dequeue(){
        PQueue<T> newPQueue = this.copy();
        if (newPQueue.counter==0)
            return newPQueue;
        newPQueue.first=newPQueue.first.next;
        newPQueue.counter--;
        return newPQueue;
    }

    /**
     * finds the first element of PQueue
     * @return first element of PQueue
     */
    T peek(){
        if (first!=null)
            return first.value;
        return null;
    }

    /**
     * creates a PQueue with the values past through
     * @param <T> type of value being passed
     * @param vals value being added to PQueue
     * @return new PQueue with passed values
     */
    static <T> PQueue<T> of(T... vals){
        PQueue<T> newPQueue = new PQueue<>();
        int i=0;
        Node<T> tempFirst=null;
        if (vals.length==0)
            return newPQueue;
        while (i<vals.length){
            if (i==0) {
                newPQueue.first = new Node<>(vals[0]);
                tempFirst=newPQueue.first;
                if (vals.length>1)
                    newPQueue.first.next= new Node<>(vals[1]);
            }
            else if (i!=vals.length-1) {
                newPQueue.first.next = new Node<>(vals[i + 1]);
                if (i + 1 == vals.length - 1) {
                    newPQueue.last = newPQueue.first.next;
                    break;
                }
            }
            newPQueue.first=newPQueue.first.next;
            i++;
        }
        newPQueue.first=tempFirst;
        newPQueue.counter+=vals.length;
        return newPQueue;
    }

    /**
     * compute the size of PQueue
     * @return size of PQueue
     */
    int size(){
        return counter;
    }
}