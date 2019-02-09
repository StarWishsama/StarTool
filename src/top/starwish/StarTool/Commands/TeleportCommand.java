package top.starwish.StarTool.Commands;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.starwish.StarTool.Utils.Utils;

// WIP
public class TeleportCommand implements CommandExecutor {
    public static Boolean sendStatus;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tp")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("startool.commands.teleport") || sender.isOp()) {
                    if (args.length == 0) {
                        sender.sendMessage(Utils.color("&bStarTool > &e用法: /tp <玩家名字>"));
                    }
                    else if (args.length == 1) {
                        if (Utils.isExist(args[0])) {
                            Location l = Bukkit.getPlayer(args[0]).getLocation();
                            ((Player) sender).teleport(l);
                            Bukkit.getPlayer(args[0]).sendMessage(Utils.color("&bStarTool > " + ((Player) sender).getDisplayName() + " 已传送到你的位置!"));
                        } else sender.sendMessage(Utils.color("&bStarTool > &c该玩家不在线或不存在!"));
                    }
                    else if (args.length > 1){
                        if (Utils.isExist(args[0]) && StringUtils.isNumeric(args[1]) && StringUtils.isNumeric(args[2]) && StringUtils.isNumeric(args[3])){
                            Player p = Bukkit.getPlayer(args[0]);
                            double x = (double)Integer.parseInt(args[1]);
                            double y = (double)Integer.parseInt(args[2]);
                            double z = (double)Integer.parseInt(args[3]);
                            p.teleport(new Location(p.getWorld(), x, y, z));
                        }
                        else if (StringUtils.isNumeric(args[0]) && StringUtils.isNumeric(args[1]) && StringUtils.isNumeric(args[2])){
                            double x = (double)Integer.parseInt(args[1]);
                            double y = (double)Integer.parseInt(args[2]);
                            double z = (double)Integer.parseInt(args[3]);
                            ((Player) sender).teleport(new Location(((Player) sender).getWorld(), x ,y, z));
                        }
                        else sender.sendMessage(Utils.color("&bStarTool > &e用法: /tp [玩家名字] <x> <y> <z>"));
                    }
                    else sender.sendMessage(Utils.color("&bStarTool > &e用法: /tp <玩家名字>"));
                } else sender.sendMessage(Utils.color("&bStarTool > &c你没有权限!"));
            } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
        } else if (cmd.getName().equalsIgnoreCase("tphere")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("startool.commands.teleport.tphere")) {
                    if (args.length == 0) {
                        sender.sendMessage(Utils.color("&bStarTool > &rUsage: /tphere <Player>"));
                    } else if (args.length == 1) {
                        if (Utils.isExist(args[0])) {
                            if (!args[0].equalsIgnoreCase(sender.getName())) {
                                Location l = ((Player) sender).getLocation();
                                Bukkit.getPlayer(args[0]).teleport(l);
                            } else sender.sendMessage("You can't tphere yourself!");
                        } else sender.sendMessage(Utils.color("&bStarTool > &c该玩家不在线或不存在!"));
                    } else sender.sendMessage(Utils.color("&bStarTool > &rUsage: /tphere <Player>"));
                } else sender.sendMessage(Utils.color("&bStarTool > &c你没有权限!"));
            } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
        } else if (cmd.getName().equalsIgnoreCase("tpa")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("startool.commands.teleport.tpa")) {
                    if (args.length == 0) {
                        sender.sendMessage(Utils.color("&bStarTool > &e用法: /tpa <玩家名字>"));
                    } else if (args.length == 1) {
                        if (Utils.isExist(args[0])) {
                            Player op = Bukkit.getPlayer(args[0]);
                            sender.sendMessage(Utils.color("&6传送请求已发送给 &a" + op));
                            op.sendMessage(Utils.color("&a" + sender.getName() + " &6请求传送到你的位置"));
                            op.sendMessage(Utils.color(""));
                            op.sendMessage(Utils.color("&a输入 /tpaccept 以接受传送请求"));
                            op.sendMessage(Utils.color("&c输入 /tpdeny 以拒绝传送请求"));
                            op.sendMessage(Utils.color(""));
                            sendStatus = Boolean.valueOf(true);
                        } else sender.sendMessage(Utils.color("&bStarTool > &c该玩家不在线或不存在!"));
                    } else sender.sendMessage(Utils.color("&bStarTool > &e用法: /tpa <玩家名字>"));
                } else sender.sendMessage(Utils.color("&bStarTool > &c你没有权限!"));
            } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
        } else {
            sender.sendMessage(Utils.color("&bStarTool > &e用法: /tp <玩家名字>"));
        }
        return true;
    }
}
