package lab6;

public final class Journal extends LibraryItem {
    private String eISSN;
    private String publisher;
    private String latestIssue;
    private String journalUrl;

    public Journal(int id, String title, String eISSN, String publisher, String latestIssue, String journalUrl) {
        super(title, id);
        this.eISSN = eISSN;
        this.publisher = publisher;
        this.latestIssue = latestIssue;
        this.journalUrl = journalUrl;
    }

    @Override
    public int daysOverdue(int currentDate) {
        if (!isReturned()) {
            int maxHoldingDays = (this.personStatus.equalsIgnoreCase("student")) ? 3 : 7; // 3 days for students, 7 days for staff
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
        int maxHoldingDays = (this.personStatus.equalsIgnoreCase("student")) ? 3 : 7; // 3 days for students, 7 days for staff
        double overdueFineRate = 2.0; // $2.0 per day

        int overdueDays = daysOverdue(currentDate);
        if (overdueDays > maxHoldingDays) {
            return overdueDays * overdueFineRate;
        }
        return 0.0;    }
}
