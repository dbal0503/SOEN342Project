import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {
    public boolean addInstructor(Instructor instructor) throws SQLException {
        String insertsql = "INSERT INTO instructors (name, id, phone_number, specialization, availabilities) VALUES (?,?,?,?,?)";
        try(Connection conn = Database.connecttoDB();) {
            assert conn != null;
            PreparedStatement preparedStatement = conn.prepareStatement(insertsql);
            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setInt(2, instructor.getId());
            preparedStatement.setString(3, instructor.getPhoneNumber());
            preparedStatement.setString(4, instructor.getSpecialization().getName());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
