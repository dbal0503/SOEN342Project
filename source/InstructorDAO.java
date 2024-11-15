import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {
    public int addInstructor(Instructor instructor) throws SQLException {
        String insertsql = "INSERT INTO instructors (name, phone_number, specialization, availabilities) VALUES (?,?,?,?) RETURNING id";
        try(Connection conn = Database.connecttoDB();) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insertsql);
            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setString(2, instructor.getPhoneNumber());
            preparedStatement.setString(3, instructor.getSpecialization().getName());
            preparedStatement.setString(4, instructor.getAvailabilities().getCitiesasString());


            try (ResultSet rs = preparedStatement.executeQuery()) { // Use executeQuery for statements returning a result set
                if (rs.next()) {
                    return rs.getInt(1); // Retrieve the generated ID
                } else {
                    throw new SQLException("Failed to create instructor, no ID obtained.");
                }
            }
        }

    }


    public Instructor getInstructorbyphonenumber(String phone_number) throws SQLException{
        String selectstatement = "SELECT * FROM instructors WHERE phone_number = ?";
        try (Connection connection = Database.connecttoDB()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectstatement)){
                preparedStatement.setString(1, phone_number);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("id");
                    String specializationquery = resultSet.getString("specialization");
                    String availabilitiesquery = resultSet.getString("availabilities");
                    Specialization specialization = new Specialization(specializationquery);
                    Availabilities availabilities = Availabilities.parseAvailabilities(availabilitiesquery);
                    return new Instructor(name, phone_number, specialization, availabilities);
                }
            }
        }
        return null;
    }
    public List<Instructor> getAllInstructor() throws SQLException{
        String selectstatement = "SELECT * FROM instructors";
        List<Instructor> instructors = new ArrayList<>();

        try(Connection connection = Database.connecttoDB()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectstatement)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String phonenumber = resultSet.getString("phonenumber");
                    String specializationquery = resultSet.getString("specialization");
                    String availabilitiesquery = resultSet.getString("availabilities");
                    Specialization specialization = new Specialization(specializationquery);
                    Availabilities availabilities = Availabilities.parseAvailabilities(availabilitiesquery);
                    Instructor instructor= new Instructor(name, phonenumber, specialization, availabilities);
                    instructors.add(instructor);
                }
            }
            return instructors;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
    public boolean updateInstructor(Instructor instructor) throws SQLException {
        String insertsql = "UPDATE instructors SET name = ?, phone_number = ?, specialization = ?, availabilities = ?, WHERE id =?";
        try(Connection conn = Database.connecttoDB();) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insertsql);
            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setString(2, instructor.getPhoneNumber());
            preparedStatement.setString(3, instructor.getSpecialization().getName());
            preparedStatement.setString(4, instructor.getAvailabilities().getCitiesasString());
            preparedStatement.setInt(5, instructor.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteInstructor(int id) throws SQLException{
        String deletestatement = "DELETE FROM instructors WHERE id =?";
        try(Connection connection = Database.connecttoDB()){
            assert connection != null;
            PreparedStatement preparedStatement= connection.prepareStatement(deletestatement);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected >0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
