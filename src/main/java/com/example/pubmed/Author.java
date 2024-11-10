package main.java.com.example.pubmed;

public class Author {
    private int id;
    private String name;
    private String affiliation;

    public Author() {}

    public Author(int id, String name, String affiliation) {
        this.id = id;
        this.name = name;
        this.affiliation = affiliation;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }
}
