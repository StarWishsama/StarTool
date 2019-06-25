package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AutoWelcome implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String JoinMessage = Utils.getCfg().getString("PlayerJoinMessage");
        Player p = e.getPlayer();
        p.sendMessage(Utils.color(JoinMessage));
    }
}
