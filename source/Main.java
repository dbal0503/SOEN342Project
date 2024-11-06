import java.util.Scanner;

public class Main {
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        Users user = null;

        do{
        System.out.println("Choose one of the following options");
        System.out.println("0. Exit");
        System.out.println("1. Admin Login");
        System.out.println("2. Instructor Menu");
        System.out.println("3. Instructor Registration");
        System.out.println("4. View Offerings Schedule");
        System.out.println("5. Make a booking");
        System.out.println("6. Client Registration");
        System.out.println("7. Client Login");
        System.out.println("8: Make a Booking");
        System.out.println("9: Instructor Login");

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
            case 6 -> {
                System.out.println("Client Registration");
                Client.clientRegistration();

            }
            case 7 -> {
                System.out.println("Client Login");
                Users client = Client.clientLogin();
                user = client;
            }
            case 8 -> {
                System.out.println("Client Enrollment");
                    if (Session.hasSession()){
                        Enrollment.enrollmentMenu((Client) user);
                }
                    else {System.out.println("User must sign in before making a booking");}
                }
            case 9 ->{
                System.out.println("Instructor Login");
                Instructor.instructorLogin();
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