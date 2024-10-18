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
    

 void createOffering(Location location, String startTime, String endTime, boolean isGroup, int capacity, String date, String offeringName) {
            Offering newOffering = new Offering(location, startTime, endTime, isGroup, capacity, date, offeringName);
            OfferingCatalog.addOffering(newOffering);
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
    public static void adminLogin(){
        scanner.nextLine();
        System.out.println(" Please enter your id: ");
        int id = scanner.nextInt();
        if (findAdmin(id)) {
            System.out.println("Logged in successfully");
        }
        else {
            System.out.println("Login failed");
        }
    }

}