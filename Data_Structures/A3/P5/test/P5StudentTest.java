import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P5StudentTest {

    @Test
    void singleWordLines() {
        RightJustifier just = new DPJustifier();
        List<String> words = List.of("one","two","three","four");
        int L = 5;
        double b = 1.0;

        List<String> lines = just.justify(words, L, b);

        // Each line must be exactly the word length + gaps (if any)
        for (String line : lines) {
            assertTrue(line.length() >= 3);
        }

        // Feasibility check
        double dpCost = JustifyTest.l1CostFromRendered(lines, words, L, b);
        assertTrue(Double.isFinite(dpCost));
    }

    @Test
    void singleWordParagraph() {
        RightJustifier just = new DPJustifier();
        List<String> words = List.of("Alone");
        int L = 10;
        double b = 2.0;

        List<String> lines = just.justify(words, L, b);

        // Should produce one line
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).length() >= words.get(0).length());

        double dpCost = JustifyTest.l1CostFromRendered(lines, words, L, b);
        assertTrue(Double.isFinite(dpCost));
    }

    @Test
    void emptyParagraph() {
        RightJustifier just = new DPJustifier();
        List<String> words = List.of();
        int L = 10;
        double b = 2.0;

        List<String> lines = just.justify(words, L, b);
        assertTrue(lines.isEmpty());
    }
}
