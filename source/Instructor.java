import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Instructor extends Users {
    public Specialization specialization;
    public Availabilities availabilities;
    private static final Scanner scanner = new Scanner(System.in);
    private static InstructorDAO instructorDAO;

    static {
        try{
            instructorDAO = new InstructorDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Instructor(String name, String phone_number, Specialization specialization, Availabilities availabilities) {
        this.name = name;
        this.phone_number = phone_number;
        this.specialization = specialization;
        this.availabilities = availabilities;
    }
    public Instructor(int id){
        this.uniqueId = id;
    }


    private static boolean registerInstructor(String name, String phone_number, String specialization, String citiesInput) {
        try {
            if (instructorDAO.isPhoneNumberExists(phone_number)) {
                System.out.println("Error: Phone number already exists.");
                return false;
            }


            List<City> cities = new ArrayList<>();
            String[] cityNames = citiesInput.split(",");
            for (String cityName : cityNames) {
                cities.add(new City(cityName.trim()));
            }

            // Create availabilities with the list of cities
            Availabilities availabilities = new Availabilities(cities);
            Specialization specialization_Instructor = new Specialization(specialization);

            Instructor newInstructor = new Instructor(name, phone_number, specialization_Instructor, availabilities);

            int generatedId = instructorDAO.addInstructor(newInstructor);
            newInstructor.setId(generatedId);
            return generatedId > 0;

        } catch (SQLException e) {
            System.out.println("Error during instructor registration: " + e.getMessage());
            return false;
        }
    }

    public String getName() {
        return this.name;
    }




    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.uniqueId;
    }

    public void setId(int id) {
        this.uniqueId = id;
    }
    public String getPhoneNumber() {
        return this.phone_number;
    }
    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }
    public Specialization getSpecialization() {
        return this.specialization;
    }
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
    public Availabilities getAvailabilities() {
        return this.availabilities;
    }
    public void setAvailabilities(Availabilities availabilities) {
        this.availabilities = availabilities;
    }
    public static void instructorRegistration() {
        System.out.println("Instructor Registration");
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your phone number in the following format xxx-xxx-xxxx: ");
        String phone_number = scanner.nextLine();
        System.out.println("Enter your specialization: ");
        String specialization = scanner.nextLine();
        try{
        City.getAllCities();}
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Enter the name of the city or cities you are available to work from delimited by comas");
        String cities = scanner.nextLine();
        if (registerInstructor(name, phone_number, specialization, cities)) {
            System.out.println("Instructor registered successfully");
        }
        else {
            System.out.println("Instructor registration failed");
        }
    }


    public static Instructor findInstructor(String phone_number) {
        try{
            return instructorDAO.getInstructorByPhoneNumber(phone_number);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean instructorLogin() {
        System.out.println("Instructor Login");
        System.out.println("Enter your phone number in the following format xxx-xxx-xxxx: ");
        String phone_number = scanner.nextLine();
        Instructor instructor = findInstructor(phone_number);
        if (instructor == null) {
            System.out.println("Invalid Phone Number");
            return false; }
        System.out.println("Logged in successfully");
        Session.getInstance(instructor);
        return true;
    }

    public static void instructorMenu() throws SQLException {
        int choice;
        do {
            System.out.println("Choose one of the following options");
            System.out.println("0. Exit");
            System.out.println("1. View your offerings");
            System.out.println("2. Accept an offering");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {System.out.println("Viewing your offerings");
                    List<Offering> offerings = OfferingDAO.getOfferingsByInstructorId(((Instructor)Session.user).uniqueId);
                    for (Offering offering : offerings) {
                        System.out.print("ID: " + offerings.indexOf(offering) + " ");
                        System.out.println(offering);

                    }
                }
                case 2 -> {
                    List<Offering> offerings = OfferingDAO.getOfferingsByInstructorCity(((Instructor)Session.user).uniqueId);
                    if (offerings.isEmpty()) {
                        System.out.println("No offerings found");
                        break;
                    }
                    Offering.printOfferingList(offerings);
                    System.out.println("Enter the id of the offering you would like to accept: ");
                    int id = scanner.nextInt();
                    Offering offering = OfferingDAO.getOfferingDetailsById(id);
                    assert offering != null;
                    offering.assignInstructor((Instructor)Session.user);

                }

                case 0 -> System.out.println("Logout");
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

}
