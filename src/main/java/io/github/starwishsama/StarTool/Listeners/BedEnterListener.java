package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Files.Lang;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedEnterListener implements Listener{
    @EventHandler
    public void onSleep(PlayerBedEnterEvent e){
        if (e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            Bukkit.broadcastMessage(Lang.playerSleepMsg.replaceAll("%s", e.getPlayer().getName()));
        }
    }
}
