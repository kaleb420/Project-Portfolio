public class FibonacciLazyList implements ILazyList<Integer>{

    private static int previous;
    private static int current;

    /**
     * constructor to initialize the values to the starting values of the fibonacci sequence
     */
    FibonacciLazyList(){
        this.previous=0;
        this.current=1;
    }

    /**
     * computes the next element in the fibonacci sequence
     * @return current element in the fibonacci sequence
     */
    @Override
    public Integer next() {
        int temp=previous;
        current=current+previous;
        previous=current-previous;
        return temp;
    }
}
