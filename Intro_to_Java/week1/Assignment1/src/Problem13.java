public class Problem13 {
    /**
     * Determine if Carlo can fit the designated amount of small and large candies into a box of w width
     * @param s # of small bars
     * @param l # of large bars
     * @param w width of box
     * @return the number of small candies that can fit into the box
     */
    static int fitCandy(int s, int l, int w){
        int large=l*5;
        int afterLarge=w-large;
        if (s+large<w || (afterLarge<0 && w%5>s))
            return -1;
        else if (afterLarge<0)
            return w%5;
        return afterLarge;
    }
}
