import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Availabilities {
    private List<City> cities;

    public Availabilities(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        return cities;
    }

    // Remove or modify the old parseAvailabilities method since we're no longer using string-based cities
    public static Availabilities parseAvailabilities(String citiesInput) {
        List<City> cities = new ArrayList<>();
        String[] cityNames = citiesInput.split(",");
        for (String cityName : cityNames) {
            cities.add(new City(cityName.trim()));
        }
        return new Availabilities(cities);
    }
}
