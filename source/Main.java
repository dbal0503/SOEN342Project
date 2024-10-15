public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Admin admin = new Admin("John", 1);
        System.out.println(admin.getName());

        Location location = new Location("4549 Main St", "CityBob", "Community Center", "Room 92");
        admin.createOffering(location, "9:00", "10:00", true, 20, "2021-10-01", "Yoga");

        OfferingCatalog.printCatalog();
        
    }
}