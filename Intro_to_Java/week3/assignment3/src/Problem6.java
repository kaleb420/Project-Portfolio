import java.util.ArrayList;
import java.util.List;

public class Problem6 {
    /**
     * imitate the tokenize method
     * @param s string given
     * @param d delimiter to separate string
     * @return tokenized string
     */
    static List<String> tokenize(String s, char d){
        List<String> ls= new ArrayList<>();
        String x="";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==d){
                if (!x.equals(""))
                    ls.add(x);
                x="";
                while (i+1<s.length() && s.charAt(i+1)==d){
                    i+=1;
                }
                i+=1;
            }
            if (i<s.length())
                x+=s.charAt(i);
        }
        if (!x.equals(""))
            ls.add(x);
        return ls;
    }
}
