package io.github.starwishsama.StarTool.Commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import io.github.starwishsama.StarTool.Utils.Utils;

public class PortableEnderChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("enderchest")){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!sender.isOp()){
                    p.setLevel(p.getLevel() - 1);
                    p.sendMessage(Utils.color("&StarTool > &e利用魔法打开末影箱消耗了 1 级经验等级w"));
                }
                p.openInventory(p.getEnderChest());
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                return true;
            }
        }
        return false;
    }
}
