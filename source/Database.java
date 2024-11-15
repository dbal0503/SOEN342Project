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

                String createLocationsTable = "CREATE TABLE IF NOT EXISTS locations (" +
                        "id SERIAL PRIMARY KEY, " +
                        "address TEXT NOT NULL, " +
                        "city TEXT NOT NULL, " +
                        "room TEXT, " +
                        "organization TEXT" +
                        ");";
                stmt.executeUpdate(createLocationsTable);



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
                        "instructor_id INT NOT NULL, " +
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







/*
    private static final String[] MIGRATIONS = {

            """
        CREATE TABLE IF NOT EXISTS instructors (
            id SERIAL PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            phone_number VARCHAR(20) NOT NULL UNIQUE,
            specialization VARCHAR(255) NOT NULL,
            availabilities TEXT NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )""",


            "CREATE INDEX IF NOT EXISTS idx_instructor_phone ON instructors(phone_number)",
            "CREATE INDEX IF NOT EXISTS idx_instructor_specialization ON instructors(specialization)",


            """
        CREATE OR REPLACE FUNCTION update_updated_at_column()
        RETURNS TRIGGER AS $$
        BEGIN
            NEW.updated_at = CURRENT_TIMESTAMP;
            RETURN NEW;
        END;
        $$ language 'plpgsql'""",


            """
        CREATE OR REPLACE TRIGGER update_instructor_updated_at
            BEFORE UPDATE ON instructors
            FOR EACH ROW
            EXECUTE FUNCTION update_updated_at_column()"""
    };

   /* static {
        try {
            Class.forName("org.postgresql.Driver");
            runMigrations();
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC driver not found");
            e.printStackTrace();
        }
    }



    private static void runMigrations() {
        try (Connection conn = connecttoDB()) {
            if (conn == null) {
                throw new SQLException("Failed to connect to database");
            }

            try (Statement stmt = conn.createStatement()) {
                for (String migration : MIGRATIONS) {
                    try {
                        stmt.execute(migration);
                        System.out.println("Successfully executed migration: " + migration.split("\n")[0] + "...");
                    } catch (SQLException e) {
                        System.err.println("Error executing migration: " + e.getMessage());
                        System.err.println("Failed migration: " + migration);
                        throw e;
                    }
                }
                System.out.println("All migrations completed successfully");
            }
        } catch (SQLException e) {
            System.err.println("Database migration failed: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void initializeDatabase() {
        runMigrations();
    }
    */
}