public class Problem2 {
    /**
     * checks if the string has a balanced amount of left and right parentheses
     * @param s string
     * @return true if there is an equal amount, false otherwise
     */
    static boolean isNestedParenthesesLoop(String s){
        int counter=0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='(')
                counter++;
            else if (s.charAt(i)==')' && counter>=0)
                counter--;
        }
        if (counter==0)
            return true;
        else
            return false;
    }
}
