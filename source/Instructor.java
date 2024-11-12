import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Instructor extends Users {
    public Specialization specialization;
    public Availabilities availabilities;
    private static List<Instructor> instructors;
    private static final Scanner scanner = new Scanner(System.in);

    private Instructor(String name, int id, int phone_number, Specialization specialization, Availabilities availabilities) {
        this.name = name;
        this.uniqueId = id;
        this.phone_number = phone_number;
        this.specialization = specialization;
        this.availabilities = availabilities;
    }

    private  static boolean registerInstructor(String name, String phone_number, String specialization, String availabilities) {
        int int_phone_number;
        int_phone_number= parsephoneNumber(phone_number);
        boolean condition = true;
        int id =0;
        Availabilities availabilities_Instructor = Availabilities.parseAvailabilities(availabilities);
        Specialization specialization_Specialization = new Specialization(specialization);
        if (Instructor.instructors == null){
            instructors = new ArrayList<>();
            Instructor newInstructor = new Instructor(name, id, int_phone_number, specialization_Specialization, availabilities_Instructor);
            instructors.add(newInstructor);
            return true;

        }
        for (Instructor instructor : instructors) {

            if (instructor.phone_number == int_phone_number) {
                System.out.println("An Instructor with this phone number already exists");
                return false;
            }
        }
        while(condition){
            id = (int)(Math.random() * 1000);
            for(Instructor instructor : instructors){
                if(instructor.uniqueId == id){
                    condition = true;
                    break;
                }
                else{
                    condition = false;
                }
            }
        }
        Instructor newInstructor = new Instructor(name, id, int_phone_number, specialization_Specialization, availabilities_Instructor);
        instructors.add(newInstructor);
        return true;
    }

    public String getName() {
        return this.name;
    }

    public static List<Instructor> getInstructors() {
        return instructors;
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
    public int getPhoneNumber() {
        return this.phone_number;
    }
    public void setPhoneNumber(int phone_number) {
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
        scanner.nextLine();
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
        for (Instructor instructor : instructors) {
            if (instructor.phone_number == parsephoneNumber(phone_number)) {
                System.out.println("Instructor found");
                return instructor;
            }
        }
        System.out.println("Instructor not found");
        return null;
    }
    public static boolean instructorLogin() {
        System.out.println("instructor Login");
        System.out.println("Enter your phone number in the following format xxx-xxx-xxxx: ");
        String phone_number = scanner.nextLine();
        Instructor instructor = findInstructor(phone_number);
        if (instructor != null) {
            System.out.println("Logged in successfully");
            Session.getInstance(instructor);
            return true;
        }
        else {
            System.out.println("Login failed");
            return false;
        }
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
