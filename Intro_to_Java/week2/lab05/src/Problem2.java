public class Problem2 {
    /**
     * helps the driver function by adding an accumulator
     * @param s string inputted from the driver function
     * @param acc accumulator
     * @return true or false
     */
    private static boolean balancedHelper(String s, int acc) {
        if (s.length() == 0){
            if (acc == 0)
                return true;
            else
                return false;
        }
        else {
            if (s.charAt(0) == '(')
                return balancedHelper(s.substring(1), acc + 1);
            else if (s.charAt(0) == ')' && acc>=1)
                return balancedHelper(s.substring(1), acc - 1);
        }
        return false;
    }
    /**
     determines if there is an equal amount of left and right parenthesis
     * @param s string inputted
     * @return true or false depending if there is an equal amount of parenthesis
     */
    static boolean isNestedParenthesesTR(String s){
        return balancedHelper(s,0);
    }
}