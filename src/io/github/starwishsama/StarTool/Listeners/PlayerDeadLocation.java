package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Config.Config;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeadLocation implements Listener {
    @EventHandler
    public void onPlayerDied(PlayerDeathEvent e){
        Location diedLoc = e.getEntity().getLocation();
        Config.backLocation.put(e.getEntity().getUniqueId(), diedLoc);
    }
}
