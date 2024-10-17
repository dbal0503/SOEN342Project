public class Offering {
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

    public Location getLocation() {
        return location;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        setVisible(true);
        OfferingCatalog.removeOffering(this);
        Schedule.addOffering(this);
    }
    
    // Parameterized constructor
    public Offering(Location location, String startTime, String endTime, boolean isGroup, int capacity, String date, String offeringName) {
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isGroup = isGroup;
        this.capacity = capacity;
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

    private void setVisible(boolean visible) {
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