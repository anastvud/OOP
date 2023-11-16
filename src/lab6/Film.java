package lab6;
import java.time.LocalDate;

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
