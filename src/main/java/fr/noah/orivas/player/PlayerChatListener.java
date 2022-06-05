package fr.noah.orivas.player;

import fr.noah.orivas.Orivas;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class PlayerChatListener implements Listener {

    private Orivas INSTANCE;

    public PlayerChatListener(Orivas instance) {
        INSTANCE = instance;
    }

    @EventHandler
    public void onPlayerChatting(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();

        if(INSTANCE.getPlayerRank().containsKey(uuid)) {//recup le rank du joueur qui parle dans le chat
            final String rank =  INSTANCE.getPlayerRank().get(uuid);

            event.setFormat("§l§a[" + rank + "] " + player.getDisplayName() + " >§r " + event.getMessage());
        }
    }

}
