package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Entity.PlayerHomes;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bed")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (p.getBedSpawnLocation() != null) {
                    sender.sendMessage(Utils.color("&bMagicTeleport™ > &a正在传送..."));
                    p.playSound(p.getLocation(), Sound.MUSIC_NETHER, 1, 1);
                    p.teleport(p.getBedSpawnLocation());
                    p.setLevel(p.getLevel() - 1);
                } else
                    sender.sendMessage(Utils.color("&bStarTool > &c你好像还没有睡过觉吧?"));
            } else sender.sendMessage(Utils.color(Config.plugin_prefix + Config.notaplayer));
        } else if (label.toLowerCase().equals("home")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (Config.playerHomes.containsKey(p.getUniqueId())){
                    if (Config.playerHomes.get(p.getUniqueId()).getPlayerHomes().containsKey(args[0])){
                        sender.sendMessage(Utils.color(Config.plugin_prefix + "&a正在传送..."));
                        p.teleport(Config.playerHomes.get(p.getUniqueId()).getPlayerHomes().get(args[0]));
                    } else sender.sendMessage(Utils.color(Config.plugin_prefix + "&c你输入了错误的家的名字!"));
                } else sender.sendMessage(Utils.color(Config.plugin_prefix + "&c你还没有设置过家!"));
            } else sender.sendMessage(Utils.color(Config.plugin_prefix + Config.notaplayer));
        } else if (label.toLowerCase().equals("sethome")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (args.length == 0){
                    sender.sendMessage(Utils.color(Config.plugin_prefix + Config.usage + "&c/sethome <家的名字> "));
                } else if (args.length == 1){
                    Location l = p.getLocation();
                    Map<String, Location> home = new HashMap<>();
                    home.put(args[0], l);
                    PlayerHomes homes = new PlayerHomes();
                    homes.setPlayerUUID(p.getUniqueId());
                    homes.setPlayerHomes(home);
                    Config.playerHomes.put(p.getUniqueId(), homes);
                    sender.sendMessage(Utils.color(Config.plugin_prefix + "&a家 " + args[0] + " 设置完成!"));
                }
            } else sender.sendMessage(Utils.color(Config.plugin_prefix + Config.notaplayer));
        }
        return true;
    }
}
