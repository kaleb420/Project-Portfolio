import java.util.ArrayList;
import java.util.List;

public class Sort<T> {

    /**
     * merges the two sorted list into one sorted list
     * @param left left sorted list
     * @param right right sorted list
     * @return combined sorted list
     * @param <T> generic type given
     */
    static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right){
        int i=0;
        int j=0;
        List<T> sorted= new ArrayList<>();
        while (i<left.size() && j<right.size()){
            if (left.get(i).compareTo(right.get(j))<0)
                sorted.add(left.get(i++));
            else
                sorted.add(right.get(j++));
        }
        while (i<left.size()) {
            sorted.add(left.get(i++));
        }
        while (j<right.size()){
            sorted.add(right.get(j++));
        }
        return sorted;
    }
    /**
     * sorts the generic given list
     * @param L list given
     * @return new list of sorted elements
     * @param <T> generic type given
     */
    static <T extends Comparable<T>> List<T> sort(List<T> L){
        if (L.isEmpty())
            return L;
        else if (L.size()==1){
            List<T> ls = new ArrayList<>();
            ls.add(L.get(0));
            return ls;
        }
        int mid=(L.size()-1)/2;
        List<T> left = new ArrayList<>(L.subList(0, mid+1));
        List<T> right = new ArrayList<>(L.subList(mid+1,L.size()));
        List<T> leftSort = sort(left);
        List<T> rightSort= sort(right);
        return merge(leftSort,rightSort);
    }
}
