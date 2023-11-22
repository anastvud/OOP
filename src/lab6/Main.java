package lab6;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadItems("D:\\AGH\\2\\OOP\\src\\lab6\\jlist.csv", 2);
        library.loadItems("D:\\AGH\\2\\OOP\\src\\lab6\\movies.csv", 3);
        library.loadItems("D:\\AGH\\2\\OOP\\src\\lab6\\books.csv", 1);
        library.addUser(new User("student"));
        library.addUser(new User("staff"));

        library.borrowItem(0, 1, 10);
        library.borrowItem(1, 1, 10);
        library.borrowItem(2, 1, 10);
        library.borrowItem(3, 1, 10);
        library.borrowItem(4, 1, 10);
        library.borrowItem(5, 1, 10);
        library.borrowItem(0, 1, 10);
        library.borrowItem(0, 1, 10);
        library.borrowItem(1, 0, 10);
        library.borrowItem(2, 0, 10);
    }
}
