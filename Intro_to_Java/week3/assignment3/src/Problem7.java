import java.util.HashMap;
import java.util.Map;

public class Problem7 {
    /**
     * count how many times a word appears in a string
     * @param s given string that is unclean (includes punctuation)
     * @return Map of word and how many times it appears
     */
    static Map<String, Integer> wordCount(String s){
        Map<String, Integer> M= new HashMap<>();
        String n="";
        String temp="";
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i)>=97 && s.charAt(i)<=122) || (s.charAt(i)>=65 && s.charAt(i)<=90) || (s.charAt(i)>=48 && s.charAt(i)<=57)|| s.charAt(i)==32) // add all lower case letters, upper case letters, numbers, and spaces to a new string
                n+=s.charAt(i);
        }
        n+=" ";
        n=n.toLowerCase();
        for (int j = 0; j < n.length(); j++) {
            if (n.charAt(j)!=32)
                temp+=n.charAt(j);
            else if (n.charAt(j)==32){
                if (temp!="") {
                    if (M.containsKey(temp))
                        M.put(temp, M.get(temp) + 1);
                    else
                        M.put(temp, 1);
                }
                temp="";
            }
        }
        return M;
    }
}
