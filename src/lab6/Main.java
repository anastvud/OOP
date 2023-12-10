package lab6;

public final class Main {
    public static void main(String[] args) {
        Library library = new Library(
                "D:\\AGH\\2\\OOP\\src\\lab6\\books.csv",
                "D:\\AGH\\2\\OOP\\src\\lab6\\jlist.csv",
                "D:\\AGH\\2\\OOP\\src\\lab6\\movies.csv"
        );
        library.simulateYear();
    }
}
