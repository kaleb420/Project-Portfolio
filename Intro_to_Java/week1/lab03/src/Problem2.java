public class Problem2 {
    /**
     * convert log formula into a given base
     * @param base desired
     * @param x value inputted
     * @return log with desired base
     */
    static double log(int base, double x){
        return Math.log10(x)/Math.log10(base);
    }
    /**
     * Computes value of x according to formula
     * @param x
     * @return value
     */
    static double crazyMath(double x){
        double first=Math.pow(Math.E,-x)+Math.cos(2/x);
        double above=Math.sin(Math.PI*x-2*Math.PI)+17*x*Math.PI;
        double below=log(3,Math.abs(x))*log(7,Math.abs(x))*Math.log(x);
        return Math.sqrt(Math.abs(first*(above/below)));
    }
}
