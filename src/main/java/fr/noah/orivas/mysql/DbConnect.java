package fr.noah.orivas.mysql;

import java.awt.*;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnect {

    private DbCredentials databaseCredentials;
    private Connection connection;

    public DbConnect(DbCredentials dbCredentials) {
        this.databaseCredentials = dbCredentials;
        this.connect();
    }

    private void connect() {
        try {
            this.connection = DriverManager.getConnection(
                    this.databaseCredentials.toURL(),
                    this.databaseCredentials.getUser(),
                    this.databaseCredentials.getPass());
            Logger.getLogger("Minecraft").info( "====  Database IS ENABLE  ====");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        if (this.connection != null) {
            if (!this.connection.isClosed()) {
                this.connection.close();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        if(this.connection != null) {
            if(!this.connection.isClosed()) {
                return this.connection;
            }
        }
        connect();
        return this.connection;
    }

}