package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Files.Lang;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class LevelUpTips implements Listener {
    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        Player p = e.getPlayer();
        if (e.getNewLevel() < e.getOldLevel())
            return;
        p.sendMessage(Utils.color(Lang.pluginPrefix + "&r" + e.getPlayer().getName() + ", 恭喜你升级至 " + e.getNewLevel() + "级!"));
    }
}
