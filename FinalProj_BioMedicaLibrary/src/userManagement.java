import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class userManagement implements userManagementInterface {
    private Connection connection;

    // Constructor to initialize the database connection
    public userManagement() {
        String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=BioMedicaLibrary";
        String user = "Kinetics";
        String password = "Loe";
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    @Override
    public void register(Scanner scanner) throws SQLException {
        // Get user input for registration
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Author/Learner): ");
        String role = scanner.nextLine();

        String sql = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
            System.out.println("Registration successful.");
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
            throw e; // Rethrow the exception for further handling
        }
    }

    @Override
    public boolean loginAsAuthor(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ? AND role = 'Author'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Author login successful.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error during author login: " + e.getMessage());
        }
        return false; // Login failed
    }

    @Override
    public boolean loginAsLearner(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ? AND role = 'Learner'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Learner login successful.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error during learner login: " + e.getMessage());
        }
        return false; // Login failed
    }

    @Override
    public void adminLogin(Scanner scanner) throws SQLException {
        // Admin login logic
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();

        if ("admin".equals(adminUsername) && "admin123".equals(adminPassword)) {
            System.out.println("Admin login successful.");
            // Admin-specific actions can be added here
        } else {
            System.out.println("Admin login failed.");
 }
    }

    // Method to close the database connection
    @Override
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}