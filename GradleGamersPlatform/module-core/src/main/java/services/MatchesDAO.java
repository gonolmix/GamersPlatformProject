package services;

import models.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchesDAO {
    private final Connection connection;

    public MatchesDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Match match) throws SQLException {
        String sql = "INSERT INTO Matches (GameID, Player1ID, Player2ID, Result) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, match.getGameId());
            stmt.setInt(2, match.getPlayer1Id());
            stmt.setInt(3, match.getPlayer2Id());
            stmt.setString(4, match.getResult());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                match.setId(keys.getInt(1));
            }
        }
    }

    public List<Match> getByPlayer(int playerId) throws SQLException {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM Matches WHERE Player1ID = ? OR Player2ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            stmt.setInt(2, playerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                matches.add(mapRow(rs));
            }
        }
        return matches;
    }

    private Match mapRow(ResultSet rs) throws SQLException {
        return new Match(
                rs.getInt("MatchID"),
                rs.getInt("GameID"),
                rs.getInt("Player1ID"),
                rs.getInt("Player2ID"),
                rs.getString("Result")
        );
    }
}
