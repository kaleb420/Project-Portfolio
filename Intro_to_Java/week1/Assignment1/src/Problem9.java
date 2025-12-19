public class Problem9 {
    /**
     * determine if a constant can be added to the smallest number to reach the middle number, and added again to the middle number to reach the larger number
     * @param x first integer
     * @param y second integer
     * @param z third integer
     * @return true or false depending if the condition is satisfied
     */
    static boolean isEvenlySpaced(int x, int y, int z){
        int constant=x-y;
        if (x+constant/2==z || x-constant/2==z || x+constant*2==z || x-constant*2==z)
            return true;
        return false;
    }
}
