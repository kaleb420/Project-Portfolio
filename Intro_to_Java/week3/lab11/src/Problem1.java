import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Problem1 {
    /**
     * given two stacks creates a new stack with the combined elements in reverse order, alternating between s1 and s2
     * @param s1 stack 1 containing some data type
     * @param s2 stack 2 containing some data type
     * @return a generic stack of some data type
     * @param <T> generic data type
     */
    static <T> Stack<T> mergeStacks(Stack<T> s1, Stack<T> s2) {
        Stack<T> x1 = new Stack<>();
        Stack<T> x2 = new Stack<>();
        for (T i : s1){
            x1.add(i);
        }
        for (T j : s2){
            x2.add(j);
        }
        Stack<T> newOrder = new Stack<>();
        if (x1.size()>=x2.size()) {
            newOrder.push(x1.pop());
                while (!x2.isEmpty()) {
                    newOrder.push(x1.pop());
                    newOrder.push(x2.pop());
                }
                while (!x1.isEmpty()) {
                    newOrder.push(x1.pop());
                }
            }
        else {
            newOrder.push(x2.pop());
                while (!x1.isEmpty()) {
                    if (!x2.isEmpty()){
                        newOrder.push(x2.pop());
                        newOrder.push(x1.pop());
                    }
                    else if (x2.isEmpty())
                        newOrder.push(x1.pop());
                }
                while (!x2.isEmpty()) {
                    newOrder.push(x2.pop());
                }
        }
        return newOrder;
    }
}
