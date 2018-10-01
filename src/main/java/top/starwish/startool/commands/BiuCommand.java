package top.starwish.startool.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// WIP
public class BiuCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("km")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("startool.killmother")) {
                    if (args.length == 0) {
                        player.setHealth(0);
                        sender.sendMessage("Testing...");
                        sender.sendMessage("§bMotherKiller!");
                    }
                    if (args.length == 1) {
                        if (sender.hasPermission("startool.killmother.other")) {
                            Player otherp = (Player) Bukkit.getPlayer("args[0]");
                            if (otherp == null) {
                                sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
                            } else {
                                otherp.setHealth(0);
                                otherp.sendMessage("§bStarTool > §e你被龙的传人 §c" + sender.getName() + " §eKilled your mother!");
                                sender.sendMessage("§bStarTool > §e成功杀害玩家 " + otherp.getName() + " §e的亲妈!");
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


