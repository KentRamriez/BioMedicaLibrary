package main.java.com.example.pubmed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeywordDAO {
    private Connection connection;

    // Constructor to initialize the database connection
    public KeywordDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to retrieve all keywords from the database
    public List<Keyword> getAllKeywords() throws SQLException {
        List<Keyword> keywords = new ArrayList<>(); // Using ArrayList to store keywords
        String sql = "SELECT * FROM keywords";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Create a new Keyword object and add it to the list
                Keyword keyword = new Keyword(rs.getInt("id"), rs.getString("term"));
                keywords.add(keyword);
            }
        }
        return keywords; // Return the list of keywords
    }

    // Method to add a new keyword to the database
    public void addKeyword(Keyword keyword) throws SQLException {
        String sql = "INSERT INTO keywords (term) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, keyword.getTerm());
            pstmt.executeUpdate(); // Execute the insert statement
        }
    }

    // Method to update an existing keyword in the database
    public void updateKeyword(Keyword keyword) throws SQLException {
        String sql = "UPDATE keywords SET term = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, keyword.getTerm());
            pstmt.setInt(2, keyword.getId());
            pstmt.executeUpdate(); // Execute the update statement
        }
    }

    // Method to delete a keyword from the database
    public void deleteKeyword(int id) throws SQLException {
        String sql = "DELETE FROM keywords WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate(); // Execute the delete statement
        }
    }
}