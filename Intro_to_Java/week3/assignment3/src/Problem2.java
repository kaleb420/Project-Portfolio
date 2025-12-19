import java.util.LinkedList;
import java.util.Queue;

public class Problem2 {
    /**
     * given two sorted by increasing value array, determine the median value of those lists
     * @param A array of integers
     * @param B array of integers
     * @return the median of each array
     */
    static int median(int[] A, int[] B) {
        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();
        for (int i : A) {
            queueA.add(i);
        }
        for (int j : B) {
            queueB.add(j);
        }
        int median = 0;
        int index = 0;
        int[] combined = new int[A.length + B.length];
        while (!(queueA.isEmpty() || queueB.isEmpty())) {
            if (queueA.peek() <= queueB.peek())
                combined[index] = queueA.poll();
            else
                combined[index] = queueB.poll();
            index++;
        }
        if (queueA.isEmpty()) {
            while (!queueB.isEmpty()) {
                combined[index] = queueB.poll();
                index++;
            }
        }
        else {
            while (!queueA.isEmpty()) {
                combined[index] = queueA.poll();
                index++;
            }
        }
        for (int i : combined)
            System.out.println(i);
        if (combined.length==0)
            return median;
        if (combined.length%2==0){
            int firstNumber=combined[(combined.length/2)-1];
            int secondNumber=combined[(combined.length/2)];
            median= (int) Math.floor((firstNumber+secondNumber)/2);
        }
        else
            median= (int) Math.floor(combined[combined.length/2]);
        return median;
    }
}
