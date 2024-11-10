package main.java.com.example.pubmed;

public class Keyword {
    private int id;
    private String term;

    public Keyword() {}

    public Keyword(int id, String term) {
        this.id = id;
        this.term = term;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}