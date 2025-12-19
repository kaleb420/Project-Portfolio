public class Problem3 {
    /** calculates the pyramid surface based on the given dimensions
     *
     * @param l length
     * @param w width
     * @param h height
     * @return surface area
     */
    static double pyramidSurfaceArea(double l, double w, double h){
        double squareRoot1=l*w+l*Math.sqrt(Math.pow(w/2,2)+Math.pow(h,2));
        double squareRoot2=w*Math.sqrt(Math.pow(l/2,2)+Math.pow(h,2));
        return squareRoot1+squareRoot2;
    }
}
