public class Problem1 {
    /**
     * helps the main function by using tail recursion
     * @param s String given
     * @param acc accumulator
     * @return true or false
     */
    static boolean isPalindromeTRHelper(String s, int acc) {
        int length = s.length();
        if (s.length()<=1) {
            if (acc==0)
                return true;
            else
                return false;
        }
        else {
            if (s.charAt(0)==s.charAt(length-1))
                return isPalindromeTRHelper(s.substring(1, length-1), acc);
            else
                return isPalindromeTRHelper(s.substring(1, length-1), acc+1);
        }
    }
    /**
     * determines if a string is a palindrome using tail recursion
     * @param s String
     * @return true if palindrome, false otherwise
     */
    static boolean isPalindromeTR(String s){
        if (isPalindromeTRHelper(s, 0))
            return true;
        else
            return false;
    }

    /**
     * determines if a string is a palindrome using a loop
     * @param s String given
     * @return true if palindrome, false otherwise
     */
    static boolean isPalindromeLoop(String s){
        while (s.length()>1){
            int length=s.length();
            if (s.charAt(0)==s.charAt(length-1))
                s=s.substring(1,length-1);
            else
                return false;
        }
        return true;
    }
}
