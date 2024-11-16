import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfferingDAO {

    public static List<Offering> getOfferingsByInstructorId(int instructorId) {
        List<Offering> offerings = new ArrayList<>();
        String sql = "SELECT o.* FROM offerings o " +
                "JOIN instructors i ON o.instructor_id = i.id " + // Join offerings to instructors
                "WHERE i.id = ?"; // Match the instructor_id

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, instructorId); // Set the instructor ID in the query

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    offerings.add(extractOffering(rs)); // Extract the offering details
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving offerings by instructor ID: " + e.getMessage());
        }

        return offerings;
    }
    public static List<Offering> getOfferingsByInstructorCity(int instructorId) {
        List<Offering> offerings = new ArrayList<>();
        String sql = "SELECT o.* FROM offerings o " +
                "JOIN locations l ON o.location_id = l.id " + // Join offerings to locations
                "JOIN instructor_cities ic ON ic.city_id = l.city_id " + // Join instructor_cities to locations via city_id
                "WHERE ic.instructor_id = ? " + // Match the instructor_id with the city_id
                "AND o.instructor_id IS NULL";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, instructorId); // Set the instructor ID in the query

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    offerings.add(extractOffering(rs)); // Extract the offering details
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving offerings by instructor's city: " + e.getMessage());
        }

        return offerings;
    }


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
    public static List<Offering> getAllOfferingsByInstructorCities(String instructorCities) {
        List<Offering> offerings = new ArrayList<>();
        String sql = "SELECT * FROM offerings o " +
                "JOIN locations l ON o.location_id = l.id " +
                "WHERE LOWER(l.city) IN (%s)";


        String[] citiesArray = instructorCities.split(",");

        // Trim and normalize all cities to lowercase for comparison
        for (int i = 0; i < citiesArray.length; i++) {
            citiesArray[i] = citiesArray[i].trim().toLowerCase();
        }

        // Prepare placeholders for the number of cities
        String citiesPlaceholder = String.join(",", Collections.nCopies(citiesArray.length, "?"));

        // Format the SQL query to include the placeholders
        sql = String.format(sql, citiesPlaceholder);

        try (Connection conn = Database.connecttoDB()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Set the cities in the prepared statement
                for (int i = 0; i < citiesArray.length; i++) {
                    pstmt.setString(i + 1, citiesArray[i]);
                }

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    offerings.add(extractOffering(rs)); // Assuming extractOffering is defined to convert ResultSet to Offering
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving offerings by instructor cities: " + e.getMessage());
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
    public boolean assignInstructorToOffering(int offeringId, int instructorId) {
        String sql = "UPDATE offerings SET instructor_id = ? WHERE id = ?";
        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorId);
            pstmt.setInt(2, offeringId);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error assigning instructor to offering: " + e.getMessage());
        }
        return false;
    }

    public static boolean offeringAlreadyExists(Offering offering) {
        String sql = "SELECT * FROM offerings o " +
                "JOIN locations l ON o.location_id = l.id " +
                "WHERE o.starttime = ? AND o.endtime = ? " +
                "AND o.date = ? AND o.location_id = ?";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, offering.getStartTime());
            pstmt.setString(2, offering.getEndTime());
            pstmt.setString(3, offering.getDate());
            pstmt.setInt(4, offering.getLocation().getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking for existing offering: " + e.getMessage());
        }

        return false;
    }





}
