import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class BookingDAO {

    public static void addBooking(int offeringId, int clientId) {
        String sql = "INSERT INTO bookings (offering_id, client_id) VALUES (?, ?)";

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
                    int bookingId = rs.getInt("id");
                    int offeringId = rs.getInt("offering_id");
                    
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

    public static boolean cancelBooking(int clientId, int bookingId) {
        if (findBookingByClientIdAndBookingId(clientId, bookingId)) {
            return deleteBooking(bookingId);
        } else {
            System.out.println("Booking not found for the specified client and booking ID.");
            return false;
        }
    }

    private static boolean findBookingByClientIdAndBookingId(int clientId, int bookingId) {
        String query = "SELECT id FROM bookings WHERE client_id = ? AND id = ?";
        try (Connection connection = Database.connecttoDB();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, clientId);
            pstmt.setInt(2, bookingId);

            return pstmt.executeQuery().next();  // result is found =booking exists

        } catch (SQLException e) {
            System.err.println("Error locating booking: " + e.getMessage());
            return false;
        }
    }
    public static boolean deleteBooking(int bookingId) {
        String deleteQuery = "DELETE FROM bookings WHERE id = ?";
        try (Connection connection = Database.connecttoDB();
             PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, bookingId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Booking cancelled successfully.");
                return true;
            } else {
                System.out.println("Failed to cancel booking.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error deleting booking: " + e.getMessage());
            return false;
        }
    }
    public static List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                int id = rs.getInt("id");
                int offeringId = rs.getInt("offering_id");
                int clientId = rs.getInt("client_id");

                Booking booking = new Booking(id, offeringId, clientId);
                bookings.add(booking);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving bookings: " + e.getMessage());
        }

        return bookings;
    }

}


