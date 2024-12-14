import java.sql.Date;

public class Article {
    private String title;
    private String author;
    private String abstractText;
    private String keywords;
    private Date publishedDate;

    // Constructor
    public Article(String title, String author, String abstractText, String keywords, Date publishedDate) {
        this.title = title;
        this.author = author;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.publishedDate = publishedDate;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter and Setter for abstractText
    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    // Getter and Setter for keywords
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    // Getter and Setter for publishedDate
    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}