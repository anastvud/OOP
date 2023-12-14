package lab6;

public final class Staff extends User {
    Staff(int id, String status) {
        super(id, status);
    }

    @Override
    public double userFine(int day) {
        double fineSum = 0;
        for (LibraryItem book : this.books) {
            fineSum += book.computeFine(day);
        }
        for (LibraryItem film : this.films) {
            fineSum += film.computeFine(day);
        }
        for (LibraryItem journal : this.journals) {
            fineSum += journal.computeFine(day);
        }
        return fineSum;
    }
}
