package fr.noah.orivas.mysql;

import java.sql.SQLException;

public class DatabaseManager {
    ;
    private DbConnect rankConnection;

    public DatabaseManager() {
        this.rankConnection = new DbConnect(new DbCredentials(
                "localhost",
                "orivasbdd",
                "tK!ZMhelA/B9OJ4Y",
                "orivas",
                3306)
        );

    }

    public void close() {
        try {
            this.rankConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DbConnect getRankConnection() {
        return rankConnection;
    }
}
