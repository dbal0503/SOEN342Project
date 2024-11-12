import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
    private static Connection connection = null;
    public static Connection connecttoDB(){
        System.out.println("Connecting to Database");

        String url = System.getenv("DB_URL_342");
        String user = System.getenv("DB_342_USER");
        String password = System.getenv("DB_342_PASSWORD");

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        return connection;
    }
}