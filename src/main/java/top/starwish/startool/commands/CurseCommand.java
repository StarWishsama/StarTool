package top.starwish.startool.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurseCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("curse")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("startool.curse.use")) {
                    if (args.length == 0) {
                        player.setHealth(0);
                        sender.sendMessage("我 诅 咒 我 自 己");
                    }
                    if (args.length == 1) {
                        if (sender.hasPermission("startool.curse.other")) {
                            Player otherp = Bukkit.getPlayer("args[0]");
                            if (otherp == null) {
                                sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
                            }
                            if (otherp == null && otherp.hasPermission("startool.curse.anticurse")){
                                sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线/无法诅咒!");
                            }
                            else {
                                otherp.setHealth(0);
                                otherp.sendMessage("§bStarTool > §e你被某名玩家加以诅咒!");
                                sender.sendMessage("§bStarTool > §e成功诅咒了 " + otherp.getName() + " !");
                                return true;
                            }
                        } else sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
                    } else sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
                } else sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
            } else sender.sendMessage("§bStarTool > §c你没有权限!");
        } else sender.sendMessage("§bStarTool > §c你必须在游戏内使用该命令!");
        return false;
    }
}


