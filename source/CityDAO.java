import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {

    public List<City> getAllCities() throws SQLException {
        String selectStatement = "SELECT * FROM cities";
        List<City> cities = new ArrayList<>();

        try (Connection conn = Database.connecttoDB();
             PreparedStatement stmt = conn.prepareStatement(selectStatement);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                cities.add(new City(name));
            }
        }
        return cities;
    }


    public int getCityIdByName(String name) throws SQLException {
        String selectStatement = "SELECT id FROM cities WHERE name = ?";
        try (Connection conn = Database.connecttoDB()) {
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setString(1, name.toLowerCase());

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    System.out.println(rs.getInt("id"));
                    return rs.getInt("id");
                }
            }
        }
        return -1;
    }
}