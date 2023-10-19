package lab3;

public class Client {

    int clientId;
    private String clientName;
    private static int count = 0;

    public Client() {
        this.clientName = "empty";
        System.out.println("Constructor for client" + count);
    }

    public Client(String name) {
        this.clientName = name;
        System.out.println("Constructor with parameter for client" + count);
    }

    {
        this.count++;
        this.clientId = this.count;
        System.out.println("Initializer block for client" + count);
    }

    private String getName() {
        return this.clientName;
    }

    private int getId() {
        return this.clientId;
    }

    private void print() {
        System.out.println("clientId " + this.clientId);
        System.out.println("clientName " + this.clientName);
    }

    private static int countClients() {
        return count;
    }


    public static void main(String[] args) {
        Client c1 = new Client();
        Client c2 = new Client("Mary");
        Client c3 = new Client("John");

        System.out.println("----------------------------");


        c3.print();
        System.out.println("Client id " + c2.getId());
        System.out.println("Client name " + c1.getName());

        System.out.println("----------------------------");

        Store store = new Store();

        store.add(c1);
        store.add(c2);
        store.add(c3);

        store.print();

        store.checkIfClientFound(2);
        store.checkIfClientFound(5);
    }
}
