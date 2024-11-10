package main.java.com.example.pubmed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public List<Article> getArticlesByKeyword(String keyword) {
        String query = "SELECT a.article_id, a.title, a.abstract, a.publication_date "
                     + "FROM Articles a "
                     + "JOIN Article_Keyword ak ON a.article_id = ak.article_id "
                     + "JOIN Keywords k ON ak.keyword_id = k.keyword_id "
                     + "WHERE k.keyword = ?";
        List<Article> articles = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, keyword);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("article_id"));
                article.setTitle(rs.getString("title"));
                article.setAbstractText(rs.getString("abstract"));
                article.setPublicationDate(rs.getString("publication_date"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
}