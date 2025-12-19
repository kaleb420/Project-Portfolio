public class Problem2 {
    /**
     * uses recursion to calculate hyperfactorial
     * @param n starting number
     * @return hyperfactorial
     */
    static long hyperfactorial(long n){
        if (n==0)
            return 1;
        else
            return (long) (Math.pow(n,n)*hyperfactorial(n-1));
        }

    /**
     * helper function that uses tail recursion
     * @param n starting number
     * @param acc accumulator
     * @return hyperfactorial
     */
    private static long hyperfactorialTRHelper(long n, long acc){
        if (n==0)
            return acc;
        else
            return hyperfactorialTRHelper(n-1, (long) (acc*Math.pow(n,n)));
    }
    /**
     * uses tail recursion to calculate hyperfactorial
     * @param n starting number
     * @return hyperfactorial
     */
    static long hyperfactorialTR(long n){
        return hyperfactorialTRHelper(n,1);
    }
    /**
     * uses a loop to calculate hyperfactorial
     * @param n starting number
     * @return hyperfactorial
     */
    static long hyperfactorialLoop(long n){
        long hyperfactorial=1;
        while (n!=0){
            hyperfactorial*=(long)Math.pow(n,n);
            n-=1;
        }
        return hyperfactorial;
    }
}
