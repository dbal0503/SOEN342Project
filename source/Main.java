import java.sql.SQLException;
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

            if (Session.hasSession()) {
                if (Session.user instanceof Admin) {
                    System.out.println("1. Admin Menu");
                } else if (Session.user instanceof Instructor) {
                    System.out.println("2. Instructor Menu");
                } else if (Session.user instanceof Client) {
                    System.out.println("3. View Bookings");
                    System.out.println("4. Make a Booking");
                }
                System.out.println("5. Logout");
            } else {
                System.out.println("6. Admin Login");
                System.out.println("7. Instructor Login");
                System.out.println("8. Instructor Registration");
                System.out.println("9. Client Login");
                System.out.println("10. Client Registration");
            }


            choice = scanner.nextInt();

            switch (choice) {
                case 0 -> System.out.println("Exiting");
                case 1 -> {
                    if (Session.hasSession() && Session.user instanceof Admin) {
                    } else {
                        System.out.println("Access denied.");
                    }
                }
                case 2 -> {
                    if (Session.hasSession() && Session.user instanceof Instructor) {
                        try{
                        Instructor.instructorMenu();}
                        catch(SQLException e){e.printStackTrace();}
                    } else {
                        System.out.println("Access denied.");
                    }
                }
                case 3 -> {
                    if (Session.hasSession() && Session.user instanceof Client) {
                        Booking.viewBookings();
                    } else {
                        System.out.println("Access denied.");
                    }
                }
                case 4 -> {
                    if (Session.hasSession() && Session.user instanceof Client) {
                        Enrollment.enrollmentMenu((Client) Session.user);
                    } else {
                        System.out.println("You must log in before making a booking.");
                    }
                }
                case 5 -> {
                    if (Session.hasSession()) {
                        Session.logout();
                        System.out.println("Logged out successfully.");
                    } else {
                        System.out.println("No user logged in.");
                    }
                }
                case 6 -> {
                    if (!Session.hasSession()) {
                        Admin.adminLogin();
                    } else {
                        System.out.println("You are already logged in.");
                    }
                }
                case 7 -> {
                    if (!Session.hasSession()) {
                        if (Instructor.instructorLogin()) {
                            System.out.println("Instructor logged in successfully.");
                        }
                    } else {
                        System.out.println("You are already logged in.");
                    }
                }
                case 8 -> {
                    if (!Session.hasSession()) {
                        Instructor.instructorRegistration();
                    } else {
                        System.out.println("You are already logged in.");
                    }
                }
                case 9 -> {
                    if (!Session.hasSession()) {
                        Users client = Client.clientLogin();
                        if (client != null) {
                            Session.getInstance(client);
                            System.out.println("Client logged in successfully.");
                        }
                    } else {
                        System.out.println("You are already logged in.");
                    }
                }
                case 10 -> {
                    if (!Session.hasSession()) {
                        Client.clientRegistration();
                    } else {
                        System.out.println("You are already logged in.");
                    }
                }
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
    
    public static void main(String[] args) {
        Database.connecttoDB();
        System.out.println("------------------------------------------------------------------");
        Admin a1 = new Admin("David", 0);

        menu();


        
    }
}