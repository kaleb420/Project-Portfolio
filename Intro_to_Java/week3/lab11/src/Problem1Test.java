import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {


    @org.junit.jupiter.api.Test
    void mergeStacks() {
        Stack<Integer> test1= new Stack<>();
        test1.push(10);
        test1.push(20);
        test1.push(30);
        test1.push(40);
        test1.push(50);

        Stack<Integer> test2= new Stack<>();
        test2.push(5);
        test2.push(15);
        test2.push(25);

        Stack<Integer> test3= new Stack<>();
        test3.push(5);
        test3.push(15);

        Stack<Integer> test4= new Stack<>();
        test4.push(30);
        test4.push(20);
        test4.push(10);

        Stack<Integer> test5= new Stack<>();
        test5.push(25);
        test5.push(15);
        test5.push(5);

        Stack<String> stringTest1= new Stack<>();
        stringTest1.push("d");
        stringTest1.push("a");

        Stack<String> stringTest2= new Stack<>();
        stringTest2.push("l");
        stringTest2.push("B");

        assertEquals(List.of(50,25,40,15,30,5,20,10), Problem1.mergeStacks(test1, test2));
        assertEquals(List.of(50,15,40,5,30,20,10), Problem1.mergeStacks(test1, test3));
        assertEquals(List.of(10,5,20,15,30,25), Problem1.mergeStacks(test5, test4));
        assertEquals(List.of("B", "a", "l", "d"), Problem1.mergeStacks(stringTest1, stringTest2));
    }
}