package lab6;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        if (!isReturned()) {
            int maxHoldingDays = (this.personStatus.equalsIgnoreCase("student")) ? 3 : 7; // 3 days for students, 7 days for staff
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
        int maxHoldingDays = (this.personStatus.equalsIgnoreCase("student")) ? 3 : 7; // 3 days for students, 7 days for staff
        double overdueFineRate = 2.0; // $2.0 per day

        int overdueDays = daysOverdue(LocalDate.now());
        if (overdueDays > maxHoldingDays) {
            return overdueDays * overdueFineRate;
        }
        return 0.0;    }
}
