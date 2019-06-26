package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("debug")){
            if (sender.isOp() || sender.hasPermission("startool.commands.debug")){
                String[] s = new String[]{
                        Config.PosTranslate + "",
                        Config.PosText
                };
                sender.sendMessage(s);
                return true;
            }
        }
        return false;
    }
}
