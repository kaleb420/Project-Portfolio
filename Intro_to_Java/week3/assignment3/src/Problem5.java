import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Problem5 {
    /**
     * create a list of arrays for peaks to climb, a peak is defined in the document but it roughly
     * translates to the peak must be greater than the previous height and less than the next height
     * and it cannot be at the start or end of the mountain range
     * @param H array of heights on a mountain
     * @return peaks Joe would climb
     */
    static int[] peakFinder(int[] H){
        Set<Integer> s= new LinkedHashSet<>();
        int counter=0;
        for (int i = 1; i < H.length-1; i++) {
            if (H[i-1]<H[i] && H[i]>H[i+1])
                s.add(H[i]);
        }
        int[] arr= new int[s.size()];
        for (Integer j: s) {
            arr[counter]=j;
            counter+=1;
        }
        return arr;
    }
}
