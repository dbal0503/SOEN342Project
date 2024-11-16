import java.util.Scanner;

public class Enrollment {
    private static final Scanner scanner = new Scanner(System.in);

    public static void enrollmentMenu(Client client) {

        System.out.println("Enrollment Menu:");
        Schedule.viewPublicOfferings();

        System.out.println("Please enter the index of the offering you wish to enroll in:");
        int index = scanner.nextInt();
        scanner.nextLine();  // Consume  newline

        Offering selectedOffering = getOfferingByIndex(index);

        if (selectedOffering == null) {
            // Handle (offering doesn't exist)
            System.out.println("Invalid index. Please try again.");
            return;
        }

        if (!selectedOffering.isAvailable()) {
            System.out.println("This offering is not available for enrollment.");
            return;
        }
        if (BookingDAO.hasBookingConflict(selectedOffering.getId(), ((Client)Session.user).uniqueId)) {
            System.out.println("You already have a booking that conflicts with this offering.");
            return;
        }
        if (client.getAge() < 18) {
            System.out.println("Guardian approval required. Confirm guardian is present to proceed? (yes/no): ");
            String guardianApproval = scanner.nextLine();
            if (!guardianApproval.equalsIgnoreCase("yes")) {
                System.out.println("Enrollment cancelled: guardian approval required.");
                return;
            }
        }
        boolean enrolled = enrollClientInOffering(client, selectedOffering);
        if (enrolled) {
            System.out.println("Enrollment successful.");
        } else {
            System.out.println("Enrollment failed. Please try again.");
        }
    }

    private static boolean enrollClientInOffering(Client client, Offering offering) {
        if (offering.isGroup()) {
            // Group offering
            if (offering.getEnrolled() < offering.getCapacity()) {
                createBooking(client,offering);
                return true;
            } else {
                System.out.println("No available seats in this group offering.");
                return false;
            }
        } else {
            // Private offering:
            if (offering.isAvailable()) {
                createBooking(client,offering);
                return true;
            } else {
                System.out.println("Private offering is no longer available.");
                return false;
            }
        }
    }
    public static Offering getOfferingByIndex(int index) {
        if (index >= 0 && index < Schedule.getOfferings().size()) {
            return Schedule.getOfferings().get(index);
        } else {
            return null;
        }
    }

    public static void createBooking(Client client, Offering offering) {
        if (offering.isGroup() && offering.getEnrolled() < offering.getCapacity()) {
            //enrolledClients.add(client);
            //enrolled++;
            BookingDAO.addBooking(offering.getId(),client.getUniqueId());

            offering.setEnrolled(offering.getEnrolled() + 1);

            if(offering.getEnrolled() == offering.getCapacity()){
                offering.setAvailable(false);
            }
            BookingDAO.updateOfferingInDatabase(offering);
        }
        else { ///booking is private
           // enrolledClients.add(client);
           // enrolled++;
           // bookings.add(booking);
            BookingDAO.addBooking(offering.getId(), client.getUniqueId());
            offering.setAvailable(false);
            offering.setEnrolled(offering.getEnrolled() + 1);
            BookingDAO.updateOfferingInDatabase(offering);
        }
    }

}
