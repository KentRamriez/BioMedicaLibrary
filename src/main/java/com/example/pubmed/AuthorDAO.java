package main.java.com.example.pubmed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    // Constructor to initialize the database connection
    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new author
    public void addAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO authors (name, affiliation) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.setString(2, author.getAffiliation());
            pstmt.executeUpdate();
        }
    }

    // Method to retrieve an author by ID
    public Author getAuthorById(int id) throws SQLException {
        String sql = "SELECT * FROM authors WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Author(rs.getInt("id"), rs.getString("name"), rs.getString("affiliation"));
            }
        }
        return null;
    }

    // Method to update an author's information
    public void updateAuthor(Author author) throws SQLException {
        String sql = "UPDATE authors SET name = ?, affiliation = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.setString(2, author.getAffiliation());
            pstmt.setInt(3, author.getId());
            pstmt.executeUpdate();
        }
    }

    // Method to delete an author
    public void deleteAuthor(int id) throws SQLException {
        String sql = "DELETE FROM authors WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Method to retrieve all authors
    public List<Author> getAllAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                authors.add(new Author(rs.getInt("id"), rs.getString("name"), rs.getString("affiliation")));
            }
        }
        return authors;
    }
}
