package lab6;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Book extends LibraryItem{
    private String author;
    private String genre;
    private String publisher;
    private int pagesNr = 100;


    private Book(int id, String title, String author, String genre, String publisher, int pagesNr) {
        super(title, id);
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.pagesNr = pagesNr;
    }

    public Book(int id, String title, String author, String genre, String publisher, int pagesNr, int extraField) {
        this(id, title, author, genre, publisher, pagesNr);
    }

    public void printDeclaredMethods() {
        Class<?> bookClass = this.getClass();

        Method[] methods = bookClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method Name: " + method.getName());
            System.out.println("Access Modifier: " + method.getModifiers());
            System.out.println("Return Type: " + method.getReturnType());
            System.out.println("--------------");
        }
    }

    public void modifyPagesNr() throws NoSuchFieldException, IllegalAccessException {
        Class<?> bookClass = this.getClass();
        Field pagesNrField = bookClass.getDeclaredField("pagesNr");
        pagesNrField.setAccessible(true);
        System.out.println("Current pagesNr: " + pagesNrField.get(this));
        pagesNrField.set(this, 150);
        System.out.println("Modified pagesNr: " + pagesNrField.get(this));
    }

    public void inspectFields() {
        Class<?> bookClass = this.getClass();

        Field[] fields = bookClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field Name: " + field.getName());
            System.out.println("Access Modifier: " + field.getModifiers());
            System.out.println("--------------");
        }
    }

    public void inspectSuperclass() {
        Class<?> superclass = this.getClass().getSuperclass();
        System.out.println("Superclass Name: " + superclass.getName());

        Method[] superclassMethods = superclass.getDeclaredMethods();
        for (Method method : superclassMethods) {
            System.out.println("Superclass Method Name: " + method.getName());
            System.out.println("Access Modifier: " + method.getModifiers());
            System.out.println("Return Type: " + method.getReturnType());
            System.out.println("--------------");
        }

        Field[] superclassFields = superclass.getDeclaredFields();
        for (Field field : superclassFields) {
            System.out.println("Superclass Field Name: " + field.getName());
            System.out.println("Access Modifier: " + field.getModifiers());
            System.out.println("--------------");
        }
    }

    public void inspectConstructors() {
        Class<?> bookClass = this.getClass();

        Constructor<?>[] constructors = bookClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor Name: " + constructor.getName());
            System.out.println("Parameter Count: " + constructor.getParameterCount());

            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("Parameter Type: " + parameterType.getName());
            }

            System.out.println("--------------");
        }
    }

    @Override
    public int daysOverdue(int currentDate) {
        if (!isReturned()) {
            int maxHoldingDays = 14; // Two weeks for all users
            int overdueDays = currentDate - this.borrowDate - maxHoldingDays;
            return Math.max(0, overdueDays);
        }
        return 0;
    }
    @Override
    public boolean isOverdue(int currentDate) {
        return daysOverdue(currentDate) > 0;
    }

    @Override
    public double computeFine(int currentDate) {
        int maxHoldingDays = 14; // Two weeks for all users
        double overdueFineRate = 0.5; // $0.5 per day

        int overdueDays = daysOverdue(currentDate);
        if (overdueDays > maxHoldingDays) {
            return overdueDays * overdueFineRate;
        }
        return 0.0;    }
}
