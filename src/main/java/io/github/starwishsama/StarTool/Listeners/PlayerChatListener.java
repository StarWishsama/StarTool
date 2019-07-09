package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Commands.AtCommand;
import io.github.starwishsama.StarTool.Files.Config;
import io.github.starwishsama.StarTool.Files.Lang;
import io.github.starwishsama.StarTool.Utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerChatListener implements Listener{
    @EventHandler(priority = EventPriority.LOWEST)
    public void chatListener(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        Player player = e.getPlayer();
        List<Player> pList = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (msg.contains("@全体玩家") || msg.contains("@全体成员") || msg.contains("@all")){
            if (player.hasPermission("startool.at.all")) {
                for (Player p : pList) {
                    if (AtCommand.notifyStatus.containsKey(p.getUniqueId())) {
                        boolean status = AtCommand.notifyStatus.get(p.getUniqueId());
                        if (status) {
                            p.sendMessage(Utils.color("&b@ > &6有人@你" + "&7(" + p.getName() + ")"));
                            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                    } else {
                        p.sendMessage(Utils.color("&b@ > &6有人@你" + "&7(" + p.getName() + ")"));
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    }
                }
                msg = msg.replace("@全体成员", Utils.color("&b@全体成员&r")).replace("@全体玩家", Utils.color("&b@全体玩家&r")).replace("@all", Utils.color("&b@all&r"));
            } else
                player.sendMessage(Lang.pluginPrefix + Lang.noPermission);
        } else if (msg.contains(Config.posText)){
            Location l = e.getPlayer().getLocation();
            int X = (int) l.getX();
            int Y = (int) l.getY();
            int Z = (int) l.getZ();
            msg = msg.replace(Config.posText, "§b(" + X + "," + Y + "," + Z + ")");
        } else {
            for (Player p : pList) {
                if (msg.toLowerCase().contains("@" + p.getName().toLowerCase()) || msg.toLowerCase().contains("@ " + p.getName().toLowerCase())) {
                    msg = msg.replaceAll("@" + p.getName(), Utils.color("&b@" + p.getName() + "&r"))
                            .replaceAll("@" + p.getName() + " ", Utils.color("&b@" + p.getName() + "&r "))
                            .replaceAll("@ " + p.getName(), Utils.color("&b@ " + p.getName() + "&r"))
                            .replaceAll("@" + p.getName().toLowerCase(), Utils.color("&b@" + p.getName() + "&r"))
                            .replaceAll("@" + p.getName().toLowerCase() + " ", Utils.color("&b@" + p.getName() + "&r "))
                            .replaceAll("@ " + p.getName().toLowerCase(), Utils.color("&b@ " + p.getName() + "&r"))
                            .replaceAll("@ " + p.getName(), Utils.color("&b@ " + p.getName() + "&r"));
                    if (AtCommand.notifyStatus.containsKey(p.getUniqueId())) {
                        boolean status = AtCommand.notifyStatus.get(p.getUniqueId());
                        if (status) {
                            p.sendMessage(Utils.color("&b@ > &6有人@你" + "&7(" + p.getName() + ")"));
                            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                    } else {
                        p.sendMessage(Utils.color("&b@ > &6有人@你" + "&7(" + p.getName() + ")"));
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    }
                }
            }
        }
        e.setMessage(msg);
    }

    /**
     * @author BryanSer
     * @see https://github.com/BryanSer/At-Bukkit/blob/master/src/me/Brian/at/Main.java
     */

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        String uncomplete = event.getBuffer();
        if (uncomplete.contains("@") && !uncomplete.endsWith(" ")) {
            String at = uncomplete.substring(uncomplete.lastIndexOf("@") + 1);
            List<String> fit = new ArrayList<>();
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (p.getName().toLowerCase().startsWith(at.toLowerCase())) {
                    if (uncomplete.contains(" ")) {
                        fit.add(uncomplete.substring(
                                uncomplete.lastIndexOf(" "),
                                uncomplete.lastIndexOf("@"))
                                + "@" + p.getName());
                    } else {
                        fit.add(uncomplete.substring(0,
                                uncomplete.lastIndexOf("@"))
                                + "@" + p.getName());
                    }
                }
            }
            event.getCompletions().clear();
            event.getCompletions().addAll(fit);
        }
    }
}
