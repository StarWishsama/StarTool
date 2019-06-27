package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Source code from: https://github.com/MlgmXyysd/Teleport/blob/master/src/main/java/org/meowcat/essential/Main.java
 * @author MlgmXyysd
 * @author StarWishsama
 */

public class ChatListener implements Listener {
    private String worldName = "";

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        if (Config.chatFormat != null) {
            switch (e.getPlayer().getWorld().getName().toLowerCase()){
                case "world":
                    worldName = "&a主世界&r";
                    break;
                case "world_nether":
                    worldName = "&c地狱&r";
                    break;
                case "world_the_end":
                    worldName = "&d末地&r";
                    break;
                default:
                    worldName = e.getPlayer().getWorld().getName();
            }
            if (msg.contains("@")) {
                List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                if (msg.contains("@全体成员")) {
                    for (Player atPlayers : onlinePlayers) {
                        atPlayers.sendMessage(Utils.color("&b[@] &6有人@你" + "&7(" + p.getName() + ")"));
                        atPlayers.playSound(atPlayers.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    }
                    msg = msg.replaceAll("@全体成员", Utils.color("&b@全体成员&r"));
                } else {
                    for (Player atPlayer : onlinePlayers) {
                        if (msg.toLowerCase().contains("@" + atPlayer.getName().toLowerCase())) {
                            msg = msg.replaceAll("@" + atPlayer.getName(), Utils.color("&b@" + atPlayer.getName() + "&r"));
                            atPlayer.sendMessage(Utils.color("&b[@] &6有人@你" + "&7(" + p.getName() + ")"));
                            atPlayer.playSound(atPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                    }
                }
                e.setMessage(msg);
            } else if (msg.contains(Config.posText)) {
                Location l = e.getPlayer().getLocation();
                int X = (int) l.getX();
                int Y = (int) l.getY();
                int Z = (int) l.getZ();
                msg = msg.replaceAll(Config.posText, Utils.color("&b(" + X + "," + Y + "," + Z + ")"));
            }
            e.setFormat(Utils.color(Config.chatFormat.replaceAll("%WorldName%", worldName).replaceAll("%PlayerName%", e.getPlayer().getDisplayName()).replaceAll("%msg%", msg)));
        }
    }
}
