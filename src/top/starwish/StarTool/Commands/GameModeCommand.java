package top.starwish.StarTool.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.starwish.StarTool.Utils.Utils;

public class GameModeCommand implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gm")){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 0){
                    sender.sendMessage(Utils.color("&bStarTool > &rUsage: /gm <GameMode>"));
                }
                else if (args.length == 1) {
                    switch (args[0]) {
                        case "0":
                            p.setGameMode(GameMode.SURVIVAL);
                            break;
                        case "1":
                            p.setGameMode(GameMode.CREATIVE);
                            break;
                        case "2":
                            p.setGameMode(GameMode.ADVENTURE);
                            break;
                        case "3":
                            p.setGameMode(GameMode.SPECTATOR);
                            break;
                    }
                } else sender.sendMessage(Utils.color("&bStarTool > &rUsage: /gm <GameMode>"));
            } else sender.sendMessage(Utils.color("&bStarTool > &rYou must be a player!"));
        } else sender.sendMessage(Utils.color("&aNMSL"));
        return true;
    }
}
