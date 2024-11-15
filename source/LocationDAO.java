import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LocationDAO {
    public static Location getLocationById(int locationId) {
        String query = "SELECT * FROM locations WHERE id = ?";
        try (Connection conn = Database.connecttoDB(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, locationId);
            ResultSet rs = pstmt.executeQuery();

            // Check if the location is found in the database
            if (rs.next()) {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String room = rs.getString("room");
                String organization = rs.getString("organization");

                return new Location(id, address, city, room, organization);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching location: " + e.getMessage());
        }
        return null;
    }

}
