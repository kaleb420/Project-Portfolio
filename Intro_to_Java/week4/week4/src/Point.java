public class Point {
    private final int X;
    private final int Y;

    /**
     * constructor for x and y values
     * @param x x given
     * @param y y given
     */
    public Point (int x, int y){
        this.X=x;
        this.Y=y;
    }

    public int getX() {
        return X;
    }

    public int getY(){
        return Y;
    }
    public double distance(Point p){
        return Math.sqrt(Math.pow(p.X-this.X,2) + Math.pow(p.Y-this.Y,2));
    }
}
