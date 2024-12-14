import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/BioMedicaLibrary";
    private static final String USER = "Kinetics";
    private static final String PASSWORD = "Loe";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}