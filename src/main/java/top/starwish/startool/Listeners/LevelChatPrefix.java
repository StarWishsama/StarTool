package top.starwish.startool.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.starwish.startool.Files.Config;

public class LevelChatPrefix implements Listener
{
    @EventHandler
    public void ChatLevel(AsyncPlayerChatEvent event)
    {
        if (Config.getEnablePrefix) {
            event.setFormat("§f[§e" + event.getPlayer().getLevel() + "§e级§f]" + event.getFormat());
        }
    }
}
