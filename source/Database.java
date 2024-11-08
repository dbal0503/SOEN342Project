import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
    public static void connecttoDB(){
        System.out.println("Connecting to Database");

        String url = "jdbc:postgresql://localhost:5432/soen342";
        String user = "admin";
        String password = "admin1";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        } 
    }
}