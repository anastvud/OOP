package lab6;
import java.time.LocalDate;

public class Journal extends LibraryItem {
    private String eISSN;
    private String publisher;
    private String latestIssue;
    private String journalUrl;

    public Journal(String title, String eISSN, String publisher, String latestIssue, String journalUrl, int libraryId) {
        super(title, libraryId);
        this.eISSN = eISSN;
        this.publisher = publisher;
        this.latestIssue = latestIssue;
        this.journalUrl = journalUrl;
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
