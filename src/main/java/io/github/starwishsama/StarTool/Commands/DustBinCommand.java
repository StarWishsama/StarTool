package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Files.Lang;
import io.github.starwishsama.StarTool.Utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DustBinCommand implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args){
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                Inventory inventory = Bukkit.createInventory(null, 9 * 3, Utils.color("&c&l随身垃圾箱"));
                p.openInventory(inventory);
            } else
                sender.sendMessage(Utils.color(Lang.pluginPrefix + Lang.notPlayer));
        }
        return true;
    }
}
