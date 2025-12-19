import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Problem1 {
    /**
     * orders words to their associated frequencies in a given string
     * @param s String
     * @return an ordered map associated to their frequencies in a string
     */
    static Map<String, Integer> frequencies(String s){
        String[] x = new String[]{};
        TreeMap<String, Integer> y = new TreeMap<String, Integer>();
        if (s.isEmpty())
            return y;
        x=s.split(" ");
        for (int i = 0; i < x.length; i++) {
            if (!y.containsKey(x[i]))
                y.put(x[i],1);
            else
                y.put(x[i], y.get(x[i])+1);
        }
        return y;
    }
}
