import java.util.ArrayList;
import java.util.List;
public class Problem8 {
    /**
     * finds the first operator present in the string
     * @param ls list given
     * @return operator if found
     */
    private static String[] operator(List<String> ls){
        String[] arr= new String[2];
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).equals("*") || ls.get(i).equals("+") || ls.get(i).equals("-") || ls.get(i).equals("/")){
                arr[0]=ls.remove(i);
                arr[1]=String.valueOf(i);
                break;
            }
        }
        return arr;
    }
    /**
     * finds the first two numbers immediately before the operator
     * @param ls list given
     * @return array of numbers
     */
    private static double[] numbers(List<String> ls, int operatorIndex){
        double[] arr=new double[2];
        arr[0]=Double.parseDouble(ls.remove(operatorIndex-2));
        arr[1]=Double.parseDouble(ls.remove(operatorIndex-2));
        return arr;
    }
    /**
     * given a list of numbers and operators, use the first given operator at the first two numbers
     * i.e. {1,2,3,*,+} will result in 1+2*3=6
     * @param l list given
     * @return resulting number after applying the operator
     */
    static double postfixEvaluator(List<String> l){
        if (l.isEmpty())
            return 0;
        String[] operator= new String[2];
        double[] arr;
        int index=0;
        List<String> ls= new ArrayList<>(l);
        while (ls.size()!=1){
            operator=operator(ls);
            index=Integer.parseInt(operator[1]);
            arr=numbers(ls, index);
            if (operator[0].equals("+"))
                ls.add(index-2, String.valueOf(arr[0]+arr[1]));
            else if (operator[0].equals("-"))
                ls.add(index-2, String.valueOf(arr[0]-arr[1]));
            else if (operator[0].equals("*"))
                ls.add(index-2, String.valueOf(arr[0]*arr[1]));
            else if (operator[0].equals("/"))
                ls.add(index-2, String.valueOf(arr[0]/arr[1]));
        }
        return Double.parseDouble(ls.get(0));
    }
}