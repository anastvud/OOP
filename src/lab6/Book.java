package lab6;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book extends LibraryItem{
    private String author;
    private String genre;
    private String publisher;

    public Book(int id, String title, String author, String genre, String publisher) {
        super(title, id);
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    @Override
    public int daysOverdue(int currentDate) {
        if (!isReturned()) {
            int maxHoldingDays = 14; // Two weeks for all users
            int overdueDays = this.borrowDate - currentDate - maxHoldingDays;
            return Math.max(0, overdueDays);
        }
        return 0;
    }
    @Override
    public boolean isOverdue(int currentDate) {
        return daysOverdue(currentDate) > 0;
    }

    @Override
    public double computeFine(int currentDate) {
        int maxHoldingDays = 14; // Two weeks for all users
        double overdueFineRate = 0.5; // $0.5 per day

        int overdueDays = daysOverdue(currentDate);
        if (overdueDays > maxHoldingDays) {
            return overdueDays * overdueFineRate;
        }
        return 0.0;    }
}
