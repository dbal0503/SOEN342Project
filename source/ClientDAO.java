import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
public class ClientDAO {

    public static Integer insertClient(String name, String phoneNumber, int age, Integer guardianId) {
        String sql = "INSERT INTO clients (name, phonenumber, age, guardianid) VALUES (?, ?, ?, ?) RETURNING uniqueid";

        try (Connection conn = Database.connecttoDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setInt(3, age);

            if (guardianId != null) {
                pstmt.setInt(4, guardianId);
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int newClientId = rs.getInt("uniqueid");
                System.out.println("Client inserted successfully with ID: " + newClientId);
                return newClientId;
            }

        } catch (SQLException e) {
            System.out.println("Error inserting client: " + e.getMessage());
        }
        return null;
    }


    public static void getAllClients() {
            String sql = "SELECT * FROM clients";

            try (Connection conn = Database.connecttoDB();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int uniqueId = rs.getInt("uniqueid");
                    String name = rs.getString("name");
                    String phoneNumber = rs.getString("phonenumber");
                    int age = rs.getInt("age");
                    int guardianId = rs.getInt("guardianid");

                    System.out.printf("ID: %d, Name: %s, Phone: %s, Age: %d, Guardian ID: %d%n",
                            uniqueId, name, phoneNumber, age, guardianId);
                }

            } catch (SQLException e) {
                System.out.println("Error querying clients: " + e.getMessage());
            }
        }

        public static Client getClientById(int uniqueId) {
            String sql = "SELECT * FROM clients WHERE uniqueId = ?";

            try (Connection conn = Database.connecttoDB();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, uniqueId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    String phoneNumber = rs.getString("phonenumber");
                    int age = rs.getInt("age");
                    int guardianId = rs.getInt("guardianid");

                    Client guardian = guardianId > 0 ? getClientById(guardianId) : null;
                    return new Client(name, uniqueId, phoneNumber, age, guardian);
                }

            } catch (SQLException e) {
                System.out.println("Error retrieving client: " + e.getMessage());
            }
            return null;
        }

        public static void deleteClient(int uniqueId) {
            String sql = "DELETE FROM clients WHERE uniqueId = ?";

            try (Connection conn = Database.connecttoDB();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, uniqueId);
                pstmt.executeUpdate();
                System.out.println("Client deleted successfully.");

            } catch (SQLException e) {
                System.out.println("Error deleting client: " + e.getMessage());
            }
        }

        public static void updateClient(int uniqueId, String name, String phonenumber, int age, Integer guardianId) {
            String sql = "UPDATE clients SET name = ?, phonenumber = ?, age = ?, guardianid = ? WHERE uniqueid = ?";

            try (Connection conn = Database.connecttoDB();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, name);
                pstmt.setString(2, phonenumber);
                pstmt.setInt(3, age);

                if (guardianId != null) {
                    pstmt.setInt(4, guardianId);
                } else {
                    pstmt.setNull(4, Types.INTEGER);
                }

                pstmt.setInt(5, uniqueId);
                pstmt.executeUpdate();
                System.out.println("Client updated successfully.");

            } catch (SQLException e) {
                System.out.println("Error updating client: " + e.getMessage());
            }
        }


        public static Client getClientByPhoneNumber(String phoneNumber) {
            String sql = "SELECT * FROM clients WHERE phonenumber = ?";

            try (Connection conn = Database.connecttoDB();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, phoneNumber);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int uniqueId = rs.getInt("uniqueid");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    int guardianId = rs.getInt("guardianid");
                    Client guardian = guardianId > 0 ? ClientDAO.getClientById(guardianId) : null;

                    return new Client(name, uniqueId, phoneNumber, age, guardian);
                }

            } catch (SQLException e) {
                System.out.println("Error retrieving client: " + e.getMessage());
            }
            return null;
        }
    }


