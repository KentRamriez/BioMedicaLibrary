package main.java.com.example.pubmed;

public class Article {
    private int id;
    private String title;
    private String abstractText;
    private String publicationDate;

    public Article() {}

    public Article(int id, String title, String abstractText, String publicationDate) {
        this.id = id;
        this.title = title;
        this.abstractText = abstractText;
        this.publicationDate = publicationDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}
