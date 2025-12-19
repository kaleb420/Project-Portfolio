public class Problem1 {
    /**
     * replaces any A in s with B
     * @param s string given
     * @return new string with A's and B's swapped
     */
    static String replaceAB(String s){
        if (s.length()==0)
            return s;
        else {
            if (s.charAt(0) =='A')
                return 'B' + replaceAB(s.substring(1));
            else
                return s.charAt(0) + replaceAB(s.substring(1));
        }
    }
}

