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


    private  static boolean registerInstructor(String name, String phone_number, String specialization, String availabilities) {
        try{

            Availabilities availabilities_Instrucotor = Availabilities.parseAvailabilities(availabilities);
            Specialization specialization_Instructor = new Specialization(specialization);

            Instructor newInstructor = new Instructor(name, phone_number, specialization_Instructor, availabilities_Instrucotor);

            int generatedId = instructorDAO.addInstructor(newInstructor);
            newInstructor.setId(generatedId);
            return generatedId > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return this.name;
    }

    public static List<Instructor> getInstructors() {
        try {
            return instructorDAO.getAllInstructor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        System.out.println("Enter the city or cities you are available to work in delimited by comas: ");
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
            return instructorDAO.getInstructorbyphonenumber(phone_number);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean instructorLogin() {
        System.out.println("instructor Login");
        System.out.println("Enter your phone number in the following format xxx-xxx-xxxx: ");
        String phone_number = scanner.nextLine();
        Instructor instructor = findInstructor(phone_number);
        System.out.println("Logged in successfully");
        Session.getInstance(instructor);
        return true;
    }

    public static void instructorMenu() {
        int choice;
        do {
            System.out.println("Choose one of the following options");
            System.out.println("0. Exit");
            System.out.println("1. View your offerings");
            System.out.println("2. Accept an offering");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {System.out.println("Viewing your offerings");
                    List<Offering> offerings = OfferingCatalog.getOfferings((Instructor)Session.user);
                    for (Offering offering : offerings) {
                        System.out.print("ID: " + offerings.indexOf(offering) + " ");
                        System.out.println(offering);

                    }
                }
                case 2 -> {
                    System.out.println("Enter the id of the offering you would like to accept: ");
                    int id = scanner.nextInt();
                    Offering offering = OfferingCatalog.getOfferings().get(id);
                    offering.setInstructor((Instructor)Session.user);

                }

                case 0 -> System.out.println("Logout");
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

}
