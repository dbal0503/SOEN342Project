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

}
