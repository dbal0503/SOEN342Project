import java.time.LocalDateTime;

public class Bookings {
    private Client client;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

    public Bookings(Client client, String name, LocalDateTime startTime, LocalDateTime endTime, String location) {
        this.client = client;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void displayBookingDetails() {
        System.out.println("Client: " + client);
        System.out.println("Booking Name: " + name);
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Location: " + location);
    }
}

