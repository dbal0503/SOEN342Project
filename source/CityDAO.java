import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    public int addCity(City city) throws SQLException {
        String insertSql = "INSERT INTO cities (name) VALUES (?) RETURNING id";
        try (Connection conn = Database.connecttoDB()) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.setString(1, city.getName());

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Failed to create city, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    public City getCityById(int id) throws SQLException {
        String selectStatement = "SELECT * FROM cities WHERE id = ?";
        try (Connection conn = Database.connecttoDB()) {
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(selectStatement);
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new City(rs.getString("name"));
                }
            }
        }
        return null;
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