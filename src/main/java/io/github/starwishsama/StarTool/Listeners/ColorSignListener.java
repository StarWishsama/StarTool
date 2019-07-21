package io.github.starwishsama.StarTool.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Objects;

public class ColorSignListener implements Listener {
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if (e.getPlayer().hasPermission("startool.colorsign")) {
            for (int i = 0; i <= e.getLines().length; i++) {
                e.setLine(i, Objects.requireNonNull(e.getLine(i)).replaceAll("&", "§"));
            }
        }
    }
}
