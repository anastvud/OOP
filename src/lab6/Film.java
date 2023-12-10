package lab6;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class Film extends LibraryItem {
    private String genre;
    private String director;
    private int year;
    private int runtimeMinutes;
    private double rating;

    public Film(int id, String title, String genre, String director, int year, int runtimeMinutes, double rating) {
        super(title, id);
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.runtimeMinutes = runtimeMinutes;
        this.rating = rating;
    }

    @Override
    public int daysOverdue(int currentDate) {
        if (!isReturned()) {
            int maxHoldingDays = 2; // Two days for all users
            int overdueDays = currentDate - this.borrowDate - maxHoldingDays;
            return Math.max(0, overdueDays);
        }
        return 0;    }

    @Override
    public boolean isOverdue(int currentDate) {
        return daysOverdue(currentDate) > 0;
    }

    @Override
    public double computeFine(int currentDate) {
        int maxHoldingDays = 2; // Two days for all users
        double overdueFineRate = 5.0; // $5.0 per day

        int overdueDays = daysOverdue(currentDate);
        if (overdueDays > maxHoldingDays) {
            return overdueDays * overdueFineRate;
        }
        return 0.0;    }
}
