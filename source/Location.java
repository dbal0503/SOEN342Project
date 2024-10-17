import java.util.List;


public class Location {
    private String address;
    private City city;
    private String room;
    private String organization;
    private List<String> offerings;

    public Location(String address, String city, String organization, String room) {
        this.address = address;
        this.city = new City(city);
        this.organization = organization;
        //this.offerings = offerings;
        this.room = room;
    }
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<String> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<String> offerings) {
        this.offerings = offerings;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", organization='" + organization + '\'' +
                ", offerings=" + offerings +
                '}';
    }
}