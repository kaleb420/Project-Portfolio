import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellCheckerTest {

    @Test
    void spellCheck() {
        SpellChecker.spellCheck("/Users/kalebrobinson/Desktop/dictionary.txt", "/Users/kalebrobinson/Desktop/file3a.txt");
        SpellChecker.spellCheck("/Users/kalebrobinson/Desktop/empty.txt", "/Users/kalebrobinson/Desktop/file3a.txt");
        SpellChecker.spellCheck("/Users/kalebrobinson/Desktop/dictionary.txt", "/Users/kalebrobinson/Desktop/multiplelines.txt");
    }
}