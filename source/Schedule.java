
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private static List<Offering> schedule = OfferingDAO.getClientVisibleOfferings();

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

    public static void viewPublicOfferings(){
        for(Offering offering : schedule){
            System.out.print("ID: " + schedule.indexOf(offering) + " ");
            System.out.println(offering);
    }
}

}