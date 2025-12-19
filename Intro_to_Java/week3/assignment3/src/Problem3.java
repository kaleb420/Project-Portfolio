public class Problem3 {

    /**
     * helper functions that uses tail recursion to calculate all possible sums
     * @param A array of integers
     * @param t desired value
     * @return true if it reaches a combination resulting in t, false otherwise
     */
    private static boolean canSumHelper(int[] A, int t, int index){
        boolean include;
        boolean exclude;
        if (t==0)
            return true;
        if (index==A.length)
            return false;
        include=canSumHelper(A, t-A[index], index+1);
        exclude=canSumHelper(A, t, index+1);
        return include || exclude;
    }
    /**
     * determine if there is a possible combination of integers in A that add up to t
     * @param A array of integers
     * @param t desired value
     * @return true if it is possible, false otherwise
     */
    static boolean canSum(int[] A, int t){
        return canSumHelper(A, t, 0);
    }
}
