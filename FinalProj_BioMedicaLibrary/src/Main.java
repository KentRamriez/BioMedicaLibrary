import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    private static final userManagementInterface userManagement = new userManagement();

    public static void main(String[] args) {
        displayInterface();
    }

    private static void displayInterface() {
        while (true) {
            System.out.println("Welcome to BioMedicaLibrary");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    try {
                        userManagement.register(scanner); // Pass the scanner to the register method
                    } catch (SQLException e) {
                        System.out.println("Registration failed: " + e.getMessage());
                    }
                }
                case 2 -> userLogin(); // Call the user login method
                case 3 -> {
                    try {
                        userManagement.adminLogin(scanner); // Pass the scanner to adminLogin
                    } catch (SQLException e) {
                        System.out.println("Admin login failed: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("Exiting...");
                    userManagement.closeConnection(); // Close the database connection
                    return; // Exit the application
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void userLogin() {
        System.out.println("Select your role:");
        System.out.println("1. Login as Author");
        System.out.println("2. Login as Learner");
        System.out.print("Choose an option: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        switch (roleChoice) {
            case 1 -> {
                if (userManagement.loginAsAuthor(username, password)) {
                    System.out.println("Welcome, Author!");
                    authorActions(); // Call Author-specific actions
                } else {
                    System.out.println("Login failed for Author.");
                }
            }
            case 2 -> {
                if (userManagement.loginAsLearner(username, password)) {
                    System.out.println("Welcome, Learner!");
                    learnerActions(); // Call Learner-specific actions
                } else {
                    System.out.println("Login failed for Learner.");
                }
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private static void authorActions() {
        while (true) {
            System.out.println("Author Actions:");
            System.out.println("1. Submit Article");
            System.out.println("2. View Submitted Articles");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> submitArticle(); // Method to handle article submission
                case 2 -> viewSubmittedArticles(); // Method to view submitted articles
                case 3 -> {
                    System.out.println("Logging out...");
                    return; // Exit the author actions loop
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void submitArticle() {
        System.out.print("Enter article title: ");
        String title = scanner.nextLine();
        System.out.print("Enter article content: ");
        String content = scanner.nextLine();

        String sql = "INSERT INTO Articles (title, content, authorUsername) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = ((Statement) userManagement).getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, userManagement.getCurrentUsername()); // Get the current username
            preparedStatement.executeUpdate();
            System.out.println("Article submitted successfully!");
        } catch (SQLException e) {
            System.out.println("Error submitting article: " + e.getMessage());
        }
    }

    private static void viewSubmittedArticles() {
        String sql = "SELECT title, content FROM Articles WHERE authorUsername = ?";
        try (PreparedStatement preparedStatement = ((Statement) userManagement).getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, userManagement.getCurrentUsername()); // Get the current username
            ResultSet resultSet = preparedStatement.executeQuery();
            
            System.out.println("Submitted Articles:");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                System.out.println("Title: " + title);
                System.out.println("Content: " + content);
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving submitted articles: " + e.getMessage());
        }
    }

    private static void learnerActions() {
        while (true) {
            System.out.println("Learner Actions:");
            System.out.println("1. Search Articles");
            System.out.println("2. View Reading History");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> searchArticles(); // Method to handle article search
                case 2 -> viewReadingHistory(); // Method to view reading history
                case 3 -> {
                    System.out.println("Logging out...");
                    return; // Exit the learner actions loop
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void searchArticles() {
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine();
        String sql = "SELECT title, content FROM Articles WHERE title LIKE ? OR content LIKE ?";
        
        try (PreparedStatement preparedStatement = ((Statement) userManagement).getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setString(2, "%" + searchTerm + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            
            System.out.println("Search Results:");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                System.out.println("Title: " + title);
                System.out.println("Content: " + content);
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error searching for articles: " + e.getMessage());
        }
    }

    private static void viewReadingHistory() {
        String sql = "SELECT articleTitle, readDate FROM ReadingHistory WHERE username = ?";
        try (PreparedStatement preparedStatement = ((Statement) userManagement).getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, userManagement.getCurrentUsername()); // Get the current username
            ResultSet resultSet = preparedStatement.executeQuery();
            
            System.out.println("Reading History:");
            while (resultSet.next ()) {
                String articleTitle = resultSet.getString("articleTitle");
                String readDate = resultSet.getString("readDate");
                System.out.println("Article Title: " + articleTitle + ", Read Date: " + readDate);
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving reading history: " + e.getMessage());
        }
    }
}