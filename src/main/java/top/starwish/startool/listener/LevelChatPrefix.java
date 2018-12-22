package top.starwish.startool.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.starwish.startool.config.configsetup;

public class LevelChatPrefix implements Listener
{
    @EventHandler
    public void ChatLevel(AsyncPlayerChatEvent event)
    {
        if (configsetup.EnableChatPrefix) {
            event.setFormat("§f[§e" + event.getPlayer().getLevel() + "§e级§f]" + event.getFormat());
        }
        else {
            event.setCancelled(true);
        }
    }
}
