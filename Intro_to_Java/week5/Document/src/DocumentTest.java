import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @org.junit.jupiter.api.Test
    void DocumentTests() {
        Document textDoc = new TextDocument();
        Document spreadDocument = new SpreadsheetDocument();
        Document document = new PresentationDocument();
        List<Document> l = new ArrayList<>();
        assertEquals(OptionalDouble.empty(), PrintingOffice.avgPages(l));
        l.add(textDoc);
        l.add(spreadDocument);
        l.add(document);
        assertEquals(56.66, PrintingOffice.avgPages(l).getAsDouble(),.01);
        assertEquals("Printing text document!", textDoc.print());
        assertEquals("Printing spreadsheet document!", spreadDocument.print());
        assertEquals("Printing the document!", document.print());
        assertEquals(100, textDoc.numberOfPages());
        assertEquals(50, spreadDocument.numberOfPages());
        assertEquals(20, document.numberOfPages());
    }
}