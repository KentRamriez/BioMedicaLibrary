package main.java.com.example.pubmed;

import java.util.List;

public class PubMedService {
    private ArticleDAO articleDAO = new ArticleDAO();

    public List<Article> searchArticlesByKeyword(String keyword) {
        return articleDAO.getArticlesByKeyword(keyword);
    }
}