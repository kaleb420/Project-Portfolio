public class RgbColor {
    private int red;
    private int green;
    private int blue;

    /**
     * constructor to initiate values to objects
     * @param red saturation
     * @param green saturation
     * @param blue saturation
     */
    public RgbColor (int red, int green, int blue){
        this.red=red;
        this.green=green;
        this.blue=blue;
    }
    public int getRed(){
        return red;
    }
    public void setRed(int r){
        red=r;
    }
    public int getGreen(){
        return green;
    }
    public void setGreen(int g){
        green=g;
    }
    public int getBlue(){
        return blue;
    }
    public void setBlue(int b){
        blue=b;
    }

    /**
     * inverts the color by subtracting each channels value by 255
     * @return the inverted channel of each color
     */
    public RgbColor invert(){
        int invertedR=255-red;
        int invertedG=255-green;
        int invertedB=255-blue;
        return new RgbColor(invertedR, invertedG, invertedB);
    }

    /**
     * creates a new RgbColor instance to grayscale the old values, taking the average of the three color channels
     * @return the grayscale object
     */
    public RgbColor grayscale(){
        int x= (red+green+blue)/3;
        return new RgbColor(x,x,x);
    }

    /**
     * determines if the given color is grayscale
     * @return true if it is grayscale, false otherwise
     */
    public boolean isGrayscale(){
        if (red==green && green==blue)
            return true;
        else
            return false;
    }

    /**
     * determines if two RgbColor objects are equal
     * @param o object that may or may not be an RgbColor
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof RgbColor){
            RgbColor otherColor = (RgbColor) o;
            return this.red==otherColor.getRed() && this.blue==otherColor.getBlue() && this.green== otherColor.getGreen();
        }
        else
            return false;
    }

    /**
     * converts the objects to a string format where they return their corresponding values
     * @return string of corresponding values of each object
     */
    @Override
    public String toString(){
        return "(" + red + ", " + green + ", " + blue + ")";
    }
}
