
import java.util.Scanner;

public class Main {
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do{
        System.out.println("Choose one of the following options");
        System.out.println("0. Exit");
        System.out.println("1. Admin Login");
        System.out.println("2. Instructor Login");
        System.out.println("3. Instructor Registration");
        System.out.println("4. View Offerings Schedule");
        System.out.println("5. Make a booking");

        choice = scanner.nextInt();
        
        switch(choice) {
            case 1 -> {
                System.out.println("Admin Login");
                Admin.adminLogin();
                }
            case 2 ->{ if(Instructor.instructorLogin()){
                Instructor.instructorMenu();}

            }
            case 3 -> Instructor.instructorRegistration();
            case 4 -> Schedule.viewPublicOfferings();
            case 5 -> {System.out.println("Booking Interface");
        
        
        }
            case 0 -> System.out.println("Exiting");
            default -> System.out.println("Invalid choice");
        }
        } while(choice !=0);
    }
    
    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------------");
        Admin admin = new Admin("John", 1);
        Admin admin1 = new Admin("Susan", 2);

        menu();
    
        Admin.displayAdmins();
        Location location = new Location("4549 Main St", "CityBob", "Community Center", "Room 92");
        admin.createOffering(location, "9:00", "10:00", true, 20, "2021-10-01", "Yoga");

        OfferingCatalog.printCatalog();
        
    }
}