import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @org.junit.jupiter.api.Test
    void binarySearch(){
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(6);
        assertEquals(2, Search.binarySearch(l,3, Comparator.naturalOrder()));
        assertEquals(-1, Search.binarySearch(l,9, Comparator.naturalOrder()));
        assertEquals(-1, Search.binarySearch(l, 9));
        assertEquals(2, Search.binarySearch(l, 3));
    }
}