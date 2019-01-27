package top.starwish.StarTool.Commands;

import org.bukkit.entity.Player;
import top.starwish.StarTool.Utils.SPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CurseCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player op = Bukkit.getPlayer(args[0]);
        if (cmd.getName().equalsIgnoreCase("curse")){
            if (sender.isOp()){
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e用法: /curse <玩家名>"));
                }
                else if (args.length > 0){
                    if (args.length == 1){
                        if (args[0].equalsIgnoreCase("debug")){
                            if (sender instanceof Player) {
                                ((Player) sender).setWalkSpeed(0.8f);
                                sender.sendMessage("Your speed have been set to 0.8(float).");
                            } else sender.sendMessage("This Command only for player in game.");
                        }
                        else if (SPlayer.isExist(args[0]) && !args[0].equalsIgnoreCase("debug")){
                            if (!op.isOp()) {
                                if (!op.getName().equalsIgnoreCase(sender.getName())) {
                                    op.setHealth(1);
                                    op.setFoodLevel(1);
                                    op.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e你被诅咒了!"));
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e成功诅咒了 " + op.getName() + " !"));
                                } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c你不能诅咒你自己!"));
                            } sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c这名玩家无法诅咒!"));
                        } else return true;
                    }
                } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c执行命令时发生了错误!"));
            } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c你没有权限!"));
        }
        return true;
    }
}