import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void countAdjacentDuplicates() {
        assertEquals(3, Problem2.countAdjacentDuplicates("aaabacabba"));
        assertEquals(5, Problem2.countAdjacentDuplicates("aabbbccc"));
        assertEquals(3, Problem2.countAdjacentDuplicates("aabbcc"));
        assertEquals(0, Problem2.countAdjacentDuplicates("abcde"));
        assertEquals(0, Problem2.countAdjacentDuplicates(""));
    }
}