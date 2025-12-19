public class Problem2 {
    /**
     * count how many adjacent pairs there are in a string
     * @param s string
     * @return amount of adjacent pairs
     */
    static int countAdjacentDuplicates(String s){
        int counter=0;
        for (int i = 0; i < s.length()-1; i++) {
            if (s.charAt(i)==s.charAt(i+1)){
                counter+=1;
            }
        }
        return counter;
    }
}
