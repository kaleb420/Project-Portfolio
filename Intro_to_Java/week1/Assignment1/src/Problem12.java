public class Problem12 {
    /**
     * if a given point is located inside the rectangle
     * @param rx x-axis center
     * @param ry y-axis center
     * @param w width
     * @param h height
     * @param px x-axis point
     * @param py y-axis point
     * @return true if the given point is inside the rectangle
     */
    static boolean isInsideRectangle(double rx, double ry, double w, double h, double px, double py){
        double wupperX=w*.5+rx;
        double wlowerX=rx-w*.5;
        double hupperY=h*.5+ry;
        double hlowerY=ry-h*.5;
        boolean xBounds=false;
        boolean yBounds=false;
        if (px<wupperX && px>wlowerX)
            xBounds=true;
        if (py<hupperY && py>hlowerY)
            yBounds=true;
        return xBounds && yBounds;
    }
}
