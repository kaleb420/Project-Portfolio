import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @org.junit.jupiter.api.Test
    void sort() {
        List<Integer> l = List.of(1,2,3,4,5,6);
        List<Integer> l2 = List.of(6,5,4,3,2,1);
        List<Integer> l3 = List.of();
        List<Integer> l4 = List.of(1);
        assertEquals(List.of(), Sort.sort(l3));
        assertEquals(List.of(1), Sort.sort(l4));
        assertEquals(List.of(1,2,3,4,5,6), Sort.sort(l));
        assertEquals(List.of(1,2,3,4,5,6), Sort.sort(l2));
    }
}