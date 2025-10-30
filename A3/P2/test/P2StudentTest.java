import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P2StudentTest {

    private final int match = 1;
    private final int mismatch = -1;
    private final int gap = -2;

    private int scoreOf(String ax, String ay, int match, int mismatch, int gap) {
        int s = 0;
        for (int i = 0; i < ax.length(); i++) {
            char a = ax.charAt(i), b = ay.charAt(i);
            if (a == '-' || b == '-') s += gap;
            else s += (a == b ? match : mismatch);
        }
        return s;
    }

    @Test
    void testEmptyVsString() {
        AlignmentStrategy nw = new NeedlemanWunsch();
        AlignmentResult r = nw.align("", "AGT");
        assertEquals(scoreOf(r.alignedX, r.alignedY, match, mismatch, gap), r.score);
        assertEquals("---", r.alignedX);
        assertEquals("AGT", r.alignedY);
    }

    @Test
    void testSingleCharacterStrings() {
        AlignmentStrategy nw = new NeedlemanWunsch();
        AlignmentResult r = nw.align("A", "T");
        assertEquals(1, r.alignedX.length());
        assertEquals(1, r.alignedY.length());
        assertEquals(scoreOf(r.alignedX, r.alignedY, match, mismatch, gap), r.score);
    }
}