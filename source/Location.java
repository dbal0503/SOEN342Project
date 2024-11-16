import java.util.ArrayList;
import java.util.List;

public class Location {

    private int id;
    private String address;
    private City city;
    //private String organization;
    public static List<Location> locations = new ArrayList<>();

    public Location(int id, String address, String city) {
        this.address = address;
        this.city = new City(city);
        this.id = id;
    }
    public Location(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    /*public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }*/
    public static void printAllLocations() {

        locations = LocationDAO.getAllLocations();
        if (locations.isEmpty()) {
            System.out.println("No locations available.");
            return;
        }

        for (Location location : locations) {
            System.out.println(location);
        }
    }

    @Override
    public String toString() {
        return "Location Details: " +
                "ID = " + id +
                '\'';
    }
}