package fr.noah.orivas;

import fr.noah.orivas.mysql.DatabaseManager;
import fr.noah.orivas.player.PlayerChatListener;
import fr.noah.orivas.player.PlayerJoinListeners;
import fr.noah.orivas.proxy.listeners.PluginMessageListener;
import fr.noah.orivas.proxy.listeners.ProxyJoinListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public final class Orivas extends JavaPlugin {

    private HashMap<UUID,String> playerRank;//*1 permet de recup UUID / avec le rank associé

    public static Orivas INSTANCE;

    private DatabaseManager databaseManager;

    public void onEnable() {
        INSTANCE = this;

        this.databaseManager = new DatabaseManager();

        this.playerRank = new HashMap<>();//*1

        this.getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListeners(this), this);

        this.getServer().getMessenger().registerIncomingPluginChannel(this, "Channel", new PluginMessageListener());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "Channel");

        this.getServer().getPluginManager().registerEvents(new ProxyJoinListener(), this);

    }
    @Override
    public void onDisable() {
        this.databaseManager.close();
    }

    public HashMap<UUID, String> getPlayerRank() {
        return playerRank;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
