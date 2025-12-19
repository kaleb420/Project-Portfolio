public class Problem9 {
    /**
     * computes the total of each positive integer within a string
     * @param s String given
     * @return all integers added together
     */
    static int strSumNums(String s){
        int num=0;
        String n="";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                n += s.charAt(i);
                while (i + 1 < s.length() && s.charAt(i+1) >= 48 && s.charAt(i+1) <= 57) {
                    i += 1;
                    n += s.charAt(i);
                }
                num+=Integer.parseInt(n);
            }
            n="";
        }
        return num;
    }
}
