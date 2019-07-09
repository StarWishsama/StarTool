package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Files.Lang;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class AtCommand implements CommandExecutor, TabCompleter {
    public static final Map<UUID, Boolean> notifyStatus = new HashMap<>();
    private String[] subCommand = {"notify"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (args.length > 0){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("notify")) {
                    if (notifyStatus.containsKey(p.getUniqueId())){
                        boolean status = notifyStatus.get(p.getUniqueId());
                        if (status) {
                            notifyStatus.put(p.getUniqueId(), false);
                            sender.sendMessage(Utils.color(Lang.pluginPrefix + Lang.atNotifyOff));
                        } else {
                            notifyStatus.put(p.getUniqueId(), true);
                            sender.sendMessage(Utils.color(Lang.pluginPrefix + Lang.atNotifyOn));
                        }
                    } else {
                        notifyStatus.put(p.getUniqueId(), false);
                        sender.sendMessage(Utils.color(Lang.pluginPrefix + Lang.atNotifyOff));
                    }
                }
            } else
                sender.sendMessage(Utils.color(Lang.pluginPrefix + Lang.notPlayer));
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
