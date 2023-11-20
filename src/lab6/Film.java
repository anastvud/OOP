package lab6;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Film extends LibraryItem {
    private String genre;
    private String director;
    private int year;
    private int runtimeMinutes;
    private double rating;

    public Film(String title, String genre, String director, int year, int runtimeMinutes, double rating, int libraryId) {
        super(title, libraryId);
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.runtimeMinutes = runtimeMinutes;
        this.rating = rating;
    }

    @Override
    public int daysOverdue(LocalDate currentDate) {
        if (!isReturned()) {
            int maxHoldingDays = 2; // Two days for all users
            long daysOnLoan = ChronoUnit.DAYS.between(this.borrowDate, currentDate) - 1; // Exclude the borrowing day
            int overdueDays = (int) (daysOnLoan - maxHoldingDays);

            return Math.max(0, overdueDays);
        }
        return 0;    }

    @Override
    public boolean isOverdue(LocalDate currentDate) {
        return daysOverdue(currentDate) > 0;
    }

    @Override
    public double computeFine() {
        int maxHoldingDays = 2; // Two days for all users
        double overdueFineRate = 5.0; // $5.0 per day

        int overdueDays = daysOverdue(LocalDate.now());
        if (overdueDays > maxHoldingDays) {
            return overdueDays * overdueFineRate;
        }
        return 0.0;    }
}
