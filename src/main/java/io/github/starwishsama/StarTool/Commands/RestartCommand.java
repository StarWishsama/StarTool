package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Files.Lang;
import io.github.starwishsama.StarTool.StarToolMain;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Date;

public class RestartCommand implements CommandExecutor {
    public static Long timeToRestart;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender.hasPermission("startool.commands.restart") || sender instanceof ConsoleCommandSender) {
            if (args.length == 0) {
                Bukkit.dispatchCommand(sender, "restart");
            } else if (args.length == 1) {
                if (StringUtils.isNumeric(args[0])) {
                    Date now = new Date();
                    timeToRestart = now.getTime() + Integer.parseInt(args[0]) * 1000;
                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", args[0])));
                    StarToolMain.doRestart();
                }
            }
        } else
            sender.sendMessage(Utils.color(Lang.pluginPrefix + Lang.noPermission));
        return false;
    }
}
