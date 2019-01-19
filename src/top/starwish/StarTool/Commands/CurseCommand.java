package top.starwish.StarTool.Commands;

import top.starwish.StarTool.Misc.SPlayer;
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
            if (sender.isOp()){
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c你不可以诅咒你自己!"));
                    if (args.length == 1 && sender instanceof Player && SPlayer.isnotExist(args[0])){
                        op.setHealth(1);
                        op.setFoodLevel(1);
                        op.setWalkSpeed(0.25f);
                        op.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e你被诅咒了!"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e成功诅咒了 " + op.getName() + " !"));
                        if (op.isOp()){
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c这名玩家无法诅咒!"));
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c此玩家不在线!"));
                    }

                    if (args.length == 1 && sender instanceof Player && args[0].equalsIgnoreCase("heal")){
                            Player p = (Player) sender;
                            p.setHealth(20);
                            p.setFoodLevel(10);
                            p.setWalkSpeed(1f);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e诅咒已消除."));
                    }
                }
            } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c你没有权限!"));
        }
        return false;
    }
}