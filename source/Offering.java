import java.util.List;
import java.util.ArrayList;

public class Offering {

    private int id;
    private Location location;
    private String startTime;
    private String endTime;
    private boolean available;
    private boolean isGroup;
    private boolean visible=false;
    private int capacity;
    private int enrolled=0;
    private Instructor instructor;
    private String date;
    private String offeringName;
    private List<Client> enrolledClients = new ArrayList<>();

    private List<Booking> bookings = new ArrayList<>();

    public int getEnrolled() {
        return enrolled;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    // Default constructor
    public Offering() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Location getLocation() {
        return location;
    }

    public Instructor getInstructor() {
        return instructor;
    }
    public Integer getInstructorId(){
        if (instructor==null)
            return null;
        return instructor.getId();
    }
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    public void enrollClient(Client client) {
        if (isGroup && enrolled < capacity) {
            enrolledClients.add(client);
            enrolled++;
            //Booking booking = new Booking(client, this);
           // bookings.add(booking);

            if(enrolled == capacity){
                Schedule.removeOffering(this);
            }
        }
        else if (!isGroup) {
            enrolledClients.add(client);
            enrolled++;
            //Booking booking = new Booking(client, this);
           // bookings.add(booking);
            setAvailable(false);
            Schedule.removeOffering(this);
        }
    }
    public static void printOfferingList(List<Offering> offeringList) {
        for (Offering offering : offeringList) {
            System.out.println(offering.toString());

        }
    }
    public Offering(int locationId, String startTime, String endTime, boolean isGroup, int capacity, String date, String offeringName) {
        this.location = new Location(locationId);
        this.startTime = startTime;
        this.endTime = endTime;
        this.available = available;
        this.isGroup = isGroup;
        this.visible = visible;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.instructor = instructor;
        this.date = date;
        this.offeringName = offeringName;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String time) {
        this.startTime = time;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOfferingName() {
        return offeringName;
    }

    public void setOfferingName(String offeringName) {
        this.offeringName = offeringName;
    }

    public void enroll() {
        enrolled++;
    }

    public void unenroll() {
        enrolled--;
    }

    public void cancel() {
        available = false;
    }
    public static void printCatalog(List<Offering> offerings) {
        for (Offering offering : offerings) {
            System.out.println(offering);
        }
    }

@Override
public String toString() {
    return "Offering{" +
            "location=" + location +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", available=" + available +
            ", isGroup=" + isGroup +
            ", visible=" + visible +
            ", capacity=" + capacity +
            ", enrolled=" + enrolled +
            ", instructor=" + instructor +
            ", date='" + date + '\'' +
            ", offeringName='" + offeringName + '\'' +
            '}';
}
}