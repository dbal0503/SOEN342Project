import java.sql.SQLException;
import java.util.List;

public class City{
    public String name;

    
    public City(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public static void getAllCities() throws SQLException {
        CityDAO cityDAO = new CityDAO();
        List<City> cities = cityDAO.getAllCities();
        for (City city : cities) {
            System.out.println(city.getName());
        }
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}