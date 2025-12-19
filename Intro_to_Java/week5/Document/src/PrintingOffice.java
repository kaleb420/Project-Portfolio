import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class PrintingOffice {
    /**
     * compute the average pages from the given documents
     * @param lodocs documents given
     * @return average of the documents
     */
    public static OptionalDouble avgPages(List<Document> lodocs){
        double sum=0;
        if (lodocs.isEmpty())
            return OptionalDouble.empty();
        for (int i = 0; i < lodocs.size(); i++) {
            sum+=lodocs.get(i).numberOfPages();
        }
        return OptionalDouble.of(sum/lodocs.size());
    }

    /**
     * prints the print statement of the corresponding document
     * @param documents to be printed
     */
    public static void printDocuments(List<Document> documents){
        for (int i = 0; i < documents.size(); i++) {
            System.out.println(documents.get(i).print());
        }
    }
}
