package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

// WIP
public class TeleportCommand implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch (label.toLowerCase()) {
            case "tpa":
                if (sender instanceof Player){
                    switch (args.length){
                        case 0:
                            sender.sendMessage(Utils.color(Config.pluginPrefix + " &e用法: /tpa [玩家名字]"));
                            break;
                        case 1:
                            if (Utils.isExist(args[0])){
                                sender.sendMessage(Utils.color(Config.pluginPrefix + " &e已向玩家 " + args[0] + " 发送传送请求"));
                                Config.tpRequests.put(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getUniqueId(), ((Player) sender).getUniqueId());
                                Player tpPlayer = Bukkit.getPlayer(args[0]);
                                Objects.requireNonNull(tpPlayer).sendMessage(Utils.color("&b" + sender.getName() + " 想要传送到你的位置."));
                                tpPlayer.sendMessage("");
                                tpPlayer.sendMessage(Utils.color("&a /tpyes 或 /tpaccept 以接受传送请求"));
                                tpPlayer.sendMessage(Utils.color("&c /tpno 或 /tpdeny 以拒绝传送请求"));
                                tpPlayer.sendMessage("");
                            } else sender.sendMessage(Utils.color(Config.pluginPrefix + "&c该玩家不在线或不存在!"));
                            break;
                    }
                } else sender.sendMessage(Utils.color(Config.pluginPrefix + Config.notAPlayer));
                break;
            case "tpahere":
                if (sender instanceof Player){
                    Player p = (Player) sender;
                    if (args.length == 0){
                        sender.sendMessage(Utils.color(Config.pluginPrefix + "&e用法: /tpahere [玩家名字]"));
                    } else if (args.length == 1){
                        if (Utils.isExist(args[0])){
                            Player tpPlayer = Bukkit.getPlayer(args[0]);
                            sender.sendMessage(Utils.color(Config.pluginPrefix + " &e已向玩家 " + args[0] + " 发送传送请求"));
                            Config.tpaHereRequests.put(Objects.requireNonNull(tpPlayer).getUniqueId(), p.getUniqueId());
                            Objects.requireNonNull(tpPlayer).sendMessage(Utils.color("&b" + sender.getName() + " 想要你传送到TA的位置."));
                            tpPlayer.sendMessage("");
                            tpPlayer.sendMessage(Utils.color("&a /tpyes 或 /tpaccept 以接受传送请求"));
                            tpPlayer.sendMessage(Utils.color("&c /tpno 或 /tpdeny 以拒绝传送请求"));
                            tpPlayer.sendMessage("");
                        } else sender.sendMessage(Utils.color(Config.pluginPrefix + "&c该玩家不在线或不存在!"));
                    }
                } else sender.sendMessage(Utils.color(Config.pluginPrefix + Config.notAPlayer));
                break;
            case "tpaccept":
                if (sender instanceof Player){
                    Player p = (Player) sender;
                    if (Config.tpRequests.containsKey(p.getUniqueId())){
                        Player tpPlayer = Objects.requireNonNull(Bukkit.getPlayer(Config.tpRequests.get(p.getUniqueId()))).getPlayer();
                        Objects.requireNonNull(tpPlayer).sendMessage(Utils.color(Config.pluginPrefix + "&e正在传送至 " + p.getName()));
                        Objects.requireNonNull(tpPlayer).teleport(p.getLocation());
                        tpPlayer.playSound(p.getLocation(), Sound.MUSIC_NETHER, 1, 1);
                        Config.tpRequests.remove(p.getUniqueId());
                    } else if (Config.tpaHereRequests.containsKey(p.getUniqueId())){
                        Player tpaPlayer = Objects.requireNonNull(Bukkit.getPlayer(Config.tpaHereRequests.get(p.getUniqueId()))).getPlayer();
                        p.sendMessage(Utils.color(Config.pluginPrefix + "&e正在传送至 " + Objects.requireNonNull(tpaPlayer).getDisplayName()));
                        p.teleport(tpaPlayer.getLocation());
                        p.playSound(tpaPlayer.getLocation(), Sound.MUSIC_NETHER, 1, 1);;
                        Config.tpaHereRequests.remove(p.getUniqueId());
                    } else sender.sendMessage(Utils.color(Config.pluginPrefix + "&c你没有待处理的传送请求哟喵w"));
                } else sender.sendMessage(Utils.color(Config.pluginPrefix + " &c你必须是游戏内的玩家!"));
                break;
            case "tpdeny":
                if (sender instanceof Player){
                    Player p = (Player) sender;
                    if (Config.tpRequests.containsKey(p.getUniqueId())){
                        p.sendMessage(Utils.color(Config.pluginPrefix + "&e传送请求已销毁."));
                        Config.tpRequests.remove(p.getUniqueId());
                    } else if (Config.tpaHereRequests.containsKey(p.getUniqueId())){
                        p.sendMessage(Utils.color(Config.pluginPrefix + "&e传送请求已销毁."));
                        Config.tpaHereRequests.remove(p.getUniqueId());
                    } else sender.sendMessage(Utils.color(Config.pluginPrefix + "&c你没有待处理的传送请求哟喵w"));
                } else sender.sendMessage(Utils.color(Config.pluginPrefix + "&c你必须是游戏内的玩家!"));
                break;
        }
        return true;
    }
}
