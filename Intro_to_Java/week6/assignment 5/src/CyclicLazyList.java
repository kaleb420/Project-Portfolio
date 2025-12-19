import java.util.ArrayList;
import java.util.List;

public class CyclicLazyList<T> implements ILazyList<T>{

    private T[] arr;
    private int counter;

    /**
     * constructor to initialize the instance variable
     * @param vals values to be iterated through
     */
    public CyclicLazyList(T... vals){
        arr=vals;
        counter=0;
    }
    /**
     * computes the next element in vals
     * @return next element in vals
     */
    @Override
    public T next() {
        if (counter<arr.length)
            return arr[counter++];
        counter=0;
        return arr[counter++];
    }
}
