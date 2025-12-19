public class Problem4 {
    /**
     * calculates collatz method recursively
     * @param n starting number
     * @return String of sequence
     */
    static String collatz(int n){
        if (n==1)
            return "1";
        else if (n%2==1)
            return n + "," + collatz(3*n+1);
        else
            return n + "," + collatz(n/2);
    }

    /**
     * helper function for the tail recursion
     * @param n starting number
     * @param acc accumulator storing the string
     * @return String of sequence
     */
    private static String collatzTRHelper(int n, String acc ){
        String s= String.valueOf(n);
        if (n==1)
            return acc+s;
        else if (n%2==1)
            return collatzTRHelper(3*n+1, acc+=s + ",");
        else
            return collatzTRHelper(n/2, acc+=s + ",");
    }

    /**
     * uses tail recursion to print the collatz method
     * @param n starting number
     * @return String of sequence
     */
    static String collatzTR(int n){
        return collatzTRHelper(n, "");
    }

    /**
     * uses a loop to print the collatz method
     * @param n starting number
     * @return String of sequence
     */
    static String collatzLoop(int n){
        String s="";
        while (n!=1){
            s+=n + ",";
            if (n%2==1)
                n=3*n+1;
            else
                n=n/2;
        }
        s+="1";
        return s;
    }
}
