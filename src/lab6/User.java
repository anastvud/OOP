package lab6;

public class User {
    private static int count = 0;
    private int id;
    private String status;
    public int books = 0;
    public int films = 0;
    public int journals = 0;


    User(String status) {
        this.id = count++;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }
}
