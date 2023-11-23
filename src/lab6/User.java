package lab6;

public class User {
    private int id;
    private String status;
    public int books = 0;
    public int films = 0;
    public int journals = 0;


    User(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }
}
