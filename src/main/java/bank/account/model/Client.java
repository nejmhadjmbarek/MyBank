package bank.account.model;

public class Client {

    private int    idClient;
    private String name;

    public Client(int idClient, String name) {
        super();
        this.setIdClient(idClient);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

}
