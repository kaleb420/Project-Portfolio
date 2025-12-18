import java.util.ArrayList;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.jetbrains.annotations.NotNull;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the PairsSolution methods using a couple of example arrays.
 * It demonstrates how each method (HashSet, Two-Pointer, Brute Force) finds
 * pairs that sum to a specific target. It also highlights time complexity differences.
 */
public class PairsSolutionTest {

    /**
     * Utility method to compare all 3 algorithms given:
     *
     * @param arr the array
     * @param target the target
     * @param expected the expected Set of all possible pairs found
     * @param solver the solver that holds all 3 algorithms
     */
    private void assertAllAlgorithmsEqual(
            int[] arr,
            int target,
            Set<String> expected,
            PairsSolution solver) {
        assertEquals(expected, solver.findPairsHashSet(arr.clone(), target));
        assertEquals(expected, solver.findPairsTwoPointer(arr.clone(), target));
        assertEquals(expected, solver.findPairsBruteForce(arr.clone(), target));
    }

    /**
     * Utility method to print the contents of an int array.
     *
     * @param arr the array to print
     */
    private static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }


    // used to time a runnable
    private long timeIt(@NotNull Runnable r) {
        long startTime = System.currentTimeMillis();
        r.run();
        return System.currentTimeMillis() - startTime;
    }

    // runnable for findPairsHashSet
    private @NotNull Runnable runHashSet(
            int[] arr,
            int target,
            @NotNull Set<String> expected,
            @NotNull PairsSolution solver) {
        return () -> {
            solver.findPairsHashSet(arr, target);
        };
    }

    // runnable for findPairsTwoPointer
    private @NotNull Runnable runTwoPointer(
            int[] arr,
            int target,
            @NotNull Set<String> expected,
            @NotNull PairsSolution solver) {
        return () -> {
            solver.findPairsTwoPointer(arr, target);
        };
    }

    // runnable for findPairsBruteForce
    private @NotNull Runnable runBruteForce(
            int[] arr,
            int target,
            @NotNull Set<String> expected,
            @NotNull PairsSolution solver) {
        return () -> {
            solver.findPairsBruteForce(arr, target);
        };
    }





    @Test
    void test0() {
        PairsSolution solver = new PairsSolution();
        int[] arr = {2, 4, 3, 5, 7, 8, 9};
        int target = 9;
        Set<String> expected = Set.of("(2, 7)", "(4, 5)");
        assertAllAlgorithmsEqual(arr, target, expected, solver);
        compare(arr, target, expected);
    }

    @Test
    void test1() {
        PairsSolution solver = new PairsSolution();
        int[] arr = {1, 1, 2, 3, 4, 5};
        int target = 6;
        Set<String> expected = Set.of("(1, 5)", "(2, 4)");
        assertAllAlgorithmsEqual(arr, target, expected, solver);
        compare(arr, target, expected);
    }

    @Test
    void testNoValidPairs() {
        PairsSolution solver = new PairsSolution();
        int[] arr = {1,1,1,1};
        int target=6;
        Set<String> expected = Set.of();
        assertAllAlgorithmsEqual(arr, target, expected, solver);
        compare(arr, target, expected);
    }

    @Test
    void testWithNegatives() {
        PairsSolution solver = new PairsSolution();
        int[] arr = {-1,-1,5,5};
        int target=-2;
        Set<String> expected = Set.of("(-1, -1)");
        assertAllAlgorithmsEqual(arr, target, expected, solver);
        compare(arr, target, expected);
    }

    @Test
    void testLargeNumbers() {
        PairsSolution solver = new PairsSolution();
        int[] arr = {100000000, 500000000};
        int target=600000000;
        Set<String> expected = Set.of("(100000000, 500000000)");
        assertAllAlgorithmsEqual(arr, target, expected, solver);
        compare(arr, target, expected);
    }

    @Test
    void testVeryLongArray() {
        PairsSolution solver = new PairsSolution();
        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5};
        int target=10;
        Set<String> expected = Set.of("(5, 5)");
        assertAllAlgorithmsEqual(arr, target, expected, solver);
        compare(arr, target, expected);
    }

    void compare(int arr[], int target, Set<String> expected) {
        PairsSolution solver = new PairsSolution();
        long timeHS=timeIt(runHashSet(arr.clone(),target, expected, solver));
        long timePtr=timeIt(runTwoPointer(arr.clone(),target, expected, solver));
        long timeBF=timeIt(runBruteForce(arr.clone(),target, expected, solver));
        System.out.println("Array Length: " + arr.length);
        System.out.println("HashSet Solver: " + timeHS);
        System.out.println("Pointer Solver: " +timePtr);
        System.out.println("Brute Force Solver: " + timeBF);
    }
}
