package project;

import junit.framework.TestCase;
import models.Game;
import models.Match;
import models.Player;
import testdb.TestDataBase;

import java.util.List;

public class AppTest extends TestCase {

    private TestDataBase db;

    public void setUp() {
        db = new TestDataBase();
    }

    public void testPlayers() {
        Player alice = db.addPlayer("Alice");
        Player bob = db.addPlayer("Bob");

        assertEquals("Alice", db.getPlayer(alice.getId()).getName());
        assertEquals(2, db.getAllPlayers().size());
    }

    public void testGames() {
        Game g1 = db.addGame("Space", "Action");
        Game g2 = db.addGame("Dragon", "RPG");

        List<Game> rpg = db.getGamesByGenre("RPG");
        assertEquals(1, rpg.size());
        assertEquals("Dragon", rpg.get(0).getTitle());
    }

    public void testMatches() {
        Player p1 = db.addPlayer("Alice");
        Player p2 = db.addPlayer("Bob");
        Game g = db.addGame("Space", "Action");

        Match m = db.addMatch(g.getId(), p1.getId(), p2.getId(), "1-0");

        List<Match> matches = db.getMatchesByPlayer(p1.getId());
        assertEquals(1, matches.size());
        assertEquals("1-0", matches.get(0).getResult());
    }
}
