package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class CurseCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("curse")) {
            if (sender.hasPermission("startool.curse.use") || sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage(Utils.color("&bStarTool > &e用法: /curse <玩家名>"));
                } else if (args.length == 1) {
                    Player op = Bukkit.getPlayer(args[0]);
                        if (Utils.isExist(args[0])){
                            if (Objects.requireNonNull(op).hasPermission("startool.commands.curse.anticurse")){
                                if (!op.getName().equalsIgnoreCase(sender.getName())){
                                    op.setHealth(1);
                                    op.setFoodLevel(1);
                                    op.sendMessage(Utils.color("&bStarTool > &e你被诅咒了!"));
                                    sender.sendMessage(Utils.color("&bStarTool > &e成功诅咒了 " + op.getName() + " !"));
                                } else sender.sendMessage(Utils.color("&bStarTool > &c你不能诅咒你自己!"));
                            } else sender.sendMessage(Utils.color(Config.nopermission));
                        } else sender.sendMessage(Utils.color("&bStarTool > &c这名玩家不存在!"));
                } else sender.sendMessage(Utils.color("&bStarTool > &e用法: /curse <玩家名>"));
            } else sender.sendMessage(Utils.color(Utils.getCfg().getString("messages.no-permission")));
        } return true;
    }
}