package lab6;
import java.time.LocalDate;

public class Book extends LibraryItem{
    private String author;
    private String genre;
    private String publisher;

    public Book(String title, String author, String genre, String publisher, int libraryId) {
        super(title, libraryId);
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    @Override
    public int daysOverdue(LocalDate currentDate) {
        return 0;
    }

    @Override
    public boolean isOverdue(LocalDate currentDate) {
        return false;
    }

    @Override
    public double computeFine() {
        return 0.0;
    }
}
