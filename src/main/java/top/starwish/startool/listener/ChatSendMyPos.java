package top.starwish.startool.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatSendMyPos implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.getMessage().equalsIgnoreCase("#getpos")){
            Player player = e.getPlayer();
            e.setMessage("§a分享我的坐标: (" + (int)(player.getLocation().getX()) + "," + (int)(player.getLocation().getY()) + "," + (int)(player.getLocation().getZ()) + ")");
            return;
        }
    }
}
