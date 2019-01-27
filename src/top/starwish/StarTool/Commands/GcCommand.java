package top.starwish.StarTool.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import top.starwish.StarTool.Utils.ServerInfo;

public class GcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gc")){
            if (sender.hasPermission("startool.commands.gc") || sender instanceof ConsoleCommandSender){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a操作系统: " + ServerInfo.getOsName()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aJVM 版本:" + ServerInfo.getJVMVersion()));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e内存使用情况: " + ServerInfo.getUsedMemory() + "MB/" + ServerInfo.getTotalMemorySize() + "MB"));
            } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &c你没有权限来执行这条命令!"));
        } return true;
    }
}
