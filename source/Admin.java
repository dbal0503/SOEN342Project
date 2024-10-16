import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Users {
    private static List<Admin> admins;
    

    public Admin(String name, int id) {
        this.name = name;
        this.uniqueId = id;
        this.phone_number = 0;

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


}