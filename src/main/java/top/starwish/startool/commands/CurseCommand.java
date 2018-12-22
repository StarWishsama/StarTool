package top.starwish.startool.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurseCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player op = Bukkit.getPlayer(args[0]);

        // Reworked
        if (cmd.getName().equalsIgnoreCase("curse")){
            if (sender.hasPermission("startool.curse.use")) {
                if (args.length == 0) { sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c你不可以诅咒你自己!")); }
                if (args.length == 1 && sender.hasPermission("startool.curse.other")) {
                    if (op.isOnline() && op != null){
                        op.setHealth(1);
                        op.setFoodLevel(1);
                        op.setWalkSpeed(0.25f);
                        op.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e你被诅咒了!"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e成功诅咒了 " + op.getName() + " !"));
                    }
                    if (op == null){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bStarTool > &c这名玩家不存在/不在线!"));
                    }

                    if (op == null && !op.isOnline()){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bStarTool > &c这名玩家不存在/不在线!"));
                    }

                    if (op != null && op.isOnline() && op.hasPermission("startool.curse.anticurse")){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bStarTool > &c这名玩家无法诅咒!"));
                    }
                } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c你没有权限!"));
            } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c你没有权限!"));
        } return false;
    }
}
 /*       Player otherp = Bukkit.getPlayer("args[0]");
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("curse")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("startool.curse.use")) {
                    if (args.length == 0) {
                        player.setHealth(0);
                        sender.sendMessage("我 诅 咒 我 自 己");
                    } else sender.sendMessage("§bStarTool > §c发生了意外之中的错误.");
                    if (args.length == 1 && sender.hasPermission("startool.curse.other")) {
                           if (otherp == null) {
                                sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线!");
                               if (otherp.hasPermission("startool.curse.anticurse")) {
                                   sender.sendMessage("§bStarTool > §c这名玩家不存在/不在线/无法诅咒!");
                               } else {
                                   otherp.setHealth(1);
                                   otherp.setFoodLevel(1);
                                   otherp.setWalkSpeed(0.25f);
                                   otherp.sendMessage("§bStarTool > §e你被某名玩家诅咒了!");
                                   sender.sendMessage("§bStarTool > §e成功诅咒了 " + otherp.getName() + " !");
                                   return true;
                               }
                           } else sender.sendMessage("");
                          } else sender.sendMessage("§bStarTool > §c你没有权限!");
                        } else sender.sendMessage("§bStarTool > §c你没有权限!");
                } else sender.sendMessage("§bStarTool > §c你必须在游戏内使用该命令!");
            } **/


