import java.util.ArrayList;
import java.util.List;

public class OfferingCatalog {
    private static List<Offering> offeringCatalog = new ArrayList<>();

    public static void addOffering(Offering offering) {
        offeringCatalog.add(offering);
    }

    public static List<Offering> getOfferings() {
        return offeringCatalog;
    }

    public static void removeOffering(Offering offering) {
        offeringCatalog.remove(offering);
    }

    public static void printCatalog() {
        for (Offering offering : offeringCatalog) {
            System.out.println(offering);
        }
    }
}   
