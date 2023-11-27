package lab6;

public class Main {
    public static void main(String[] args) {
        Library library = new Library(
                "D:\\AGH\\2\\OOP\\src\\lab6\\books.csv",
                "D:\\AGH\\2\\OOP\\src\\lab6\\jlist.csv",
                "D:\\AGH\\2\\OOP\\src\\lab6\\movies.csv"
        );


        library.simulateYear();

//        library.borrowItem(0, 1, 10);
//        library.borrowItem(1, 1, 10);
//        library.borrowItem(1, 1, 10);
//        library.borrowItem(2, 1, 10);
//        library.borrowItem(3, 1, 10);
//        library.borrowItem(4, 1, 10);
//        library.borrowItem(5, 1, 10);
//        library.borrowItem(0, 1, 10);
//        library.borrowItem(5300, 1, 10);
//        library.borrowItem(1, 0, 10);
//        library.borrowItem(2, 0, 10);
        System.out.println("Statistics at the end of the year:");
//        library.showStatistics(364);
//        library.showLoans();

    }
}
