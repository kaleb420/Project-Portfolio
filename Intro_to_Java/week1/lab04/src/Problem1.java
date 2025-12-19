public class Problem1 {
    /** delete c or d if it is in s
     *
     * @param s string given
     * @param c character to be removed
     * @param d character to be removed
     * @return the formatted string
     */
    static String popChars(String s, char c, char d){
        if (s.length()>0 && s.charAt(0)==c){
            s=s.substring(1);
            if (s.length()>0 && s.charAt(0)==d){
                return s.substring(1);
            }
            return s;
        }
        else if (s.length()>1 && s.charAt(1)==d){
            return s.charAt(0) + s.substring(2);
        }
        return s;
    }
}
