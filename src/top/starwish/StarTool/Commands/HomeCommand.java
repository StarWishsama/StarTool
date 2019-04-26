package top.starwish.StarTool.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.starwish.StarTool.Utils.Utils;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("home")){
            if (sender instanceof Player){
                Player p = (Player)sender;
                if (p.getBedSpawnLocation() != null) {
                    p.teleport(p.getBedSpawnLocation());
                } else
                    sender.sendMessage(Utils.color("&bStarTool > &r你好像还没有睡过觉吧?"));
            } else
                sender.sendMessage(Utils.color(Utils.getCfg().getString("messages.not-a-player")));
        } return true;
    }
}
