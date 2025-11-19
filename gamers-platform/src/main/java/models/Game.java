package models;

public class Game {
    private int id;
    private String title;
    private String genre;

    public Game(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public Game(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { // <-- важно
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}
