import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    public static Location getLocationById(int locationId) {
        String query = "SELECT l.*, c.name as city_name FROM locations l " +
                "JOIN cities c ON l.city_id = c.id WHERE l.id = ?";
        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, locationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                String cityName = rs.getString("city_name");
                String room = rs.getString("room");
                String organization = rs.getString("organization");

                return new Location(id, address, cityName, room, organization);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching location: " + e.getMessage());
        }
        return null;
    }

    public static List<Location> getAllLocations() {
        String query = "SELECT l.*, c.name as city_name FROM locations l " +
                "JOIN cities c ON l.city_id = c.id";
        List<Location> locations = new ArrayList<>();

        try (Connection conn = Database.connecttoDB()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String address = rs.getString("address");
                    String cityName = rs.getString("city_name");
                    String room = rs.getString("room");
                    String organization = rs.getString("organization");

                    Location location = new Location(id, address, cityName, room, organization);
                    locations.add(location);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching locations: " + e.getMessage());
        }

        return locations;
    }
}