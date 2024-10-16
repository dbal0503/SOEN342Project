import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Instructor extends Users {
    public Specialization specialization;
    public Availabilities availabilities;
    private static List<Instructor> instructors;
    
    private Instructor(String name, int id, int phone_number, Specialization specialization, Availabilities availabilities) {
        this.name = name;
        this.uniqueId = id;
        this.phone_number = phone_number;
        this.specialization = specialization;
        this.availabilities = availabilities;
    }
    public static boolean registerInstructor(String name, String phone_number, String specialization, String availabilities) {
        int int_phone_number= 0;
        boolean condition = true;
        int id =0;
        Availabilities availabilities_Instructor = parseAvailabilities(availabilities);
        Specialization specialization_Specialization = new Specialization(specialization);
        if (Instructor.instructors == null){
            instructors = new ArrayList<Instructor>();
            Instructor newInstructor = new Instructor(name, id, int_phone_number, specialization_Specialization, availabilities_Instructor);
            instructors.add(newInstructor);
            return true;

        }
        for (Instructor instructor : instructors) {
            Instructor newInstructor = new Instructor(name, id, int_phone_number, specialization_Specialization, availabilities_Instructor);
            instructors.add(newInstructor);
            
             int_phone_number= parsephoneNumber(phone_number);
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
    /*public static int parsephoneNumber(String phone_number) {
        String[] phone_numberArray = phone_number.split("-");
        String phoneNumber = "";
        for (String number : phone_numberArray) {
            phoneNumber += number;
        }
        return Integer.parseInt(phoneNumber);
    }*/
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
    public static void instructorRegistration(Scanner scanner) {
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

}
