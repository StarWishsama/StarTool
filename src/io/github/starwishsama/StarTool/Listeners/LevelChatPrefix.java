package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
        if (!Utils.getCfg().getString("chat-format").isEmpty())
            event.setFormat(Utils.color(Utils.getCfg().getString("chat-format").replaceAll("%WorldName%", WorldName).replaceAll("%PlayerName%", PlayerName).replaceAll("%msg%", SentMessage)));
    }
}
