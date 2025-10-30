public class NeedlemanWunsch extends AbstractAlignment {
    public NeedlemanWunsch() {
        super(+1, -1, -2); // default scoring: match=+1, mismatch=-1, gap=-2
    }

    public String reverse(String str){
        String temp="";
        for (int i = str.length()-1; i >= 0; i--) {
            temp+=str.charAt(i);
        }
        return temp;
    }

    @Override
    public AlignmentResult align(String X, String Y) {
        int[][] dp = new int[X.length() + 1][Y.length() + 1];
        String alignedX="";
        String alignedY="";
        if (X.isEmpty()) {
            while (alignedX.length() != Y.length()) {
                alignedX += "-";
            }
            return new AlignmentResult(alignedX, Y, alignedX.length()*gapPenalty);
        }
        else if (Y.isEmpty()){
            while (alignedY.length()!=X.length()){
                alignedY+="-";
            }
            return new AlignmentResult(X, alignedY, alignedY.length()*gapPenalty);
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0]=i*gapPenalty;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i]=i*gapPenalty;
        }
        int tempMax;
        int match;
        int firstGap;
        int secondGap;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                match = dp[i - 1][j - 1] + score(X.charAt(i - 1), Y.charAt(j - 1));
                firstGap = dp[i - 1][j] + gapPenalty;
                secondGap = dp[i][j - 1] + gapPenalty;
                tempMax = Math.max(match, firstGap);
                tempMax = Math.max(tempMax, secondGap);
                dp[i][j] = tempMax;
            }
        }
        int i=dp.length-1;
        int j=dp[0].length-1;
        while (i>0 && j>0){
            if (dp[i][j]==dp[i-1][j-1] + score(X.charAt(i-1), Y.charAt(j-1))){
                alignedX+=X.charAt(i-1);
                alignedY+=Y.charAt(j-1);
                i--;
                j--;
            }
            else if (dp[i][j]==dp[i-1][j]+gapPenalty){
                alignedX+=X.charAt(i-1);
                alignedY+="-";
                i--;
            }
            else if(dp[i][j]==dp[i][j-1]+gapPenalty){
                alignedX+="-";
                alignedY+=Y.charAt(j-1);
                j--;
            }
        }
        while (i > 0) {
            alignedX += X.charAt(i - 1);
            alignedY += "-";
            i--;
        }
        while (j > 0) {
            alignedX += "-";
            alignedY += Y.charAt(j - 1);
            j--;
        }
        alignedX=reverse(alignedX);
        alignedY=reverse(alignedY);
        return new AlignmentResult(alignedX, alignedY, dp[X.length()][Y.length()]);
    }
}