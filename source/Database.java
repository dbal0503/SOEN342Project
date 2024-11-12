import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
    public static final String url = System.getenv("DB_URL_342");
    public static final String user = System.getenv("DB_342_USER");
    public static final String password = System.getenv("DB_342_PASSWORD");

    public static Connection connecttoDB(){

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}