public class Problem5 {
    /**
     * calculates the length of the third side of the triangle
     * @param a length of one side
     * @param b length of the second side
     * @param th angle between side a and side b
     * @return length of third side
     */
    static double lawOfCosines(double a, double b, double th){
        double squareA=Math.pow(a,2);
        double squareB=Math.pow(b,2);
        double radians=Math.toRadians(th);
        return Math.sqrt(squareA+squareB-2*a*b*Math.cos(radians));
    }
}
