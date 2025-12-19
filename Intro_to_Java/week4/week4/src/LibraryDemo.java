public class LibraryDemo {
    public static void main(String[] args) {
        LibraryOOP library = new LibraryOOP();
        library.addBook("And Then There Were None ");
        library.addBook("Gone With the Wind");
        library.addBook("Lord of the Rings");
        library.addBook("1984");
        library.displayBook();
    }
}