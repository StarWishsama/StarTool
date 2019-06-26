package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * code from: https://github.com/MlgmXyysd/Teleport/blob/master/src/main/java/org/meowcat/essential/Main.java
 * @author MlgmXyysd
 */

public class AtListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        Player player = event.getPlayer();
        if (msg.contains("@")) {
            List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
            if (msg.contains("@全体成员")) {
                for (Player players : onlinePlayers) {
                    players.sendMessage(Utils.color("&b[@] 有人@你 &a"+ player.getDisplayName()));
                    players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                }
                msg = msg.replaceAll("@全体成员", ChatColor.AQUA + "@全体成员" + ChatColor.RESET);
            } else {
                for (Player players : onlinePlayers) {
                    if (msg.toLowerCase().contains("@" + players.getName().toLowerCase())) {
                        msg = msg.replaceAll("(?i)@ " + players.getName() + " ", Utils.color("&b@" + players.getName() + "&r "));
                        msg = msg.replaceAll("(?i)@ " + players.getName(), Utils.color("&b@" + players.getName() + "&r "));
                        msg = msg.replaceAll("(?i)@" + players.getName(), Utils.color("&b@" + players.getName() + "&r "));
                        players.sendMessage(Utils.color("&b[@] 有人@你: &a" + player.getDisplayName()));
                        players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    }
                }
            }
            event.setMessage(msg);
        }
    }
}
