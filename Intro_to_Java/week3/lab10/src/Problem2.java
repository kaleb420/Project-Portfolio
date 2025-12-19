import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Problem2 {
    /**
     * if a value occurs more than 3 times in an array add it to a new list
     * @param A Array
     * @return a set of value that occurs more than 3 times in an array
     */
    static Set<Integer> moreThanThree(int[] A){
        Set<Integer> s = new HashSet<>();
        HashMap<Integer, Integer> y = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (!y.containsKey(A[i]))
                y.put(A[i], 1);
            else if (y.containsKey(A[i])){
                y.put(A[i], y.get(A[i]) + 1);
                if (y.get(A[i]) > 3)
                    s.add(A[i]);
            }
        }
        return s;
    }
}
