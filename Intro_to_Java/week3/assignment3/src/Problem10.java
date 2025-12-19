import java.util.List;

public class Problem10 {
    /**
     * determines if two lists of the same type are parallel (if they differ by a constant factor)
     * @param t list 1
     * @param u list 2
     * @return true if the lists are parallel, false otherwise
     * @param <T> Type of object being used in the function
     */
    static <T extends List<Integer>> boolean areParallelLists(T t, T u){
        double constant=0;
        if (t.size()!=u.size())
            return false;
        if (t.size()==0 && u.size()==0)
            return true;
        if (t.get(0)==0) {
            for (int i = 0; i < t.size(); i++) {
                if (t.get(i) != 0)
                    return false;
            }
            return true;
        }
        else if (u.get(0)==0){
            for (int j = 0; j < u.size(); j++) {
                if (u.get(j)!=0)
                    return false;
            }
            return true;
        }
        if (t.get(0)>=u.get(0)) {
            constant = (double) t.get(0) / u.get(0);
            for (int i = 0; i < t.size(); i++) {
                if (u.get(i)*constant!=t.get(i))
                    return false;
            }
        }
        else{
            constant= (double) u.get(0) / t.get(0);
            for (int j = 0; j < t.size(); j++) {
                if (t.get(j)*constant!=u.get(j))
                    return false;
            }
        }
        return true;
    }
}
