import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class ApplyOperationsTest {

    @org.junit.jupiter.api.Test
    void applyOperations() {
        ApplyOperations.applyOperations("/Users/kalebrobinson/Desktop/test1.txt", "/Users/kalebrobinson/Desktop/test1outtxt");
        ApplyOperations.applyOperations("testadd.in", "testadd.out");
        ApplyOperations.applyOperations("testsub.in", "testsub.out");
        ApplyOperations.applyOperations("testmultiply.in", "testmultiply.out");
        ApplyOperations.applyOperations("testdivide.in", "testdivide.out");
        assertThrows(InputMismatchException.class, ()->ApplyOperations.applyOperations("filedouble.txt", "failure.txt"));
        assertThrows(ArithmeticException.class, ()->ApplyOperations.applyOperations("file0.txt", "failure.txt"));
        assertThrows(UnsupportedOperationException.class, ()->ApplyOperations.applyOperations("file^.txt", "failure.txt"));
    }
}