package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHandler implements Listener {
    @EventHandler
    public void CommandChecker(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().equalsIgnoreCase("/help startool") || e.getMessage().equalsIgnoreCase("/? startool")) {
            e.getPlayer().sendMessage(Utils.color("&bStarTool > &r请使用 /startool 获取帮助."));
            e.setCancelled(true);
        }
    }
}
