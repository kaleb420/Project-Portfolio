public class TextDocument implements Document {
    private int pages;

    /**
     * constructor to initiate the value to 100
     */
    public TextDocument(){
        pages=100;
    }
    int getPages(){
        return pages;
    }

    /**
     * Overrides the number of pages present to the number of pages in this document
     * @return pages (equals 100)
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
        return "Printing text document!";
    }

}
