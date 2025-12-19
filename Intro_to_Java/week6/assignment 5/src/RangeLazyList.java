import java.util.*;
import java.util.function.Function;

public class RangeLazyList implements ILazyList<Integer>, Iterator<Integer>, Iterable<Integer>{

    private int start;
    private int end;
    private int current;
    private Function<Integer, Integer> m;

    /**
     * constructor to initialize instance variables
     * @param s start index
     * @param e end index
     * @param f unary function to iterate over
     */
    private RangeLazyList(int s, int e, Function<Integer, Integer> f){
        this.start=s;
        this.end=e;
        this.current=s;
        this.m=f;
    }

    /**
     * constructor to determine the next element in the list
     * @param n end value
     */
    private RangeLazyList(int n){
        this(0, n, x -> x+1);
    }

    /**
     * determines if there is another element in the list
     * @return true if the iteration has more elements, otherwise false
     */
    @Override
    public boolean hasNext() {
        if (current<end)
            return true;
        return false;
    }

    /**
     * computes the next element in the list, if there is one
     * @return next element in the list
     */
    @Override
    public Integer next() {
        int result=0;
        if (hasNext()){
            result=current;
            current=m.apply(current++);
        }
        return result;
    }

    /**
     * Returns an iterator over elements of type, because it's definitionally the same to the parent function returning this works
     * @return I think this returns the next element in the list
     */
    @Override
    public Iterator<Integer> iterator() {
        return this;
    }

    /**
     * creates a new instance of RangeLazyList from [s,e) given the parameters
     * @param s start index
     * @param e end index
     * @param f unary function
     * @return new instance of RangeLazyList
     */
    static RangeLazyList range(int s, int e, Function<Integer, Integer> f){
        return new RangeLazyList(s, e, f);
    }

    /**
     * creates a new instance of RangeLazyList from [0,n), with the given function
     * @param n end index
     * @return new instance of RangeLazyList
     */
    static RangeLazyList range(int n){
        return new RangeLazyList(n);
    }
}
