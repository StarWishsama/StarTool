package top.starwish.StarTool.Listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.starwish.StarTool.StarToolStartup;
import top.starwish.StarTool.Utils.Utils;

public class ChatSendMyPos implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (Utils.getCfg().getBoolean("PosTranslate")) {
            if (e.getMessage().equalsIgnoreCase(StarToolStartup.getInstance().getConfig().getString("PosText"))) {
                Location l = e.getPlayer().getLocation();
                int X = (int) l.getX();
                int Y = (int) l.getY();
                int Z = (int) l.getZ();
                e.setMessage("§b(" + X + "," + Y + "," + Z + ")");
                return;
            }
        }
    }
}
