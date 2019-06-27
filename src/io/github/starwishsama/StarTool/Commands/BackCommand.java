package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (label.toLowerCase().equals("back")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (Config.backLocation.containsKey(p.getUniqueId())) {
                    Location l = Config.backLocation.get(p.getUniqueId());
                    p.teleport(l);
                } else sender.sendMessage(Utils.color(Config.pluginPrefix + "&c你还没死过就不用返回了吧..."));
            } else sender.sendMessage(Utils.color(Config.pluginPrefix + Config.notAPlayer));
        }
        return true;
    }
}
