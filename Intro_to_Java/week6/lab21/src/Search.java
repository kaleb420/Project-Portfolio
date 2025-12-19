import java.util.Comparator;
import java.util.List;

public class Search<T> {

    /**
     * takes a generic list of elements, the key, and a comparator to determine the index of k
     * @param L list given
     * @param k key
     * @param c comparator
     * @return index of k
     * @param <T> generic type being used
     */
    static <T> int binarySearch(List<T> L, T k, Comparator<T> c){
        int searchIndex=L.size()/2;
        int upper=L.size()-1;
        int lower=0;
        while (c.compare(L.get(searchIndex), k)!=0){
            searchIndex = (lower + upper) / 2;
            if (lower>upper)
                return -1;
            else if (c.compare(L.get(searchIndex), k)<0){
                lower=searchIndex + 1;
            }
            else if (c.compare(L.get(searchIndex), k)>0){
                upper=searchIndex - 1;
            }
        }
        return searchIndex;
    }

    /**
     * same as last but without using the comparator
     * @param L list given
     * @param k key
     * @return index of k
     * @param <T> generic type being used
     */
    static <T extends Comparable<T>> int binarySearch(List<T> L, T k){
        int searchIndex=L.size()/2;
        int upper=L.size();
        int lower=0;
        while (L.get(searchIndex)!=k){
            if (lower>upper)
                return -1;
            else if (L.get(searchIndex).compareTo(k)<0){
                lower=searchIndex;
                searchIndex=(upper+searchIndex)/2;
            }
            else if (L.get(searchIndex).compareTo(k)>0){
                upper=searchIndex;
                searchIndex=(searchIndex+lower)/2;
            }
        }
        return searchIndex;
    }
}
