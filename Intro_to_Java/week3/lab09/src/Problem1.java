import java.util.ArrayList;
import java.util.List;

public class Problem1 {
    /**
     * Assigns letter grade to given exam based upon percent grade
     * @param G list of grades
     * @param L list of letter grades
     * @param C cut off score as an array
     * @return String list where the ith element returned is the ith element of G
     */
    static List<String> assignGrades(List<Double> G, List<String> L, double[] C){
        List<String> letterGrades= new ArrayList<>();
        for (int i = 0; i < G.size(); i++) {
            for (int j = 0; j < C.length; j++) {
                if (G.get(i)>=C[j]){
                    letterGrades.add(L.get(j));
                    break;
                }
                else if (G.get(i)<=C[j] && j==C.length-1){
                    letterGrades.add(L.get(j+1));
                }
            }
        }
        return letterGrades;
    }
}
