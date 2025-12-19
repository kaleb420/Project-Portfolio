import java.util.ArrayList;
import java.util.List;

public class LibraryOOP {
    private List<String> books;

    /**
     * constructor
     */
    public LibraryOOP(){
        this.books= new ArrayList<>();
    }
    public void addBook(String title){
        if (!this.books.contains(title))
            this.books.add(title);
    }
    public void removeBook(String title){
        if (this.books.contains(title))
            this.books.remove(title);
    }
    public void displayBook(){
        for (String s: this.books){
            System.out.println(s);
        }
    }
}
