package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Objects.Home;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

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
                } else
                    sender.sendMessage(Utils.color("&bStarTool > &c你好像还没有睡过觉吧?"));
            } else sender.sendMessage(Utils.color(Config.pluginPrefix + Config.notAPlayer));
        } else if (label.toLowerCase().equals("home")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (Config.playerHomes.containsKey(p.getUniqueId())){
                    for (int i = 0; i < Config.playerHomes.get(p.getUniqueId()).size(); i++){
                        if (Config.playerHomes.get(p.getUniqueId()).get(i).getHomeName().equalsIgnoreCase(args[0])){
                            sender.sendMessage(Utils.color(Config.pluginPrefix + "&a正在传送..."));
                            p.teleport(Config.playerHomes.get(p.getUniqueId()).get(i).getHomeLocation().get(args[0]));
                            p.playSound(p.getLocation(), Sound.MUSIC_NETHER, 1, 1);
                        } else {
                            sender.sendMessage(Utils.color(Config.pluginPrefix + "&c你输入了错误的家的名字!"));
                            break;
                        }
                    }
                } else sender.sendMessage(Utils.color(Config.pluginPrefix + "&c你还没有设置过家!"));
            } else sender.sendMessage(Utils.color(Config.pluginPrefix + Config.notAPlayer));
        } else if (label.toLowerCase().equals("sethome")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (args.length == 0){
                    sender.sendMessage(Utils.color(Config.pluginPrefix + Config.usage + "&c/sethome <家的名字> "));
                } else if (args.length == 1){
                    if (!Config.playerHomes.containsKey(p.getUniqueId())) {
                        Location l = p.getLocation();
                        Map<String, Location> homesList = new HashMap<>();
                        homesList.put(args[0], l);
                        Home home = new Home();
                        home.setHomeLocation(homesList);
                        home.setHomeName(args[0]);
                        List<Home> list = new ArrayList<>();
                        list.add(home);
                        Config.playerHomes.put(p.getUniqueId(), list);
                    } else {
                        Location l = p.getLocation();
                        Map<String, Location> homesMap = new HashMap<>();
                        homesMap.put(args[0], l);
                        List<Home> home = Config.playerHomes.get(p.getUniqueId());
                        Home newHome = new Home();
                        newHome.setHomeLocation(homesMap);
                        newHome.setHomeName(args[0]);
                        home.add(newHome);
                    }
                    sender.sendMessage(Utils.color(Config.pluginPrefix + "&a家 " + args[0] + " 设置完成!"));
                    //Config.saveHomes();
                }
            } else sender.sendMessage(Utils.color(Config.pluginPrefix + Config.notAPlayer));
        }
        return true;
    }
}
