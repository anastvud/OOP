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
    public int daysOverdue(LocalDate currentDate) {
        if (!isReturned()) {
            int maxHoldingDays = 14; // Two weeks for all users
            long daysOnLoan = ChronoUnit.DAYS.between(this.borrowDate, currentDate) - 1; // Exclude the borrowing day
            int overdueDays = (int) (daysOnLoan - maxHoldingDays);

            return Math.max(0, overdueDays);
        }
        return 0;
    }
    @Override
    public boolean isOverdue(LocalDate currentDate) {
        return daysOverdue(currentDate) > 0;
    }

    @Override
    public double computeFine() {
        int maxHoldingDays = 14; // Two weeks for all users
        double overdueFineRate = 0.5; // $0.5 per day

        int overdueDays = daysOverdue(LocalDate.now());
        if (overdueDays > maxHoldingDays) {
            return overdueDays * overdueFineRate;
        }
        return 0.0;    }
}
