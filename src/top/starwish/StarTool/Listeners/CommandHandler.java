package top.starwish.StarTool.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import top.starwish.StarTool.Utils.Utils;

public class CommandHandler implements Listener {
    @EventHandler
    public void CommandChecker(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().equalsIgnoreCase("/help startool") || e.getMessage().equalsIgnoreCase("/? startool")) {
            e.getPlayer().sendMessage(Utils.color("&bStarTool > &r请使用 /startool 获取帮助."));
            e.setCancelled(true);
        }
    }
}