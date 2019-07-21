package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Files.Lang;
import io.github.starwishsama.StarTool.StarToolMain;
import io.github.starwishsama.StarTool.Utils.Utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RestartCommand implements CommandExecutor, TabCompleter {
    public static Long timeToRestart;
    private String[] subCommand = {"cancel"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (label.equalsIgnoreCase("srestart")) {
            if (sender.hasPermission("startool.commands.restart") || sender instanceof ConsoleCommandSender) {
                switch (args.length) {
                    case 0:
                        sender.sendMessage(Utils.color(Lang.pluginPrefix + "&c/srestart <时间>"));
                        sender.sendMessage(Utils.color(Lang.pluginPrefix + "&c/srestart cancel 取消当前的重启计时器"));
                        break;
                    case 1:
                        if (StringUtils.isNumeric(args[0])) {
                            Date now = new Date();
                            timeToRestart = now.getTime() + Integer.parseInt(args[0]) * 1000;
                            Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", args[0])));
                            StarToolMain.doRestart();
                        } else if (args[0].equalsIgnoreCase("cancel")) {
                            timeToRestart = -1L;
                            sender.sendMessage(Utils.color(Lang.pluginPrefix + "&a取消成功"));
                        } else {
                            sender.sendMessage(Utils.color(Lang.pluginPrefix + "&c/srestart <时间>"));
                            sender.sendMessage(Utils.color(Lang.pluginPrefix + "&c/srestart cancel 取消当前的重启计时器"));
                        }
                        break;
                }
            } else
                sender.sendMessage(Utils.color(Lang.pluginPrefix + Lang.noPermission));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0)
            return Arrays.asList(subCommand);
        if (args.length > 1)
            return new ArrayList<>();
        return Arrays.stream(subCommand).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
