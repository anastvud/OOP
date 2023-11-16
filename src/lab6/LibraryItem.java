package lab6;
import java.time.LocalDate;

abstract class LibraryItem {
    private static int id;
    private String title;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public LibraryItem(String title, int id) {
        this.title = title;
        this.id = id;
        this.isReturned = false;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowDate = borrowedDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.isReturned = true;
    }

    public String getTitle() {
        return title;
    }

    public int getLibraryId() {
        return id;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public abstract int daysOverdue(LocalDate currentDate);

    public abstract boolean isOverdue(LocalDate currentDate);

    public abstract double computeFine();
}
