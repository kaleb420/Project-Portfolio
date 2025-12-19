import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class demo {
    public static void main(String[] args) {
        TreeSet<Integer> s = new TreeSet<>();
        s.add(3);
        s.add(27);
        s.add(14);
        s.add(17);
        s.add(2);
        for (int x: s){
            System.out.println(x);
        }
    }
}
