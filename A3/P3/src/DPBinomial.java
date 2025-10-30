public class DPBinomial extends AbstractBinomial {
    @Override
    public long binom(int n, int k) {
        validate(n, k);
        int[][] C = new int[n+1][k+1];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[i].length; j++) {
                if (j==0 || i==0 || i==j) {
                    C[i][j]=1;
                    C[i][0]=C[i][j];
                }
                else
                    C[i][j]=C[i-1][j-1]+C[i-1][j];
            }
        }
        return C[n][k];
    }
}