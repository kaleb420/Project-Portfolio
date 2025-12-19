import java.util.ArrayList;
import java.util.List;

public class LazyListTake<T> implements ILazyList<T>{

    private ILazyList<T> list;
    private int n;
    private int counter;

    /**
     * constructor to initialize the instance variables
     * @param list given lazy list
     * @param n number of elements
     */
    public LazyListTake(ILazyList<T> list, int n){
        this.list=list;
        this.n=n;
        counter=0;
    }

    /**
     * computes the next element in the given list
     * @return next element in the given list
     */
    @Override
    public T next() {
        return list.next();
    }

    /**
     * computes n elements from the given lazy list
     * @return n elements from the given lazy list
     */
    List<T> getList(){
        List<T> l = new ArrayList<>();
        while (counter<n){
            l.add(next());
            n--;
        }
        return l;
    }
}
