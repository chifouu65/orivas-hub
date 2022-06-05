package fr.noah.orivas.proxy.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PluginMessageListener implements org.bukkit.plugin.messaging.PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {

        if(channel.equals("Channel")) {
            final ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            final String sub = in.readUTF();

            if(sub.equals("GetProfile")) {
                final int id = in.readInt();
                final String uuid = in.readUTF();
                final String team = in.readUTF();
                final float coins = in.readFloat();
                final int level = in.readInt();
                final String rank = in.readUTF();
                final int kill = in.readInt();

                player.sendMessage(id + team + coins + rank + kill);
                player.sendMessage(id +
                        "----------" +
                        uuid +
                        "----------" +
                        ChatColor.GRAY +team +
                        "----------" +
                        ChatColor.LIGHT_PURPLE +coins +
                        "----------" +
                        ChatColor.DARK_GREEN + level +
                        "----------" +
                        ChatColor.BLUE+ rank +
                        "----------" +
                                ChatColor.YELLOW + kill
                );
            }
        }
    }
}
