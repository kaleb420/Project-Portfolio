public class Problem10 {
    /**
     * computes the left-Riemann approximation of the area of a circle using height*width
     * @param r radius
     * @param delta delta
     * @return approximate area of circle
     */
    static double circleArea(double r, double delta){
        int numberOfRectangles= (int) (r/delta);
        double width=delta;
        double quadrant=0;
        double x=0;
        int i=0;
        while (i<numberOfRectangles){
            double height=Math.sqrt(Math.pow(r,2)-Math.pow(x,2));
            quadrant+=height*width;
            i+=1;
            x+=delta;
        }
        return quadrant*4;
    }
}
