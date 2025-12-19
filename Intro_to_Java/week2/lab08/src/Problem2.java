public class Problem2 {
    /**
     * count how many even and odd numbers there are
     * @param vals values in the array
     * @return a tuple of # of even,odds
     */
    static int[] countEvenOdds(int[] vals){
        int[] storage={0,0};
        for (int i = 0; i < vals.length; i++) {
            if (vals[i]%2==0)
                storage[0]+=1;
            else
                storage[1]+=1;
        }
        return storage;
    }
}
