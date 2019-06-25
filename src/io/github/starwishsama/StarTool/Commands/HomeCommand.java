package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("home")){
            if (sender instanceof Player){
                Player p = (Player) sender;
                if (p.getBedSpawnLocation() != null) {
                    sender.sendMessage(Utils.color("&bMagicTeleport™ > &a回家将会消耗 1 级经验值哟w"));
                    p.playSound(p.getLocation(), Sound.MUSIC_NETHER, 1, 1);
                    sender.sendMessage(Utils.color("&bStarTool > &a正在传送..."));
                    p.teleport(p.getBedSpawnLocation());
                    p.setLevel(p.getLevel() - 1);
                } else
                    sender.sendMessage(Utils.color("&bStarTool > &c你好像还没有睡过觉吧?"));
            } else
                sender.sendMessage(Utils.color(Utils.getCfg().getString("messages.not-a-player")));
        } return true;
    }
}
