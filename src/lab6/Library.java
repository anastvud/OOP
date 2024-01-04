package lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;


public class Library {
    public List<LibraryItem> libraryItems = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private int currentId = 0;
    private int c = 0;


    public void loadItems(String path, int type) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                switch (type) {
                    case 1:
                        String bookTitle = values[0];
                        String bookAuthor = values[1];
                        String bookGenre = values[2];
                        String bookPublisher;
                        if (values.length == 4) {
                            bookPublisher = values[3];
                        } else {
                            bookPublisher = "";
                        }
                        libraryItems.add(new Book(currentId, bookAuthor, bookTitle, bookGenre, bookPublisher, 100, 9));
                        break;
                    case 2:
                        String journalTitle = values[0];
                        String journaleISSN = values[3];
                        String journalpublisher = values[4];
                        String journalissueNumber = values[6];
                        String journalUrl;
                        if (values.length == 13) {
                            journalUrl = values[12];
                        } else {
                            journalUrl = "";
                        }
                        libraryItems.add(new Journal(currentId, journalTitle, journaleISSN, journalpublisher, journalissueNumber, journalUrl));
                        break;
                    case 3:
                        String filmTitle = values[1];
                        String filmGenre = values[2];
                        int i = 0;
                        if (values[3].startsWith("\"")) {
                            while (!values[3 + i].endsWith("\"")) {
                                i++;
                            }
                        }
                        String filmDirector = values[4 + i];
                        int filmYear = Integer.parseInt(values[6 + i]);
                        int filmRuntime = Integer.parseInt(values[7 + i]);
                        double filmRating = Double.parseDouble(values[8 + i]);
                        libraryItems.add(new Film(currentId, filmTitle, filmGenre, filmDirector, filmYear, filmRuntime, filmRating));
                }
                currentId++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    Library(String pathBooks, String pathJournal, String pathFilms) {
        // load items
//        loadItems(pathBooks, 1);
//        loadItems(pathJournal, 2);
//        loadItems(pathFilms, 3);

        // create users
        for (int i = 0; i < 100; i++) {
            if (i < 80) users.add(new User(i, "Student"));
            else users.add(new User(i, "Faculty"));
        }
    }


    public void borrowItem(int id, int clientId, int currentDate) {
        LibraryItem item = libraryItems.get(id);
        if (users.get(clientId).getStatus().equalsIgnoreCase("student") && item instanceof Book && users.get(clientId).books.size() >= 3) {
//            System.out.println("Client has too many books");
            return;
        }
        if (users.get(clientId).getStatus().equalsIgnoreCase("student") && item instanceof Journal && users.get(clientId).journals.size() >= 3) {
//            System.out.println("Client has too many magazines");
            return;
        }
        if (users.get(clientId).getStatus().equalsIgnoreCase("student") && item instanceof Film && !users.get(clientId).films.isEmpty()) {
//            System.out.println("Client has too many films");
            return;
        }
        if (item.isReturned) {
            item.whoRented = users.get(clientId).getId();
            item.borrowDate = currentDate;
            item.isReturned = false;
            item.personStatus = users.get(clientId).getStatus();
            switch (item) {
                case Book ignored -> users.get(clientId).books.add(item);
                case Journal ignored -> users.get(clientId).journals.add(item);
                case Film ignored -> users.get(clientId).films.add(item);
                case null, default -> {
                }
            }
        }
    }

    public void showLoans() {
        System.out.println("Items on Loan:");
        for (LibraryItem item : libraryItems) {
            if (!item.isReturned()) {
                System.out.println(item);
            }
        }
    }

    public void showStatistics(int currentDate) {
        int totalLent = 0;
        int totalOnLoan = 0;
        int totalOverdue = 0;
        double totalFineCollected = 0;

        for (LibraryItem item : libraryItems) {
            if (!item.isReturned()) {
                totalLent++;

                if (item.isOverdue(currentDate)) {
                    totalOverdue++;
                    totalFineCollected += item.computeFine(currentDate);
                }
            }
        }

        totalOnLoan = totalLent - totalOverdue;

        System.out.println("\nLibrary Statistics:");
        System.out.println("Total items lent: " + totalLent);
        System.out.println("Total items on loan: " + totalOnLoan);
        System.out.println("Total overdue items: " + totalOverdue);
        System.out.println("Total fine collected: " + totalFineCollected);
    }

    public void returnItem(int userId) {
        User currUser = users.get(userId);
        if (!currUser.films.isEmpty()) {
            LibraryItem borrowedFilm = libraryItems.get(currUser.films.get(0).getLibraryId());
            borrowedFilm.isReturned = true;
            currUser.films.remove(borrowedFilm);
        } else if (!currUser.journals.isEmpty()) {
            LibraryItem borrowedJournal = libraryItems.get(currUser.journals.get(0).getLibraryId());
            borrowedJournal.isReturned = true;
            currUser.journals.remove(borrowedJournal);
        } else if (!currUser.books.isEmpty()) {
            LibraryItem borrowedBook = libraryItems.get(currUser.books.get(0).getLibraryId());
            borrowedBook.isReturned = true;
            currUser.books.remove(borrowedBook);
        }
    }

    public void dailyOperation(int day) {
//        Random rand = new Random();

        // 5 books
        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            int userId = rand.nextInt(100);
            int itemId = rand.nextInt(libraryItems.size());
            while (!(libraryItems.get(itemId) instanceof Book)) {
                itemId = rand.nextInt(libraryItems.size());
            }
            borrowItem(itemId, userId, day);
        }

        // 5 films
        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            int userId = rand.nextInt(100);
            int itemId = rand.nextInt(libraryItems.size());
            while (!(libraryItems.get(itemId) instanceof Film)) {
                itemId = rand.nextInt(libraryItems.size());
            }
            borrowItem(itemId, userId, day);
        }

        // 8 journals
        for (int i = 0; i < 8; i++) {
            Random rand = new Random();
            int userId = rand.nextInt(100);
            int itemId = rand.nextInt(libraryItems.size());
            while (!(libraryItems.get(itemId) instanceof Journal)) {
                itemId = rand.nextInt(libraryItems.size());
            }
            borrowItem(itemId, userId, day);
        }


        // Simulate returning
        for (int i = 0; i < 2; i++) {
            Random rand = new Random();

            int userId = rand.nextInt(100);
            User u = users.get(userId);
            while (!u.films.isEmpty() || !u.books.isEmpty() || !u.journals.isEmpty()) {
                userId = rand.nextInt(100);
                u = users.get(userId);
            }

//            returnItem(u.getId());

        }
        showStatistics(day);
        System.out.println(c++);
    }

    public void simulateYear() {
        for (int i = 0; i < 365; i++) {
            dailyOperation(i);
        }

        // Show final statistics at the end of the year
//        showStatistics(365);
    }


}
