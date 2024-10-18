public class Availabilities{
    public City[] cities;
    public Availabilities(City[] cities) {
        this.cities = cities;
    }
        public static Availabilities parseAvailabilities(String availabilities){
        String[] availabilitiesArray = availabilities.split(",");
        City[] cities = new City[availabilitiesArray.length];
        for (int i = 0; i < availabilitiesArray.length; i++) {
            availabilitiesArray[i] = availabilitiesArray[i].trim();
            City city = new City(availabilitiesArray[i]);
            cities[i] = city;
        }
        Availabilities availabilities_Instructor = new Availabilities(cities);
        return availabilities_Instructor;

    }
}
