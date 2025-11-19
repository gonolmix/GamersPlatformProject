package services;

import models.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayersDAO {
    private final Connection connection;

    public PlayersDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Player player) throws SQLException {
        String sql = "INSERT INTO Players (PlayerName) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, player.getName());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                player.setId(keys.getInt(1));
            }
        }
    }

    public Player getById(int id) throws SQLException {
        String sql = "SELECT * FROM Players WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        }
        return null;
    }

    public List<Player> getAll() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Players";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                players.add(mapRow(rs));
            }
        }
        return players;
    }

    public void update(Player player) throws SQLException {
        String sql = "UPDATE Players SET PlayerName = ? WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Players WHERE PlayerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Player mapRow(ResultSet rs) throws SQLException {
        return new Player(
                rs.getInt("PlayerID"),
                rs.getString("PlayerName")
        );
    }
}
