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
                int index = 0;
                System.out.println("Your Bookings:");
                for (Booking booking : bookings) {
                    int offeringId = booking.getOfferingId();
                    Offering offering = OfferingDAO.getOfferingDetailsById(offeringId);
                    System.out.println("Booking #" + (index + 1) + ": Offering ID: " + booking.getOfferingId() +
                            " - Client ID: " + booking.getId());
                    if (offering != null) {
                        System.out.println("Offering Name: " + offering.getOfferingName());
                        System.out.println("Location: " + offering.getLocation());
                        System.out.println("Date: " + offering.getDate());
                        System.out.println("Start Time: " + offering.getStartTime());
                        System.out.println("End Time: " + offering.getEndTime());
                        System.out.println("Available: " + (offering.isAvailable() ? "Yes" : "No"));
                        System.out.println("Group Offering: " + (offering.isGroup() ? "Yes" : "No"));
                        System.out.println("Capacity: " + offering.getCapacity());
                        System.out.println("Enrolled: " + offering.getEnrolled());
                    } else {
                        System.out.println("Booking #" + (index + 1) + ": Offering details not found for Offering ID: " + offeringId);
                    }

                    index++;
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

