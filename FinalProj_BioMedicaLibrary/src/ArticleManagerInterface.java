import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ArticleManagerInterface {
    void addArticle(String title, String author, String abstractText, String keywords, Date publishedDate) throws SQLException;
    List<Article> filterArticlesByKeywords(String keyword) throws SQLException;
}