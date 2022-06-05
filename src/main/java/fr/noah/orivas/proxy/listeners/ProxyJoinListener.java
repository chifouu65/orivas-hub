package fr.noah.orivas.proxy.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.noah.orivas.Orivas;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ProxyJoinListener implements Listener {

    @EventHandler
    public void ProxyJoin(PlayerJoinEvent event){
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();

        final Player player = event.getPlayer();

        out.writeUTF("GetProfile");
        out.writeUTF(event.getPlayer().getUniqueId().toString());

        Bukkit.getScheduler().scheduleSyncDelayedTask(Orivas.INSTANCE, () -> {
            player.sendPluginMessage(Orivas.INSTANCE, "Channel", out.toByteArray());

        },3L);
    }

}
