public class SpreadsheetDocument implements Document{
    private int pages;

    /**
     * constructor to initialize the page count to 50
     */
    public SpreadsheetDocument(){
        pages=50;
    }
    int getPages(){
        return pages;
    }

    /**
     * Overrides the number of pages present to the number of pages in this document
     * @return pages (equals 50)
     */
    @Override
    public int numberOfPages() {
        return pages;
    }

    /**
     * Override the print function in the interface
     * @return designated string
     */
    @Override
    public String print(){
        return "Printing spreadsheet document!";
    }

}
