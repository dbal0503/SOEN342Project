import java.time.LocalDateTime;
import java.util.*;
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

    public static void viewBookings() {
        if (Session.hasSession()) { //Check f user has session
            Client client = (Client) Session.user;
            List<Booking> bookings = BookingDAO.getBookingsByClient(client.getUniqueId());

            if (bookings.isEmpty()) {
                System.out.println("You have no bookings.");
            } else {
                System.out.println("Your Bookings:");
                for (Booking booking : bookings) {
                    System.out.println("Booking ID: " + booking.getId());
                    System.out.println("Offering ID: " + booking.getOfferingId());
                    System.out.println("--------------------------");
                }
            }
        } else {
            System.out.println("You must log in first to view bookings.");
        }
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

    public int getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(int offeringId) {
        this.offeringId = offeringId;
    }
}

