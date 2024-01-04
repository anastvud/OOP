package lab6;

public class Main {
    public static void main(String[] args) {
//        Library library = new Library(
//                "D:\\AGH\\2\\OOP\\src\\lab6\\books.csv",
//                "D:\\AGH\\2\\OOP\\src\\lab6\\jlist.csv",
//                "D:\\AGH\\2\\OOP\\src\\lab6\\movies.csv"
//        );


        Book book = new Book(1, "Sample Book", "Author", "Genre", "Publisher", 90, 9);

        // Task 1: Print declared methods of Book class
        book.printDeclaredMethods();

        // Task 2: Modify the value of the private field 'pagesNr'
        try {
            book.modifyPagesNr();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Task 3: List and inspect all fields with their access modifiers in the Book class
        book.inspectFields();

        // Task 4: Get the superclass for Book class and inspect its methods and fields
        book.inspectSuperclass();

        // Task 5: Find information about the constructors of the class
        book.inspectConstructors();

//        library.simulateYear();

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
//        System.out.println("Statistics at the end of the year:");
//        library.showStatistics(364);
//        library.showLoans();

    }
}
