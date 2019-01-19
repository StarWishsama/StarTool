package top.starwish.StarTool.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import top.starwish.StarTool.Files.Config;

public class AutoWelcome implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getJoinMessage));
        return;
    }
}
