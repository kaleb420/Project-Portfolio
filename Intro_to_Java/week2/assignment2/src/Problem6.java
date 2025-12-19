public class Problem6 {
    /**
     * implement the rules of wordle, if Wi=Gi append Wi to the output, if Gi is a member of W append an asterisk to the string, otherwise it is a dash
     * @param W Word
     * @param G Guess
     * @return string output corresponding to the rules described above
     */
    static String guessWord(String W, String G){
        String n="";
        if (W.length()!=G.length())
            return null;
        for (int i = 0; i < W.length(); i++) {
            if (W.charAt(i)==G.charAt(i))
                n+=G.charAt(i);
            else if (W.contains(G.charAt(i) + ""))
                n+="*";
            else
                n+="-";
        }
        return n;
    }
}
