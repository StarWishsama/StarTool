package top.starwish.StarTool.Listeners;

import org.bukkit.World;
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

            if (WorldName.equals("world")){
                WorldName = "主世界";
            }
            else if (WorldName.equals("world_nether")) {
                WorldName = "地狱";
            }
            else if (WorldName.equals("world_the_end")) {
                WorldName = "末地";
            }
            else WorldName = event.getPlayer().getWorld().getName();
            event.setFormat(Utils.color(WorldName + "&f" + PlayerName + " > " + SentMessage));
    }
}
