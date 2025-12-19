import java.util.Arrays;

public class Problem1 {
    /**
     * repeat strings A[i] amount of times
     * @param S String
     * @param A Array for how many times the string at i is repeated
     * @return repeated array of strings
     */
    static String[] repeatStrings(String[] S, int[] A){
        String[] x= new String[(S.length)];
        for (int i = 0; i < S.length; i++) {
            String y="";
            for (int j = 0; j < A[i]; j++) {
                y+=S[i];
            }
            x[i]=y;
        }
        return x;
    }
}
