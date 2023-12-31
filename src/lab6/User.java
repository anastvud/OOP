package lab6;

import java.util.ArrayList;
import java.util.List;

abstract sealed class User implements IUser permits Student, Staff{
    private int id;
    private String status;
    public List<LibraryItem> books = new ArrayList<LibraryItem>();
    public List<LibraryItem> films = new ArrayList<LibraryItem>();
    public List<LibraryItem> journals = new ArrayList<LibraryItem>();


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

    @Override
    public String toString() {
        return "User: " + id + ", status: " + status;
    }
}
