package top.starwish.startool.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class LevelChat implements Listener
{
    @EventHandler
    public void ChatLevel(AsyncPlayerChatEvent event)
    {
        event.setFormat("§f[§e" + event.getPlayer().getLevel() + "§e级§f]" + event.getFormat());
    }
}
