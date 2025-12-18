import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class P3StudentTest {
    @Test
    void largeValues() {
        BinomialCoefficient dp = new DPBinomial();
        assertEquals(43758L, dp.binom(18,8));
        assertEquals(635376L, dp.binom(20,10));
        assertEquals(300540195L, dp.binom(25,12));
    }

    @Test
    void recursionLargeValues() {
        BinomialCoefficient rb = new RecursiveBinomial();
        assertEquals(43758L, rb.binom(18,8));
        assertEquals(635376L, rb.binom(20,10));
        assertEquals(300540195L, rb.binom(25,12));
    }
}
