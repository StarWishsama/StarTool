package top.starwish.StarTool.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.starwish.StarTool.Utils.Utils;

public class LevelChatPrefix implements Listener {
    @EventHandler
    public void ChatLevel(AsyncPlayerChatEvent event) {
        // From Stiven Ding
            String WorldName = event.getPlayer().getWorld().getName();
            String PlayerName = event.getPlayer().getName();
            String SentMessage = event.getMessage();

        switch (WorldName) {
            case "world":
                WorldName = "主世界";
                break;
            case "world_nether":
                WorldName = "地狱";
                break;
            case "world_the_end":
                WorldName = "末地";
                break;
            default:
                WorldName = event.getPlayer().getWorld().getName();
                break;
        }
            event.setFormat(Utils.color(WorldName + " &f" + PlayerName + " > " + SentMessage));
    }
}
