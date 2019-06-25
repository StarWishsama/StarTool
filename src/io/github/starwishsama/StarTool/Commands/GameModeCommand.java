package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gm")){
            if (sender instanceof Player) {
                if (sender.hasPermission("startool.commands.gamemode.use")) {
                    Player p = (Player) sender;
                    if (args.length == 0) {
                        sender.sendMessage(Utils.color(Config.plugin_prefix + Config.usage + " /gm <GameMode>"));
                    } else if (args.length == 1) {
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
                    } else sender.sendMessage(Utils.color(Config.plugin_prefix + Config.usage + " /gm <GameMode>"));
                } else sender.sendMessage(Utils.color(Config.plugin_prefix + Config.nopermission));
            } else sender.sendMessage(Utils.color(Config.plugin_prefix + Config.notaplayer));
        } return true;
    }
}
