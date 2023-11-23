package lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;


public class Library {
    private List<LibraryItem> libraryItems = new ArrayList<LibraryItem>();
    private List<User> users = new ArrayList<User>();
    private int currentId = 0;


    public void loadItems(String path, int type){
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
                        libraryItems.add(new Book(currentId, bookAuthor, bookTitle, bookGenre, bookPublisher));
                        break;
                    case 2:
                        String journalTitle = values[0];
                        String journaleISSN = values[3];
                        String journalpublisher = values[4];
                        String journalissueNumber = values[6];
                        String journalUrl;
                        if(values.length == 13){
                            journalUrl = values[12];
                        }else{
                            journalUrl = "";
                        }
                        libraryItems.add(new Journal(currentId, journalTitle, journaleISSN, journalpublisher, journalissueNumber, journalUrl));
                        break;
                    case 3:
                        String filmTitle = values[1];
                        String filmGenre = values[2];
                        int i = 0;
                        if(values[3].startsWith("\"")){
                            while(!values[3+i].endsWith("\"")){
                                i++;
                            }
                        }
                        String filmDirector = values[4+i];
                        int filmYear = Integer.parseInt(values[6+i]);
                        int filmRuntime = Integer.parseInt(values[7+i]);
                        double filmRating = Double.parseDouble(values[8+i]);
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
        loadItems(pathBooks, 1);
        loadItems(pathJournal, 2);
        loadItems(pathFilms, 3);

        // create users
        for (int i = 0; i < 100; i++) {
            if (i < 80) users.add(new User(i, "Student"));
            else users.add(new User(i, "Faculty"));
        }
    }


    public void borrowItem(int id, int clientId, int currentDate){
        LibraryItem item = libraryItems.get(id);
        if(users.get(clientId).getStatus().equalsIgnoreCase("student") && item instanceof Book && users.get(clientId).books >= 3){
            System.out.println("Client has too many books");
            return;
        }
        if(users.get(clientId).getStatus().equalsIgnoreCase("student") && item instanceof Journal && users.get(clientId).journals >= 3){
            System.out.println("Client has too many magazines");
            return;
        }
        if(users.get(clientId).getStatus().equalsIgnoreCase("student") && item instanceof Film && users.get(clientId).films >= 1){
            System.out.println("Client has too many films");
            return;
        }
        if(item.isReturned){
            item.whoRented = users.get(clientId).getId();
            item.borrowDate = 0;
            if(item instanceof Book){
                users.get(clientId).books++;
            }else if(item instanceof Journal){
                users.get(clientId).journals++;
            }else if(item instanceof Film){
                users.get(clientId).films++;
            }
        } else {
            System.out.println("Item is not available");
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

        for (LibraryItem item : libraryItems) {
            if (!item.isReturned()) {
                totalLent++;

                if (item.isOverdue(currentDate)) {
                    totalOverdue++;
                }
            }
        }

        totalOnLoan = totalLent - totalOverdue;

        System.out.println("\nLibrary Statistics:");
        System.out.println("Total items lent: " + totalLent);
        System.out.println("Total items on loan: " + totalOnLoan);
        System.out.println("Total overdue items: " + totalOverdue);
//        System.out.println("Total fine collected: " + totalFineCollected);
    }
























































    // Method to borrow any item on any day
//    public void borrowItem(int userId, double alphaBook, double alphaJournal, double alphaFilm, double beta) {
//        Random rand = new Random();
//        double randomNum = rand.nextDouble();
//
//        if (users.contains(userId)) {
//            if (randomNum < alphaBook) {
//                // Borrow a book
//                LibraryItem book = getRandomItem("Book");
//                if (book != null && !book.isReturned()) {
//                    book.setBorrowedDate(LocalDate.now());
//                    int maxHoldingDays = book.getMaxHoldingDays(getUserType(userId));
//                    book.setReturnDate(LocalDate.now().plusDays(maxHoldingDays));
//                }
//            } else if (randomNum < alphaBook + alphaJournal) {
//                // Borrow a journal
//                LibraryItem journal = getRandomItem("Journal");
//                if (journal != null && !journal.isReturned()) {
//                    journal.setBorrowedDate(LocalDate.now());
//                    int maxHoldingDays = journal.getMaxHoldingDays(getUserType(userId));
//                    journal.setReturnDate(LocalDate.now().plusDays(maxHoldingDays));
//                }
//            } else if (randomNum < alphaBook + alphaJournal + alphaFilm) {
//                // Borrow a film
//                LibraryItem film = getRandomItem("Film");
//                if (film != null && !film.isReturned()) {
//                    film.setBorrowedDate(LocalDate.now());
//                    int maxHoldingDays = film.getMaxHoldingDays(getUserType(userId));
//                    film.setReturnDate(LocalDate.now().plusDays(maxHoldingDays));
//                }
//            }
//        }
//
//        // Simulate return with probability beta
//        if (rand.nextDouble() < beta) {
//            returnRandomItem(userId);
//        }
//    }
//
//    // Helper method to get a random item of a specific type
//    private LibraryItem getRandomItem(String itemType) {
//        List<LibraryItem> availableItems = new ArrayList<>();
//        for (LibraryItem item : libraryItems) {
//            if (!item.isReturned() && item.getItemType().equalsIgnoreCase(itemType)) {
//                availableItems.add(item);
//            }
//        }
//
//        if (!availableItems.isEmpty()) {
//            Random rand = new Random();
//            return availableItems.get(rand.nextInt(availableItems.size()));
//        }
//
//        return null;
//    }
//
//    // Helper method to get the user type (assuming some logic here)
//    private String getUserType(String userId) {
//        // Example logic, you may adapt this based on your user management
//        return (userId.startsWith("student")) ? "student" : "staff";
//    }
//
//    // Helper method to return a random item for a specific user
//    private void returnRandomItem(String userId) {
//        List<LibraryItem> borrowedItems = new ArrayList<>();
//        for (LibraryItem item : libraryItems) {
//            if (!item.isReturned() && item.getBorrowerId().equalsIgnoreCase(userId)) {
//                borrowedItems.add(item);
//            }
//        }
//
//        if (!borrowedItems.isEmpty()) {
//            Random rand = new Random();
//            LibraryItem returnedItem = borrowedItems.get(rand.nextInt(borrowedItems.size()));
//            returnedItem.setReturnDate(LocalDate.now());
//        }
//    }
}
