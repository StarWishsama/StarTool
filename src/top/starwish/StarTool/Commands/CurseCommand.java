package top.starwish.StarTool.Commands;

import org.bukkit.entity.Player;
import top.starwish.StarTool.Utils.SPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import top.starwish.StarTool.Utils.TTC;

@SuppressWarnings("deprecation")
public class CurseCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("curse")) {
            if (sender.hasPermission("startool.curse.use") || sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage(TTC.color("&bStarTool > &e用法: /curse <玩家名>"));
                } else if (args.length >= 1) {
                    Player op = Bukkit.getPlayer(args[0]);
                        if (SPlayer.isExist(args[0])){
                            if (op.hasPermission("startool.commands.curse.anticurse")){
                                if (!op.getName().equalsIgnoreCase(sender.getName())){
                                    op.setHealth(1);
                                    op.setFoodLevel(1);
                                    op.sendMessage(TTC.color("&bStarTool > &e你被诅咒了!"));
                                    sender.sendMessage(TTC.color("&bStarTool > &e成功诅咒了 " + op.getName() + " !"));
                                } else sender.sendMessage(TTC.color("&bStarTool > &c你不能诅咒你自己!"));
                            } else sender.sendMessage(TTC.color("&bStarTool > &c你没有权限!"));
                        } else sender.sendMessage(TTC.color("&bStarTool > &c这名玩家不存在!"));
                } else sender.sendMessage(TTC.color("&bStarTool > &e用法: /curse <玩家名>"));
            } else sender.sendMessage(TTC.color("&bStarTool > &c你没有权限!"));
        } return true;
    }
}