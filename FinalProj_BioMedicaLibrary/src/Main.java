import java.sql.SQLException;
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
        @SuppressWarnings("unused")
        String title = scanner.nextLine();
        System.out.print("Enter article content: ");
        @SuppressWarnings("unused")
        String content = scanner.nextLine();
        // Logic to save the article to the database
        System.out.println("Article submitted successfully!");
    }

    private static void viewSubmittedArticles() {
        // Logic to retrieve and display submitted articles from the database
        System.out.println("Displaying submitted articles...");
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
        // Logic to search for articles in the database
        System.out.println("Searching for articles related to: " + searchTerm);
    }

    private static void viewReadingHistory() {
        // Logic to retrieve and display the learner's reading history
        System.out.println("Displaying reading history...");
    }
}