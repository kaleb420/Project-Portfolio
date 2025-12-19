public class Problem7 {
    /**
     * imitate the substring function, given a string return the new string from index a to index b
     * @param s String given
     * @param a index 1
     * @param b index 2
     * @return substring of string based on index 1 and index 2
     */
    static String substring(String s, int a, int b){
        if (a<0 || b<0)
            return null;
        if (a>b)
            return null;
        String n="";
        if (s.length()<b)
            return null;
        for (int i=a; i<b; i++) {
            n+=s.charAt(i);
        }
        return n;
    }
}
