public class Problem8 {
    /**
     * analyze if any two numbers are within a distance of +/-19 of each other
     * @param x input 1
     * @param y input 2
     * @param z input 3
     * @return true if any two numbers are within +/-19 of each other, or false is they are not within +/-19 of each other
     */
    static boolean lessThan20(int x, int y, int z){
        int upperX=x+19;
        int lowerX=x-19;
        int upperY=y+19;
        int lowerY=y-19;
        int upperZ=z+19;
        int lowerZ=z-19;
        if (x>=y && (upperX<=y || lowerX<=y))
            return true;
        else if (x>=z && (upperX<=z || lowerX<=z))
            return true;
        else if (y>=z && (upperY<=z || lowerY<=z))
            return true;
        else if (y>=x && (upperY<=x || lowerY<=x))
            return true;
        else if (z>=x && (upperZ<=x || lowerZ<=x))
            return true;
        else if (z>=y && (upperZ<=y || lowerZ<=y))
            return true;
        return false;
    }
}
