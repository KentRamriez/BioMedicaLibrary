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
            adminActions(scanner); // Call the method to handle admin actions
        } else {
            System.out.println("Admin login failed.");
        }
    }

    private void adminActions(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\nAdmin Actions:");
            System.out.println("1. View All Users");
            System.out.println("2. Delete User");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAllUsers();
                case 2 -> deleteUser (scanner);
                case 3 -> {
                    System.out.println("Logging out...");
                    return; // Exit the admin actions loop
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllUsers() {
        String sql = "SELECT * FROM Users";
        try (PreparedStatement preparedStatement = connection .prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println("User  List:");
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");
                System.out.println("Username: " + username + ", Role: " + role);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }

    private void deleteUser (Scanner scanner) {
        System.out.print("Enter username to delete: ");
        String usernameToDelete = scanner.nextLine();
        String sql = "DELETE FROM Users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usernameToDelete);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User  " + usernameToDelete + " deleted successfully.");
            } else {
                System.out.println("User  not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
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