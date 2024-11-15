
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private static List<Offering> schedule = new ArrayList<>();

    public static void addOffering(Offering offering) {
        if (offering.isVisible()){
        Schedule.schedule.add(offering);
    }
    }
    public static void removeOffering(Offering offering) {
        if (offering.isVisible()){
            Schedule.schedule.remove(offering);
        }
    }

    public static List<Offering> getOfferings() {
        return schedule;
    }

    public static void viewPublicOfferings() {
        schedule = OfferingDAO.getClientVisibleOfferings();

        if (schedule.isEmpty()) {
            System.out.println("No public offerings available at the moment.");
        } else {
            System.out.println("Public Offerings:");
            for (int i = 0; i < schedule.size(); i++) {
                Offering offering = schedule.get(i);
                System.out.println(i + ": " + offering.getOfferingName() +
                        " - Location: " + offering.getLocation() +
                        " - Date: " + offering.getDate() +
                        " - Start Time: " + offering.getStartTime() +
                        " - End Time: " + offering.getEndTime());
            }
        }
    }
}
