package main.java.com.example.pubmed;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/keyword_db"; // Update with your database URL
        String user = "your_username"; // Update with your database username
        String password = "your_password"; // Update with your database password

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            KeywordDAO keywordDAO = new KeywordDAO(connection);

            // Add a new keyword
            keywordDAO.addKeyword(new Keyword(0, "Java Programming"));

            // Retrieve and display all keywords
            List<Keyword> keywords = keywordDAO.getAllKeywords();
            for (Keyword keyword : keywords) {
                System.out.println("ID: " + keyword.getId() + ", Term: " + keyword.getTerm());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
