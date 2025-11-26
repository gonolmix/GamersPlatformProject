package by.gonol.project;

import connection.AppProperties;
import connection.ConnectionManager;
import models.Game;
import models.Match;
import models.Player;
import services.GamesDAO;
import services.MatchesDAO;
import services.PlayersDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            AppProperties.getInstance().load();
            Connection connection = ConnectionManager.getInstance().getConnection();

            GamesDAO gamesDAO = new GamesDAO(connection);
            PlayersDAO playersDAO = new PlayersDAO(connection);
            MatchesDAO matchesDAO = new MatchesDAO(connection);

            // Создаём игроков
            Player player1 = new Player("Alice");
            Player player2 = new Player("Bob");
            Player player3 = new Player("Charlie");
            playersDAO.create(player1);
            playersDAO.create(player2);
            playersDAO.create(player3);

            // Создаём игры
            Game game1 = new Game("Space Adventure", "Action");
            Game game2 = new Game("Mystery Manor", "Puzzle");
            Game game3 = new Game("Fantasy Quest", "RPG");
            gamesDAO.create(game1);
            gamesDAO.create(game2);
            gamesDAO.create(game3);

            // Создаём матчи
            Match match1 = new Match(game1.getId(), player1.getId(), player2.getId(), "1-0");
            Match match2 = new Match(game2.getId(), player1.getId(), player3.getId(), "0-1");
            Match match3 = new Match(game3.getId(), player2.getId(), player3.getId(), "1-1");
            matchesDAO.create(match1);
            matchesDAO.create(match2);
            matchesDAO.create(match3);

            System.out.println("Матчи для Alice:");
            List<Match> matches = matchesDAO.getByPlayer(player1.getId());
            matches.forEach(m ->
                    System.out.println("MatchID=" + m.getId() +
                            ", GameID=" + m.getGameId() +
                            ", P1=" + m.getPlayer1Id() +
                            ", P2=" + m.getPlayer2Id() +
                            ", Result=" + m.getResult())
            );

            ConnectionManager.getInstance().closeConnection();

        } catch (SQLException e) {
            System.err.println("Ошибка работы с базой данных: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка приложения: " + e.getMessage());
        }
    }
}
