package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("debug")){
            if (sender.isOp() || sender.hasPermission("startool.commands.debug") || sender instanceof ConsoleCommandSender){
                String[] s = new String[]{
                        Config.posTranslate + "",
                        Config.posText
                };
                sender.sendMessage(s);
                return true;
            }
        }
        return false;
    }
}
