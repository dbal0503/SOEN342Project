
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private static List<Offering> schedule = new ArrayList<>();

    public void addOffering(Offering offering) {
        this.schedule.add(offering);
    }

    public List<Offering> getOfferings() {
        return schedule;
    }
}
