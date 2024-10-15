public class Offering {
    private Location location;
    private String time;
    private boolean available;
    private boolean isGroup;
    private boolean visible;
    private int capacity;
    private int enrolled=0;
    private Instructor instructor;

    // Default constructor
    public Offering() {
    }

    // Parameterized constructor
    public Offering(Location location, String time, boolean available, boolean isGroup, boolean visible, int capacity, int enrolled, Instructor instructor) {
        this.location = location;
        this.time = time;
        this.available = available;
        this.isGroup = isGroup;
        this.visible = visible;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    // Parameterized constructor
    public Offering(Location location, String time, boolean available, boolean isGroup, boolean visible, int capacity, int enrolled) {
        this.location = location;
        this.time = time;
        this.available = available;
        this.isGroup = isGroup;
        this.visible = visible;
        this.capacity = capacity;
        this.enrolled = enrolled;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
