import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager implements ArticleManagerInterface {
    private Connection connection;

    // Constructor to initialize the database connection
    public ArticleManager(String dbUrl, String user, String password) {
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    // Method to add a new article to the database
    @Override
    public void addArticle(String title, String author, String abstractText, String keywords, Date publishedDate) throws SQLException {
        String sql = "INSERT INTO Articles (title, author, abstractText, keywords, publishedDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, abstractText);
            preparedStatement.setString(4, keywords);
            preparedStatement.setDate(5, publishedDate);
            preparedStatement.executeUpdate();
            System.out.println("Article added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding article: " + e.getMessage());
            throw e; // Rethrow the exception for further handling
        }
    }

    // Method to filter articles by keywords
    @Override
    public List<Article> filterArticlesByKeywords(String keyword) throws SQLException {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM Articles WHERE keywords LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String abstractText = resultSet.getString("abstractText");
                String keywordsResult = resultSet.getString("keywords");
                Date publishedDate = resultSet.getDate("publishedDate");

                Article article = new Article(title, author, abstractText, keywordsResult, publishedDate);
                articles.add(article);
            }
        } catch (SQLException e) {
            System.out.println("Error filtering articles: " + e.getMessage());
            throw e; // Rethrow the exception for further handling
        }
        return articles;
    }

    // Method to close the database connection
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