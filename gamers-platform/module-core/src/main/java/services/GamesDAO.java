package services;

import models.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GamesDAO {
    private final Connection connection;

    public GamesDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Game game) throws SQLException {
        String sql = "INSERT INTO Games (Title, Genre) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, game.getTitle());
            stmt.setString(2, game.getGenre());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                game.setId(keys.getInt(1));
            }
        }
    }

    public List<Game> getByGenre(String genre) throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Games WHERE Genre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, genre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                games.add(mapRow(rs));
            }
        }
        return games;
    }

    private Game mapRow(ResultSet rs) throws SQLException {
        return new Game(
                rs.getInt("GameID"),
                rs.getString("Title"),
                rs.getString("Genre")
        );
    }
}
