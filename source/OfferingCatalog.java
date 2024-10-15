import java.util.ArrayList;
import java.util.List;

public class OfferingCatalog {
    private static List<Offering> offeringCatalog = new ArrayList<>();

    public void addOffering(Offering offering) {
        offeringCatalog.add(offering);
    }

    public List<Offering> getOfferings() {
        return new ArrayList<>(offeringCatalog);
    }
}   
