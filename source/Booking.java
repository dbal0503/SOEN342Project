import java.time.LocalDateTime;

public class Booking {

    int id;
    int clientId;
    int offeringId;
    private Client client;
    private Offering offering;
    public Booking(int id, Client client, Offering offering) {
        this.id = id;
        this.client = client;
        this.offering = offering;
    }

    public Booking(int id, int clientId, int offeringId){
        this.clientId = clientId;
        this.offeringId = offeringId;
        this.id = id;
    }


    public Client getClient() {
        return client;
    }

    public Offering getOffering() {
        return offering;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

