import exceptions.EmptyStackE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackITest {
    @Test
    void testSimple () {
        StackPL<Integer> stackPL = new StackPL<>();
        int n = 10;

        for (int i = 0; i < n; i++) {
            stackPL.push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            try {
                assertEquals(i, stackPL.pop());
            }
            catch (EmptyStackE ex) {
                throw new Error("Internal bug: EmptyStackE should not be thrown in this test");
            }
        }
    }

    @Test
    void testComplicated(){
        StackPL<Integer> stackPL = new StackPL<>();
        int n=100;
        for (int i = 0; i < n; i++) {
            stackPL.push(i);
        }
        for (int i = n-1; i >= n-50 ; i--) {
            try {
                assertEquals(i, stackPL.pop());
            }
            catch (EmptyStackE ex) {
                throw new Error("Test failed");
            }
        }
        for (int i = n-50; i < n; i++) {
            stackPL.push(i);
        }
        for (int i = n-1; i >=0; i--) {
            try {
                assertEquals(i, stackPL.pop());
            }
            catch (EmptyStackE ex){
                throw new Error("Test failed");
            }
        }
    }
}
