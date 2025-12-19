public class Problem1 {
    /**
     * count how many times chicken is in s
     * @param s string
     * @return int of how many times chicken appears in the s
     */
    static int chickenCounter(String s){
        int counter=0;
        for (int i = 0; i < s.length()-6; i++) {
            if (s.substring(i,i+7).equals("chicken")){
                counter+=1;
                s=s.substring(0,i) + s.substring(i+7);
                i=-1;
            }
        }
        return counter;
    }
}