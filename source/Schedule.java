
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private static List<Offering> schedule = new ArrayList<>();

    public static void addOffering(Offering offering) {
        if (offering.isVisible()){
        Schedule.schedule.add(offering);
    }
    }

    public List<Offering> getOfferings() {
        return schedule;
    }
}
