package lab6;
import java.time.LocalDate;

public abstract class LibraryItem {
    protected int id;
    protected String title;
    protected int borrowDate;
    protected int returnDate;
    protected boolean isReturned;
    protected String personStatus;
    protected int whoRented;

    public LibraryItem(String title, int id) {
        this.title = title;
        this.id = id;
        this.isReturned = true;
    }

    public void setBorrowedDate(int borrowedDate) {
        this.borrowDate = borrowedDate;
    }

    public void setReturnDate(int returnDate) {
        this.returnDate = returnDate;
        this.isReturned = true;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
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

    public abstract int daysOverdue(int currentDate);

    public abstract boolean isOverdue(int currentDate);

    public abstract double computeFine(int currentDate);
}
