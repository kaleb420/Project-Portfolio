public class Problem3 {
    /**
     * uses recursion to calculate subfactorial
     * @param n starting number
     * @return subfactorial
     */
    static long subfactorial(long n){
        if (n==0)
            return 1;
        else if (n==1)
            return 0;
        else
            return (n-1)*(subfactorial(n-1)+subfactorial(n-2));
    }
    /**
     * helper function to use tail recursion
     * @param n starting number
     * @param a current recursive iteration
     * @param b next recursive iteration
     * @return subfactorial
     */
    private static long subfactorialTRHelper(long n, long a, long b){
        if (n==0)
            return a;
        else if (n==1)
            return b;
        else
            return subfactorialTRHelper(n-1, b, (n-1)*(a+b));
    }

    /**
     * calculates subfactorial using tail recursion
     * @param n starting number
     * @return subfactorial
     */
    static long subfactorialTR(long n){
        return subfactorialTRHelper(n,1, 0);
    }
    /**
     * calculates the factorial of n and k
     * @param n starting number
     * @return factorial
     */
    private static long subfactorialLoopHelper(long n){
        long factorial=1;
        if (n==0)
            return 1;
        while (n!=0){
            factorial*=n;
            n-=1;
        }
        return factorial;
    }
    /**
     * calculates subfactorial using a loop
     * @param n starting number
     * @return subfactorial
     */
    static long subfactorialLoop(long n){
        long factorial=subfactorialLoopHelper(n);
        double summation=0;
        long kFactorial;
        for (int i = 0; i <= n; i++) {
            kFactorial=subfactorialLoopHelper(i);
            summation+= (Math.pow(-1, i)/kFactorial);
        }
        return (long) (factorial* summation);
    }
}
