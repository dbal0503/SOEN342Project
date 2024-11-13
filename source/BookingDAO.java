import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAO {

    public static void addBooking(int offeringId, int clientId) {
        String sql = "INSERT INTO bookings (offeringid, clientid) VALUES (?, ?)";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, offeringId);
            pstmt.setInt(2, clientId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking added successfully.");
            } else {
                System.out.println("Failed to add booking.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding booking: " + e.getMessage());
        }
    }

    public static void updateOfferingInDatabase(Offering offering) {
        String sql = "UPDATE offerings SET available = ?, enrolled = ? WHERE id = ?";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, offering.isAvailable());
            pstmt.setInt(2, offering.getEnrolled());
            pstmt.setInt(3, offering.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Offering availability and enrolled count updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating offering in database: " + e.getMessage());
        }
    }


}
