public class Problem4 {
    /**
     * determine the correlation coefficient given x and y values
     * @param xs sample array of x coordinates
     * @param ys sample array of y coordinates, affiliated with the x value at the corresponding index
     * @return r value (correlation coefficient)
     */
    static double correlationCoefficient(double[] xs, double[] ys){
        double summation=0;
        double xMean=0;
        double yMean=0;
        double xSD=0;
        double ySD=0;
        for (int i = 0; i < xs.length; i++) {
            xMean+=xs[i];
            yMean+=ys[i];
        }
        xMean/=xs.length;
        yMean/=ys.length;
        for (int j = 0; j < xs.length; j++) {
            xSD+=Math.pow(xs[j]-xMean,2);
            ySD+=Math.pow(ys[j]-yMean,2);
        }
        xSD=Math.sqrt(xSD/(xs.length-1));
        ySD=Math.sqrt(ySD/(ys.length-1));
        double first=1/(xs.length-1.0);
        double second=1/(xSD*ySD);
        for (int k = 0; k < xs.length; k++) {
            summation+=(xs[k]-xMean)*(ys[k]-yMean);
        }
        return first*second*summation;
    }
}
