public class Problem1 {
    /**
     * uses a loop to replace every A with B
     * @param s string
     * @return formatted string
     */
    static String replaceABLoop(String s){
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='A')
                s=s.substring(0,i) + 'B' + s.substring(i+1);
        }
        return s;
    }
}
