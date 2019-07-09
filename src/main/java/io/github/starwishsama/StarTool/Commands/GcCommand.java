package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Files.Lang;
import io.github.starwishsama.StarTool.Utils.ServerInfo;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.command.*;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GcCommand implements TabCompleter, CommandExecutor {
    private String[] subCommand = {"info"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("startool.commands.gc") || sender instanceof ConsoleCommandSender){
            if (args.length > 0 && args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(Utils.color("&b操作系统: " + ServerInfo.getOsName()));
                sender.sendMessage(Utils.color("&bJVM 版本:" + ServerInfo.getJVMVersion()));
                sender.sendMessage(Utils.color("&b内存使用情况: " + ServerInfo.getUsedMemory() + "MB/" + ServerInfo.getMaxMemory() + "MB"));
                sender.sendMessage(Utils.color("&b已运行时间: " + ((System.currentTimeMillis() - ManagementFactory.getRuntimeMXBean().getStartTime()) / 100 / 60) + "分钟"));
            } else if (args.length == 0) {
                long usedMemoryBefore = ServerInfo.getUsedMemory();
                System.runFinalization();
                System.gc();
                if (ServerInfo.getUsedMemory() < usedMemoryBefore){
                    long cleanedMemory = usedMemoryBefore - ServerInfo.getUsedMemory();
                    sender.sendMessage(Utils.color(Lang.pluginPrefix + "&a成功清理内存 " + cleanedMemory + " MB!"));
                } else
                    sender.sendMessage(Utils.color(Lang.pluginPrefix + "&cGC 清理没有效果, 不建议再次使用"));
            }
        } else sender.sendMessage(Utils.color(Lang.noPermission));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0)
            return Arrays.asList(subCommand);
        if (args.length > 1)
            return new ArrayList<>();
        return Arrays.stream(subCommand).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
