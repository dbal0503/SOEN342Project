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

    static {
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
}