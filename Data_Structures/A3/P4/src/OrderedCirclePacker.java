import java.util.Arrays;

public class OrderedCirclePacker extends AbstractCirclePacker {
    @Override
    public double packWidth(double[] r) {
        if (r.length==0)
            return 0;
        double[] x= new double[r.length];
        double[][] dp = new double[r.length][r.length];
        double width=Integer.MIN_VALUE;
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r.length; j++) {
                double addR=Math.pow(r[i]+r[j],2);
                double subR=Math.pow(r[i]-r[j],2);
                dp[i][j]=Math.sqrt(addR-subR);
            }
        }
        x[0]=r[0];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (j<i)
                    x[i]=Math.max(r[i], Math.max(x[j], x[j]+sep(r[i], r[j])));
            }
            if (x[i]>width)
                width=x[i]+r[i];
        }
        return width;
    }
}
