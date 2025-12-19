public class Problem2 {
    /** calculate total grocery cost based on given items
     *
     * @param a # of apples
     * @param b # of bananas
     * @param o # of oranges
     * @param g # of grapes
     * @param p # of pineapples
     * @return total cost
     */
    static double grocery(int a, int b, int o, int g, int p){
        return a*.59+b*.99+o*.45+g*1.39+p*2.24;
    }
}
