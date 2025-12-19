import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @Test
    void moreThanThree() {
        assertEquals(Set.of(1,3), Problem2.moreThanThree(new int[]{1,1,1,1,2,2,3,3,3,3}));
        assertEquals(Set.of(0), Problem2.moreThanThree(new int[]{0,0,0,0}));
        assertEquals(Set.of(), Problem2.moreThanThree(new int[]{0,0,0}));
        assertEquals(Set.of(), Problem2.moreThanThree(new int[]{}));

    }
}