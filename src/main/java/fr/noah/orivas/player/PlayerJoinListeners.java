package fr.noah.orivas.player;

import fr.noah.orivas.Orivas;
import fr.noah.orivas.mysql.DbConnect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.UUID;

public class PlayerJoinListeners implements Listener {

    private Orivas INSTANCE;

    public PlayerJoinListeners(Orivas INSTANCE) {
        this.INSTANCE = INSTANCE;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();
        final DbConnect rankConnect = INSTANCE.getDatabaseManager().getRankConnection();
        final Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskAsynchronously(INSTANCE,() -> {
            try {
                final Connection connection = rankConnect.getConnection();                   // https://prnt.sc/1RjEQevR7AUZ //https://prnt.sc/Qb1-PzfpS2I9
                final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, rank FROM player_rank WHERE uuid=?");
                //1 = au premier ? [TOP] == = récup l'uuid du joueur le transforme en string
                preparedStatement.setString(1, uuid.toString());
                final ResultSet resultSet = preparedStatement.executeQuery();//retourne un resultset

                if (resultSet.next()) {
                    final String rank = resultSet.getString("rank");//pour recup la colone "rank" dans la bdd
                    player.sendMessage("§l§eSucces Account §aload");

                    INSTANCE.getPlayerRank().put(uuid, rank);
                } else { //si il n'y a pas de result on doit metre le joueur dans la bdd car il existent pas encore
                    createAccount(connection, uuid);
                    System.out.println("Account " + event.getPlayer().getDisplayName() + "Is add on BDD");
                    event.getPlayer().sendMessage("§l§eSucces ! Account §aRegistered §e§lin BDD");
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createAccount(Connection connection, UUID uuid) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO player_rank VALUES (?,?,?,?)");
            final long time = System.currentTimeMillis();

            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, "Joueur");
            preparedStatement.setTimestamp(3, new Timestamp(time));
            preparedStatement.setTimestamp(4, new Timestamp(time));
            preparedStatement.executeUpdate();
//afet this is this ->
            INSTANCE.getPlayerRank().put(uuid, "Joueur");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
