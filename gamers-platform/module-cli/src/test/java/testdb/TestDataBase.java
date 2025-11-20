package testdb;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataBase {

    private int playerIdCounter = 1;
    private int gameIdCounter = 1;
    private int matchIdCounter = 1;

    private final Map<Integer, Player> players = new HashMap<>();
    private final Map<Integer, Game> games = new HashMap<>();
    private final Map<Integer, Match> matches = new HashMap<>();

    public Player addPlayer(String name) {
        Player p = new Player(playerIdCounter++, name);
        players.put(p.getId(), p);
        return p;
    }

    public Player getPlayer(int id) {
        return players.get(id);
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players.values());
    }

    public boolean deletePlayer(int id) {
        return players.remove(id) != null;
    }

    public Game addGame(String title, String genre) {
        Game g = new Game(gameIdCounter++, title, genre);
        games.put(g.getId(), g);
        return g;
    }

    public Game getGame(int id) {
        return games.get(id);
    }

    public List<Game> getGamesByGenre(String genre) {
        List<Game> result = new ArrayList<>();
        for (Game g : games.values()) {
            if (g.getGenre().equalsIgnoreCase(genre)) {
                result.add(g);
            }
        }
        return result;
    }

    public boolean deleteGame(int id) {
        return games.remove(id) != null;
    }

    public Match addMatch(int gameId, int p1, int p2, String result) {
        Match m = new Match(matchIdCounter++, gameId, p1, p2, result);
        matches.put(m.getId(), m);
        return m;
    }

    public List<Match> getMatchesByPlayer(int playerId) {
        List<Match> result = new ArrayList<>();
        for (Match m : matches.values()) {
            if (m.getPlayer1Id() == playerId || m.getPlayer2Id() == playerId) {
                result.add(m);
            }
        }
        return result;
    }

    public boolean deleteMatch(int id) {
        return matches.remove(id) != null;
    }
}
