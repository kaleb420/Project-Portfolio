public class Problem2 {
    /** Find the string that is "in between" the others in terms of ascii values
     *
     * @param a first string
     * @param b second string
     * @param c third string
     * @return the string that is "in between"
     */
    static String middleString(String a, String b, String c){
        if ((a.compareTo(b)<0 && a.compareTo(c)>0) || (a.compareTo(b)>0 && a.compareTo(c)<0)){
            return a;
        }
        else if ((b.compareTo(a)<0 && b.compareTo(c)>0) || (b.compareTo(a)>0 && b.compareTo(c)<0)){
            return b;
        }
        else if ((c.compareTo(a)<0 && c.compareTo(b)>0) || (c.compareTo(a)>0 && c.compareTo(b)<0)){
            return c;
        }
        return a;
    }
}
