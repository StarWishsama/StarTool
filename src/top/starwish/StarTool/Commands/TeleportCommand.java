package top.starwish.StarTool.Commands;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.starwish.StarTool.Utils.Utils;

import java.util.HashMap;

// WIP
public class TeleportCommand implements CommandExecutor {
    private HashMap<Player, Player> tpList = new HashMap<>();

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch (cmd.getName().toLowerCase()) {
            case "tp":
                if (sender instanceof Player) {
                    if (sender.hasPermission("startool.commands.teleport") || sender.isOp()) {
                        switch (args.length) {
                            case 0:
                                sender.sendMessage(Utils.color("&bStarTool > &e用法: /tp <玩家名字>"));
                                break;
                            case 1:
                                if (Utils.isExist(args[0])) {
                                    Location l = Bukkit.getPlayer(args[0]).getLocation();
                                    ((Player) sender).teleport(l);
                                    Bukkit.getPlayer(args[0]).sendMessage(Utils.color("&bStarTool > " + ((Player) sender).getDisplayName() + " 已传送到你的位置!"));
                                } else sender.sendMessage(Utils.color("&bStarTool > &c该玩家不在线或不存在!"));
                                break;
                            case 2:
                                if (StringUtils.isNumeric(args[0]) && StringUtils.isNumeric(args[1]) && StringUtils.isNumeric(args[2])) {
                                    double x = (double) Integer.parseInt(args[1]);
                                    double y = (double) Integer.parseInt(args[2]);
                                    double z = (double) Integer.parseInt(args[3]);
                                    ((Player) sender).teleport(new Location(((Player) sender).getWorld(), x, y, z));
                                }
                                break;
                            case 3:
                                if (Utils.isExist(args[0]) && StringUtils.isNumeric(args[1]) && StringUtils.isNumeric(args[2]) && StringUtils.isNumeric(args[3])) {
                                    double x = (double) Integer.parseInt(args[1]);
                                    double y = (double) Integer.parseInt(args[2]);
                                    double z = (double) Integer.parseInt(args[3]);
                                    Bukkit.getPlayer(args[0]).teleport(new Location(Bukkit.getPlayer(args[0]).getWorld(), x, y, z));
                                }
                                break;
                            default:
                                sender.sendMessage(Utils.color("&bStarTool > &e用法: /tp [玩家名字] <x> <y> <z>"));
                                break;
                        }
                    } else sender.sendMessage(Utils.color(Utils.getCfg().getString("messages.no-permission")));
                } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
                break;
            case "tpa":
                if (sender instanceof Player){
                    switch (args.length){
                        case 0:
                            sender.sendMessage(Utils.color("&bStarTool > &e用法: /tpa [玩家名字]"));
                            break;
                        case 1:
                            if (Utils.isExist(args[0])){
                                sender.sendMessage(Utils.color("&bStarTool > &e已向玩家 " + args[0] + " 发送传送请求"));
                                tpList.put(Bukkit.getPlayer(args[0]), (Player) sender);
                                Player tpPlayer = Bukkit.getPlayer(args[0]);

                                tpPlayer.sendMessage(Utils.color("&b" + sender.getName() + " 想要传送到你的位置."));
                                tpPlayer.sendMessage("");
                                tpPlayer.sendMessage(Utils.color("&a /tpyes 或 /tpaccept 以接受传送请求"));
                                tpPlayer.sendMessage(Utils.color("&c /tpno 或 /tpdeny 以拒绝传送请求"));
                                tpPlayer.sendMessage("");
                            } else sender.sendMessage(Utils.color("&bStarTool > &c该玩家不在线或不存在!"));
                            break;
                    }
                } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
                break;
            case "tpyes":
            case "tpaccept":
                if (sender instanceof Player){
                    if (tpList.containsKey(sender)){
                        sender.sendMessage(Utils.color("&bStarTool > &e正在传送..."));
                        Location location = tpList.get(sender).getLocation();
                        ((Player) sender).teleport(location);
                        tpList.remove(sender);
                    }
                } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
                break;
            case "tpno":
            case "tpdeny":
                if (sender instanceof Player){
                    if (tpList.containsKey(sender)){
                        sender.sendMessage(Utils.color("&bStarTool > &e传送请求已销毁."));
                        tpList.remove(sender);
                    }
                } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
                break;
        }
        return true;
    }
}
