import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoinChangeTest {

    @Test
    void testBottomUp() {
        int[] denominations = {1, 5, 10, 25};
        assertEquals(1, CoinChange.minChange(1, denominations));
        assertEquals(1, CoinChange.minChange(5, denominations));
        assertEquals(2, CoinChange.minChange(6, denominations));
        assertEquals(4, CoinChange.minChange(4, denominations));
    }

    @Test
    void testTopDown() {
        int[] denominations = {1, 5, 10, 25};
        assertEquals(1, CoinChangeTopDown.minChangeMemo(1, denominations));
        assertEquals(1, CoinChangeTopDown.minChangeMemo(5, denominations));
        assertEquals(2, CoinChangeTopDown.minChangeMemo(6, denominations));
        assertEquals(4, CoinChangeTopDown.minChangeMemo(4, denominations));
    }
}
