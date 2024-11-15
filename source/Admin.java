import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Users {
    private static List<Admin> admins;
    private static final Scanner scanner = new Scanner(System.in);

    

    public Admin(String name, int id) {
        this.name = name;
        this.uniqueId = id;

        if (Admin.admins == null) {
            admins = new ArrayList<Admin>();
            Admin.admins.add(this);
        }
        else {
            Admin.admins.add(this);
        }
    }

    public static void displayAdmins() {
        for (Admin admin : admins) {
            System.out.println(admin.getName());
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
public static boolean findAdmin(int id) {
        for (Admin admin : admins) {
            if (admin.uniqueId == id) {
                System.out.println("Admin found");
                return true;
            }
        }
        System.out.println("Admin not found");
        return false;
    }
    public static void adminLogin() {
        System.out.println("Please enter your id: ");
        int id = scanner.nextInt();
        if (findAdmin(id)) {
            System.out.println("Logged in successfully!");
            adminMenu();
        } else {
            System.out.println("Login failed. Admin not found.");
        }
    }

    public static void adminMenu() {
        int choice;
        do {
            System.out.println("Admin Menu:");
            System.out.println("1. Create Offering");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAdminOffering();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 0);
    }

   public static void createAdminOffering() {
        scanner.nextLine();  // Consume newline
        System.out.println("Enter the offering name: ");
        String offeringName = scanner.nextLine();
        System.out.println("Enter the start time (YYYY-MM-DD HH:MM:SS): ");
        String startTime = scanner.nextLine();
        System.out.println("Enter the end time (YYYY-MM-DD HH:MM:SS): ");
        String endTime = scanner.nextLine();
        System.out.println("Is this a group offering? (true/false): ");
        boolean isGroup = scanner.nextBoolean();
        System.out.println("Enter the capacity of the offering: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the day of the week of the offering: ");
        String date = scanner.nextLine();

        System.out.println("Enter the location ID for this offering: ");
        Location.printAllLocations();
        int locationId = scanner.nextInt();

        Location location = LocationDAO.getLocationById(locationId);

        if (location != null) {
            Offering newOffering = new Offering(locationId, startTime, endTime, isGroup, capacity, date, offeringName);

            int offeringId = OfferingDAO.addOffering(newOffering);

            if (offeringId != -1) {
                System.out.println("Offering created successfully with ID: " + offeringId);
            } else {
                System.out.println("Error creating offering.");
            }
        } else {
            System.out.println("Invalid location ID. Offering creation failed.");
        }
    }

}