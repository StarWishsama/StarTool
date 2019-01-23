package top.starwish.StarTool.Listeners;

import org.bukkit.World;
import top.starwish.StarTool.Files.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class LevelChatPrefix implements Listener
{
    @EventHandler
    public void ChatLevel(AsyncPlayerChatEvent event)
    {
        if (Config.getEnablePrefix) {
            String WorldName = event.getPlayer().getWorld().getName();
            switch (WorldName){
                case "world":
                    event.setFormat("§a主世界§f "+ event.getPlayer().getName() + " >" + event.getMessage());
                case "world_nether":
                    event.setFormat("§a地狱§f "+ event.getPlayer().getName() + " >" + event.getMessage());
                case "world_the_end":
                    event.setFormat("§a末地§f "+ event.getPlayer().getName() + " >" + event.getMessage());
                default:
                    event.setFormat("§a" + event.getPlayer().getWorld().getName() +"§f "+ event.getPlayer().getName() + " >" + event.getMessage());
            }
        }
    }
}
