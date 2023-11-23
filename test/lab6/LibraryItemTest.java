package lab6;

import static org.junit.jupiter.api.Assertions.*;

class LibraryItemTest {
    @org.junit.jupiter.api.Test
    void daysOverdue1() {
        LibraryItem book = new Book(1, "Title", "Author", "Genre", "Publisher");
        book.setBorrowedDate(10);
        int today = 24;
        int expectedDaysOverdue = today - book.borrowDate - 14;
        assertEquals(expectedDaysOverdue, book.daysOverdue(today));
    }

    @org.junit.jupiter.api.Test
    void daysOverdue2() {
        LibraryItem journal = new Journal(1, "Title", "384", "Penguin", "20-12-2020", "url");
        journal.setBorrowedDate(10);
        journal.setPersonStatus("student");
        int today = 2;
        assertEquals(0, journal.daysOverdue(today));
    }

    @org.junit.jupiter.api.Test
    void isOverdue() {
        LibraryItem book = new Book(1, "Title", "Author", "Genre", "Publisher");
        book.setBorrowedDate(10);
        int today = 26;
        assertFalse(book.isOverdue(today));
    }

    @org.junit.jupiter.api.Test
    void computeFine() {
        LibraryItem book = new Book(1, "Title", "Author", "Genre", "Publisher");
        book.setBorrowedDate(10);
        int today = 24;
        double expectedFine = book.daysOverdue(today) * 0.50;
        assertEquals(expectedFine, book.computeFine(today));
    }
}