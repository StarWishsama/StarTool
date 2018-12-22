package top.starwish.startool.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.starwish.startool.config.Config;

public class LevelChatPrefix implements Listener
{
    @EventHandler
    public void ChatLevel(AsyncPlayerChatEvent event)
    {
        if (Config.DefaultEnablePrefix) {
            event.setFormat("§f[§e" + event.getPlayer().getLevel() + "§e级§f]" + event.getFormat());
        }
        else {
            event.setCancelled(true);
        }
    }
}
