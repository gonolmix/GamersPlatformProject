package models;

public class Match {
    private int id;
    private int gameId;
    private int player1Id;
    private int player2Id;
    private String result;

    public Match(int id, int gameId, int player1Id, int player2Id, String result) {
        this.id = id;
        this.gameId = gameId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.result = result;
    }

    public Match(int gameId, int player1Id, int player2Id, String result) {
        this.gameId = gameId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { // <-- важно
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public int getPlayer1Id() {
        return player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    public String getResult() {
        return result;
    }
}
