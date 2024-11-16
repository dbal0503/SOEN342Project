import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    //static {initDatabase();}

    public static void initDatabase(){
        Connection connection = connecttoDB();

        if (connection != null) {
            try (Statement stmt = connection.createStatement()) {
                String createCityTable = "CREATE TABLE IF NOT EXISTS cities (" +
                        "id SERIAL PRIMARY KEY, " +
                        "name TEXT NOT NULL)";
                stmt.executeUpdate(createCityTable);

                String createLocationsTable = "CREATE TABLE IF NOT EXISTS locations (" +
                        "id SERIAL PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "city_id INT NOT NULL," +
                        "address TEXT NOT NULL," +
                        "FOREIGN KEY (city_id) REFERENCES cities(id))";
                stmt.executeUpdate(createLocationsTable);

                String createInstructorsTable = "CREATE TABLE IF NOT EXISTS instructors (" +
                        "id SERIAL PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "phone_number TEXT UNIQUE NOT NULL," +
                        "specialization TEXT," +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
                stmt.executeUpdate(createInstructorsTable);



                String createClientsTable = "CREATE TABLE IF NOT EXISTS clients (" +
                        "uniqueid SERIAL PRIMARY KEY," +
                        "name TEXT," +
                        "phonenumber TEXT," +
                        "age INT," +
                        "guardianid INT)";
                stmt.executeUpdate(createClientsTable);



                String createOfferingsTable = "CREATE TABLE IF NOT EXISTS offerings (" +
                        "id SERIAL PRIMARY KEY, " +
                        "location_id INT NOT NULL, " +
                        "starttime TEXT NOT NULL, " +
                        "endtime TEXT NOT NULL, " +
                        "available BOOLEAN DEFAULT TRUE, " +
                        "isgroup BOOLEAN NOT NULL, " +
                        "visible BOOLEAN DEFAULT FALSE, " +
                        "capacity INT NOT NULL CHECK (capacity > 0), " +
                        "enrolled INT DEFAULT 0 CHECK (enrolled >= 0), " +
                        "instructor_id INT, " +
                        "date TEXT NOT NULL, " +
                        "offeringname TEXT NOT NULL, " +
                        "FOREIGN KEY (location_id) REFERENCES locations(id), " +
                        "FOREIGN KEY (instructor_id) REFERENCES instructors(id))";
                stmt.executeUpdate(createOfferingsTable);

                String createBookingsTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                        "id SERIAL PRIMARY KEY," +
                        "client_id INT," +
                        "offering_id INT," +
                        "booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                        "status TEXT," +
                        "FOREIGN KEY (client_id) REFERENCES clients(uniqueid)," +
                        "FOREIGN KEY (offering_id) REFERENCES offerings(id))";
                stmt.executeUpdate(createBookingsTable);



                String createInstructorCitiesTable = "CREATE TABLE IF NOT EXISTS instructor_cities (" +
                        "instructor_id INT," +
                        "city_id INT," +
                        "PRIMARY KEY (instructor_id, city_id)," +
                        "FOREIGN KEY (instructor_id) REFERENCES instructors(id)," +
                        "FOREIGN KEY (city_id) REFERENCES cities(id))";
                stmt.executeUpdate(createInstructorCitiesTable);


                System.out.println("Tables created or verified successfully.");

            } catch (SQLException e) {
                System.err.println("Error creating tables: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close(); // Close th connect
                    }
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        } else {
            System.err.println("Failed to connect to the database.");
        }
    }

}