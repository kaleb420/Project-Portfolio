import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeTest {

    @Test
    void capitalize() throws IOException {
        Capitalize.capitalize("/Users/kalebrobinson/Desktop/file2a.txt");
        Capitalize.capitalize("/Users/kalebrobinson/Desktop/multiplelines.txt");
        Capitalize.capitalize("/Users/kalebrobinson/Desktop/empty.txt");
        assertThrows(FileNotFoundException.class, ()->Capitalize.capitalize("doesntexist.in"));
    }
}