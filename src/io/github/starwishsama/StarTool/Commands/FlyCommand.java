package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (label.equalsIgnoreCase("fly")){
            if (sender.hasPermission("startool.commands.fly")){
                Player p = (Player) sender;
                if (p.getAllowFlight()){
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    sender.sendMessage(Utils.color(Config.pluginPrefix + "&a飞行已关闭."));
                } else {
                    p.setAllowFlight(true);
                    sender.sendMessage(Utils.color(Config.pluginPrefix + "&a飞行已开启."));
                }
            } else sender.sendMessage(Utils.color(Config.pluginPrefix + Config.noPermission));
        }
        return true;
    }
}
