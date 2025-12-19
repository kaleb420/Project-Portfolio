public class PresentationDocument implements Document{
    private int pages;

    /**
     * constructor to initiate the pages value
     */
    public PresentationDocument(){
        pages=20;
    }

    /**
     * Overrides the number of pages present to the number of pages in this document
     * @return pages (equals 20)
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
    public String print() {
        return Document.super.print();
    }
}
