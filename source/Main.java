
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

        choice = scanner.nextInt();
        
        switch(choice) {
            case 1:
                System.out.println("Admin Login");
                break;
            case 2:
                Instructor.instructorLogin(scanner);
                break;
            case 3:
                Instructor.instructorRegistration(scanner);
                break;
            case 0:
                System.out.println("Exiting");
                break;
            default:
                System.out.println("Invalid choice");
        }
        } while(choice !=0);
    }
    
    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------------");
        
        menu();
    
        Admin admin = new Admin("John", 1);
        System.out.println(admin.getName());

        Location location = new Location("4549 Main St", "CityBob", "Community Center", "Room 92");
        admin.createOffering(location, "9:00", "10:00", true, 20, "2021-10-01", "Yoga");

        OfferingCatalog.printCatalog();
        
    }
}