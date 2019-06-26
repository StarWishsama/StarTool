package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AutoTranslatePos implements Listener {
    @EventHandler
    public void ChatLevel(AsyncPlayerChatEvent event) {
        String SentMsg = event.getMessage();
            if (Config.PosTranslate) {
                if (SentMsg.contains(Config.PosText) && !Config.PosText.isEmpty()) {
                    Location l = event.getPlayer().getLocation();
                    int X = (int) l.getX();
                    int Y = (int) l.getY();
                    int Z = (int) l.getZ();
                    SentMsg.replaceFirst(Config.PosText, Utils.color("&b(" + X + "," + Y + "," + Z + ")"));
                }
            }
    }
}
