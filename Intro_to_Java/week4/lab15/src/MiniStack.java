import java.util.*;

public class MiniStack<T> {
    private  T[] elements;
    private int size;
    private int capacity;
    private static final int INITIAL_CAPACITY=10;
    private static final int RESIZE_FACTOR=2;
    public MiniStack(){
        this.capacity=INITIAL_CAPACITY;
        size=0;
        this.elements= (T[]) new Object[MiniStack.INITIAL_CAPACITY];
    }

    void resize(){
        T[] temp = (T[]) new Object[capacity * MiniStack.RESIZE_FACTOR];
        capacity*=RESIZE_FACTOR;
        for (int i = 0; i < size; i++) {
            temp[i]= elements[i];
        }
        this.elements=temp;
    }
    /**
     * adds an element on the top (right most element) of the stack
     * @param t Type of data being inputted
     */
    void add (T t){
        if (capacity==size)
            resize();
        elements[size]=t;
        size++;
    }

    /**
     * finds (but doesn't remove) the top (right most element) of the stack
     * @return top most element, if non exists return an empty optional
     */
    Optional<T> peek(){
        if (size==0)
            return Optional.empty();
        else
            return Optional.of(elements[size-1]);
    }

    /**
     * finds and removes the top (right most element) of the stack
     * @return value removed
     */
    Optional<T> pop(){
        T temp;
        if (size==0)
            return Optional.empty();
        else {
            temp = elements[size - 1];
            elements[size-1]=null;
            size--;
            return Optional.of(temp);
        }
    }

    /**
     * determines the number of logical (non null) elements
     * @return the number of elements
     */
    int size(){
        return size;
    }
}
