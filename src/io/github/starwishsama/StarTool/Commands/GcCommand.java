package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Utils.ServerInfo;
import io.github.starwishsama.StarTool.Utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class GcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gc")){
            if (sender.hasPermission("startool.commands.gc") || sender instanceof ConsoleCommandSender){
                if (args.length > 0 && args[0].equalsIgnoreCase("info")) {
                    sender.sendMessage(Utils.color("&a操作系统: " + ServerInfo.getOsName()));
                    sender.sendMessage(Utils.color("&aJVM 版本:" + ServerInfo.getJVMVersion()));
                    sender.sendMessage(Utils.color("&e内存使用情况: " + ServerInfo.getUsedMemory() + "MB/" + ServerInfo.getMaxMemory() + "MB"));
                } else if (args.length == 0) {
                    long usedMemoryNow = ServerInfo.getUsedMemory();
                    System.runFinalization();
                    System.gc();
                    if (ServerInfo.getUsedMemory() < usedMemoryNow){
                        //TODO: return some thing
                    }
                }
            } else sender.sendMessage(Utils.color(Utils.getCfg().getString("messages.no-permission")));
        } return true;
    }
}
