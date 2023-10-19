package lab3;

import java.util.ArrayList;

public class Store {
    private ArrayList<Client> clients;

    public Store() {
        clients = new ArrayList<>();
        System.out.println("Constructor for store");

    }

    public void add (Client client) {
        this.clients.add(client);
        System.out.println("Added client to store");

    }

    public void print() {
        System.out.println("Clients: \n" + clients);
    }

    public Client find(int Id) {
        for (Client client : this.clients) {
            if (client.clientId == Id) {
                return client;
            }
        }
        return null;
    }

    public void checkIfClientFound(int Id) {
        Client c = this.find(Id);
        if (c != null) {
            System.out.println("Client found " + c.toString());
        } else {
            System.out.println("Client not found");
        }
    }
}
