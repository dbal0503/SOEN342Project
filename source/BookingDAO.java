import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

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


    public static List<Booking> getBookingsByClient(int clientId) {
        List<Booking> bookings = new ArrayList<>();

        String query = "SELECT * FROM bookings WHERE client_id = ?";

        try (Connection connection = Database.connecttoDB();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, clientId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int bookingId = rs.getInt("booking_id");
                    int offeringId = rs.getInt("offering_id");
                    Date bookingDate = rs.getDate("booking_date");
                    String status = rs.getString("status");
                    
                    Booking booking = new Booking(bookingId, clientId, offeringId);
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: Unable to retrieve bookings for client.");
            e.printStackTrace();
        }
        return bookings;
    }

}


