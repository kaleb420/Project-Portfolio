import java.util.ArrayList;
import java.util.List;

public class LibraryProc {
    /**
     * adds a book to the list of library books
     * add the book only if its not in the library
     * @param books
     * @param title
     */
    public static void addBook(List<String> books, String title) {
        if (!books.contains(title))
            books.add(title);
    }

    /**
     * removes a book form the list of library books
     * remove the book only if it is in the library
     * @param books
     * @param title
     */
    public static void removeBook(List<String> books, String title){
        if (books.contains(title))
            books.remove(title);
    }

    /**
     * display the title of all books in the library
     * @param books
     */
    public static void displayBooks(List<String> books){
        for (String s: books){
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        List<String> books = new ArrayList<>();
        addBook(books, "And Then There Were None ");
        addBook(books, "Gone With the Wind");
        addBook(books, "Lord of the Rings");
        addBook(books, "1984");
        displayBooks(books);
        addBook(books, "1984");
        displayBooks(books);
    }
}
