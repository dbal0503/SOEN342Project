
public class Admin {
    private String name;
    private int id;
    

    public Admin(String name, int id) {
        this.name = name;
        this.id = id;
    }

 void createOffering(Location location, String startTime, String endTime, boolean isGroup, int capacity, String date, String offeringName) {
            Offering newOffering = new Offering(location, startTime, endTime, isGroup, capacity, date, offeringName);
            OfferingCatalog.addOffering(newOffering);
        }
    public String getName() {
        return this.name;
    }

public void setName(String name) {
    this.name = name;
}

public int getId() {
    return this.id;
}

public void setId(int id) {
    this.id = id;
}
}