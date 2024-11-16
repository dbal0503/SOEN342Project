import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InstructorDAO {

    public Instructor getInstructorByPhoneNumber(String phone_number) throws SQLException {
        String selectStatement = "SELECT * FROM instructors WHERE phone_number = ?";
        try (Connection connection = Database.connecttoDB()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectStatement)) {
                preparedStatement.setString(1, phone_number);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Get basic instructor info
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("id");
                    String specializationName = resultSet.getString("specialization");

                    // Create specialization object
                    Specialization specialization = new Specialization(specializationName);

                    List<City> instructorCities = getInstructorCities(id);

                    // Create availabilities object with the cities
                    Availabilities availabilities = new Availabilities(instructorCities);

                    // Create and return the instructor object
                    Instructor instructor = new Instructor(name, phone_number, specialization, availabilities);
                    instructor.setId(id);
                    return instructor;
                }
            }
        }
        return null;
    }


    public boolean isPhoneNumberExists(String phoneNumber) throws SQLException {
        String query = "SELECT COUNT(*) FROM instructors WHERE phone_number = ?";
        try (Connection connection = Database.connecttoDB()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, phoneNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        }
        return false;
    }
    // Add instructor cities
    private void addInstructorCities(Connection conn, int instructorId, List<City> cities) throws SQLException {
        String insertSql = "INSERT INTO instructor_cities (instructor_id, city_id) VALUES (?, ?)";
             PreparedStatement stmt = conn.prepareStatement(insertSql);

            for (City city : cities) {
                CityDAO cityDAO = new CityDAO();
                int cityId = cityDAO.getCityIdByName(city.getName());
                if (cityId != -1) {
                    stmt.setInt(1, instructorId);
                    stmt.setInt(2, cityId);
                    stmt.executeUpdate();

            }
        }
    }

    // Get instructor cities
    private List<City> getInstructorCities(int instructorId) throws SQLException {
        String query = "SELECT c.name FROM cities c " +
                "JOIN instructor_cities ic ON c.id = ic.city_id " +
                "WHERE ic.instructor_id = ?";
        List<City> cities = new ArrayList<>();

        try (Connection conn = Database.connecttoDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, instructorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cities.add(new City(rs.getString("name")));
            }
        }
        return cities;
    }

    public int addInstructor(Instructor instructor) throws SQLException {
        String insertSql = "INSERT INTO instructors (name, phone_number, specialization) VALUES (?,?,?) RETURNING id";
        try (Connection conn = Database.connecttoDB()) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setString(2, instructor.getPhoneNumber());
            preparedStatement.setString(3, instructor.getSpecialization().getName());

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    int instructorId = rs.getInt(1);
                    addInstructorCities(conn, instructorId, instructor.getAvailabilities().getCities());
                    return instructorId;
                } else {
                    throw new SQLException("Failed to create instructor, no ID obtained.");
                }
            }
        }
    }

}