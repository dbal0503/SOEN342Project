import java.util.Scanner;

public class Main {
    public static void menu(){
        Database.initDatabase();
        Scanner scanner = new Scanner(System.in);
        int choice;
        Users user = null;

        do{
        System.out.println("Choose one of the following options");
        System.out.println("0. Exit");
        System.out.println("1. Admin Login");
        System.out.println("2. Instructor Menu");
        System.out.println("3. Instructor Registration");
        System.out.println("6. Client Registration");
        System.out.println("7. Client Login");
        System.out.println("8: Make a Booking");
        System.out.println("9: Instructor Login");
        System.out.println("10: View Bookings");

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
                System.out.println("Make A Booking");
                if (Session.hasSession()) {
                    Enrollment.enrollmentMenu((Client) Session.user);
                } else {
                    System.out.println("User must sign in before making a booking");
                }
                }
            case 9 ->{
                System.out.println("Instructor Login");
                Instructor.instructorLogin();
            }
            case 10 ->{
                if (Session.hasSession()) {
                    Client clientFromSession = (Client) Session.user;
                    Booking.viewBookings();
                } else {
                    System.out.println("You must log in first to view bookings.");
                }
            }
            case 11 ->{
                if (Session.hasSession()) {
                    Client clientFromSession = (Client) Session.user;
                    Booking.deleteBookings(clientFromSession);
                } else {
                    System.out.println("You must log in first to view bookings.");
                }

            }




            
            case 0 -> System.out.println("Exiting");
            default -> System.out.println("Invalid choice");
        }
        } while(choice !=0);
    }
    
    public static void main(String[] args) {
        Database.connecttoDB();
        System.out.println("------------------------------------------------------------------");
        Admin admin = new Admin("John", 1);
        Admin admin1 = new Admin("Susan", 2);
        menu();
    }
}