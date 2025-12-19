import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Problem10Test {

    @Test
    void areParallelLists() {
        assertEquals(true, Problem10.areParallelLists(List.of(5, 10, 15, 20), List.of(20, 40, 60, 80)));
        assertEquals(true, Problem10.areParallelLists(List.of(100, 200, 300, 200), List.of(10, 20, 30, 20)));
        assertEquals(true, Problem10.areParallelLists(List.of(0, 0, 0, 0), List.of(20, 40, 60, 80)));
        assertEquals(false, Problem10.areParallelLists(List.of(0, 0, 0, 5), List.of(20, 40, 60, 80)));
        assertEquals(true, Problem10.areParallelLists(List.of(), List.of()));
        assertEquals(false, Problem10.areParallelLists(List.of(5, 10, 15, 20), List.of(27, 43, 58, 99)));
    }
}