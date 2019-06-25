package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Config.Config;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatSendMyPos implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (Config.PosTranslate) {
            if (e.getMessage().equalsIgnoreCase(Config.PosText)) {
                Location l = e.getPlayer().getLocation();
                int X = (int) l.getX();
                int Y = (int) l.getY();
                int Z = (int) l.getZ();
                e.setMessage("§b(" + X + "," + Y + "," + Z + ")");
            }
        }
    }
}
