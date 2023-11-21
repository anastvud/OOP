package lab6;
import java.time.LocalDate;

public abstract class LibraryItem {
    protected int id;
    protected String title;
    protected LocalDate borrowDate;
    protected LocalDate returnDate;
    protected boolean isReturned;
    protected String personStatus;
    protected int whoRented;

    public LibraryItem(String title, int id) {
        this.title = title;
        this.id = id;
        this.isReturned = true;
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
