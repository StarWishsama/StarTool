package top.starwish.StarTool.Listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatSendMyPos implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
           if (e.getMessage().equalsIgnoreCase("pos")) {
               Location l = e.getPlayer().getLocation();
               int X = (int)l.getX();
               int Y = (int)l.getY();
               int Z = (int)l.getZ();
               e.setMessage("§b(" + X + "," + Y + "," + Z + ")");
               return;
           }
    }
}
