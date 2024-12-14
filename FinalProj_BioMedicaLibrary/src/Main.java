import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    private static final userManagementInterface userManagement = new userManagement(); // Corrected class name

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
                    try (scanner) {
                        System.out.println("Exiting...");
                        userManagement.closeConnection(); // Close the database connection
                        // Close the scanner
                    }
                    return;
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
                    // Author-specific actions can be added here
                } else {
                    System.out.println("Login failed for Author.");
                }
            }
            case 2 -> {
                if (userManagement.loginAsLearner(username, password)) {
                    System.out.println("Welcome, Learner!");
                    // Learner-specific actions can be added here
                } else {
                    System.out.println("Login failed for Learner.");
                }
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
    }
}