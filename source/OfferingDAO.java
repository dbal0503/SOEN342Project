import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferingDAO {

    public static int addOffering(Offering offering) {
        String sql = "INSERT INTO offerings (location_id, starttime, endtime, available, isgroup, visible, capacity, enrolled, instructor_id, date, offeringname) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, offering.getLocation().getId());
            pstmt.setString(2, offering.getStartTime());
            pstmt.setString(3, offering.getEndTime());
            pstmt.setBoolean(4, offering.isAvailable());
            pstmt.setBoolean(5, offering.isGroup());
            pstmt.setBoolean(6, offering.isVisible());
            pstmt.setInt(7, offering.getCapacity());
            pstmt.setInt(8, offering.getEnrolled());
            Integer instructorId = offering.getInstructorId();
            if (instructorId == null) {
                pstmt.setNull(9, Types.INTEGER);  // Set null for instructor_id
            } else {
                pstmt.setInt(9, instructorId);    // Set the actual instructor ID if not null
            }

            pstmt.setString(10, offering.getDate());
            pstmt.setString(11, offering.getOfferingName());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("Offering added successfully with ID: " + id);
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Error adding offering: " + e.getMessage());
        }
        return -1;
    }



        //method for schedule
    public static List<Offering> getClientVisibleOfferings() {
        List<Offering> offerings = new ArrayList<>();
        String sql = "SELECT * FROM offerings WHERE visible = TRUE";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                offerings.add(extractOffering(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving client-visible offerings: " + e.getMessage());
        }
        return offerings;
    }


    public static List<Offering> getInstructorRestrictedOfferings() {
        List<Offering> offerings = new ArrayList<>();
        String sql = "SELECT * FROM offerings WHERE visible = FALSE";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                offerings.add(extractOffering(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving instructor-restricted offerings: " + e.getMessage());
        }
        return offerings;
    }

    private static Offering extractOffering(ResultSet rs) throws SQLException {
        Offering offering = new Offering();
        offering.setId(rs.getInt("id"));
        offering.setLocation(new Location(rs.getInt("location_id")));
        offering.setStartTime(rs.getString("starttime"));
        offering.setEndTime(rs.getString("endtime"));
        offering.setAvailable(rs.getBoolean("available"));
        offering.setGroup(rs.getBoolean("isgroup"));
        offering.setVisible(rs.getBoolean("visible"));
        offering.setCapacity(rs.getInt("capacity"));
        offering.setEnrolled(rs.getInt("enrolled"));
        offering.setInstructor(new Instructor(rs.getInt("instructor_id")));
        offering.setDate(rs.getString("date"));
        offering.setOfferingName(rs.getString("offeringname"));
        return offering;
    }

    public static List<Offering> getAllOfferings() {
        List<Offering> offerings = new ArrayList<>();
        String sql = "SELECT * FROM offerings";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                offerings.add(extractOffering(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all offerings: " + e.getMessage());
        }
        return offerings;
    }

    public static Offering getOfferingDetailsById(int offeringId) {
        String sql = "SELECT * FROM offerings WHERE id = ?";
        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, offeringId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractOffering(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving offering details: " + e.getMessage());
            return null;
        }
    }



}
