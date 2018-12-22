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