package lab6;

public abstract class LibraryItem {
    protected int id;
    protected String title;
    protected int borrowDate;
    protected int returnDate = -1;
    protected boolean isReturned = true;
    protected String personStatus;
    protected int whoRented;

    public LibraryItem(String title, int id) {
        this.title = title;
        this.id = id;
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
        return this.title;
    }

    public int getLibraryId() {
        return this.id;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public abstract int daysOverdue(int currentDate);

    public abstract boolean isOverdue(int currentDate);

    public abstract double computeFine(int currentDate);

    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Borrow Date: " + borrowDate + ", Return Date: " + returnDate;
    }
}
