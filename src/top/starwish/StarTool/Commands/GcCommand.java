package top.starwish.StarTool.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import top.starwish.StarTool.StarToolStartup;
import top.starwish.StarTool.Utils.ServerInfo;
import top.starwish.StarTool.Utils.Utils;

public class GcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gc")){
            if (sender.hasPermission("startool.commands.gc") || sender instanceof ConsoleCommandSender){
                sender.sendMessage(Utils.color("&a操作系统: " + ServerInfo.getOsName()));
                sender.sendMessage(Utils.color("&aJVM 版本:" + ServerInfo.getJVMVersion()));
                sender.sendMessage(Utils.color("&e内存使用情况: " + ServerInfo.getUsedMemory() + "MB/" + ServerInfo.getTotalMemorySize() + "MB"));
            }
            else sender.sendMessage(Utils.color(Utils.getCfg().getString("messages.no-permission")));
        } return true;
    }
}
