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
        if (cmd.getName().equalsIgnoreCase("biu")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("startool.biu")) {
                    if (args.length == 0) {
                        player.setHealth(0);
                        sender.sendMessage("Testing...");
                        sender.sendMessage("§bStarTool > §e你被苟管理Biu了一下!惊喜吧!");
                    }
                    if (args.length == 1) {
                        if (sender.hasPermission("starwish.biu.other")) {
                            Player otherp = (Player) Bukkit.getPlayer("args[0]");
                            if (otherp == null) {
                                sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
                            }
                            otherp.setHealth(0);
                            otherp.sendMessage("§bStarTool > §e你被苟管理 §c" + sender.getName() + " §eBiu了一下!惊喜吧!");
                            sender.sendMessage("§bStarTool > §e成功击杀玩家 " + otherp.getName() + " §e!");
                            return true;
                        } else sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
                    } else sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
                } else sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
            } else sender.sendMessage("§bStarTool > §c你没有权限!");
        } else sender.sendMessage("§bStarTool > §c你必须在游戏内使用该命令!");
        return false;
    }
}


