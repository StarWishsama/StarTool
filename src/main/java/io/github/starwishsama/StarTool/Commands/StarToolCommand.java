package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Files.Config;
import io.github.starwishsama.StarTool.Files.Lang;
import io.github.starwishsama.StarTool.StarToolMain;
import io.github.starwishsama.StarTool.UpdateChecker;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StarToolCommand implements CommandExecutor, TabCompleter {
    private String[] subCommands = {"reload", "clear", "version"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        switch (args[0].toLowerCase()){
            case "reload":
                if (sender.hasPermission("startool.reload")) {
                    Config.loadCfg();
                    sender.sendMessage(Utils.color(Lang.pluginPrefix + "&a配置重载成功!"));
                }
                else
                    sender.sendMessage(Utils.color("&bStarTool &7> &b你没有权限!"));
            break;
            case "clear":
                if (sender.hasPermission("startool.clearscreen") || sender instanceof ConsoleCommandSender) {
                    if (args.length == 1) {
                        IntStream.rangeClosed(0, 60).mapToObj(i -> "       ").forEach(Bukkit::broadcastMessage);
                        sender.sendMessage(Lang.pluginPrefix + "§e全服清屏成功~");
                    } else {
                        if (sender.hasPermission("startool.clearscreen.other") || sender instanceof ConsoleCommandSender) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (args.length == 2) {
                                if (Utils.isExist(args[1])) {
                                    Objects.requireNonNull(target);
                                    IntStream.rangeClosed(0, 60).forEach(i -> target.sendMessage("        "));
                                    target.sendMessage(Lang.pluginPrefix + "§e您已被管理员清屏!");
                                    sender.sendMessage(Lang.pluginPrefix + "§e成功为 §c" + target.getName() + " §e清屏!");
                                } else
                                    sender.sendMessage(Utils.color(Lang.pluginPrefix + "&c玩家不在线!"));
                            } else
                                sender.sendMessage(Lang.pluginPrefix + "§c执行命令时发生了错误");
                        } else
                            sender.sendMessage(Utils.color(Lang.noPermission));
                    }
                } else
                    sender.sendMessage(Utils.color(Lang.noPermission));
                break;
            case "version":
                if (sender instanceof ConsoleCommandSender || sender.hasPermission("startool.commands.version")) {
                    sender.sendMessage(Utils.color(Lang.pluginPrefix + "&e目前版本: " + StarToolMain.getInstance().getDescription().getVersion()));
                    sender.sendMessage(Utils.color(Lang.pluginPrefix + "&e服务端版本: " + Bukkit.getBukkitVersion()));
                    sender.sendMessage(Utils.color(Lang.pluginPrefix + "&a部分代码来自于 https://github.com/MlgmXyysd/Teleport/\nhttps://github.com/BryanSer/At-Bukkit/"));
                    if (UpdateChecker.isLatest()) {
                        sender.sendMessage(Utils.color(Lang.pluginPrefix + "&r你当前正在使用最新版本 " + StarToolMain.getInstance().getDescription().getVersion()));
                    } else {
                        sender.sendMessage(Utils.color(Lang.pluginPrefix + "&r你目前使用的不是最新版本! 最新版本为 " + UpdateChecker.getLatestVer()));
                        sender.sendMessage(Utils.color(Lang.pluginPrefix + "&r请至 https://github.com/StarWishsama/StarTool/releases 下载最新版!"));
                    }
                } else sender.sendMessage(Utils.color(Lang.noPermission));
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0)
            return Arrays.asList(subCommands);
        if (args.length > 1)
            return new ArrayList<>();
        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
