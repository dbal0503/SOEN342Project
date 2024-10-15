import java.util.List;


public class Location {
    private String address;
    private String city;
    private String organization;
    private List<String> offerings;

    public Location(String address, String city, String organization, List<String> offerings) {
        this.address = address;
        this.city = city;
        this.organization = organization;
        this.offerings = offerings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
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