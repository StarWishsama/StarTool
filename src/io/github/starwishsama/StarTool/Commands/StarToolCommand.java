package io.github.starwishsama.StarTool.Commands;

import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.StarToolStartup;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.starwishsama.StarTool.CheckUpdate.UpdateChecker;

import java.util.stream.IntStream;

public class StarToolCommand implements CommandExecutor {

    private String Version = StarToolStartup.getInstance().getDescription().getVersion();

    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("startool")){
            if (args.length == 0) {
                sender.sendMessage("§bStarTool " + Version +", By StarWishsama");
                sender.sendMessage("");
                sender.sendMessage("/laba [内容] 发送全服公告");
                sender.sendMessage("/curse [玩家] 诅咒一名玩家(不能诅咒你自己)");
                sender.sendMessage("/gc 获取服务器信息");
                sender.sendMessage("/stool uuid 获取你的UUID");
                sender.sendMessage("/stool clear <玩家> 清屏全服/玩家的聊天栏");
                sender.sendMessage("/stool version 显示插件目前版本");
                sender.sendMessage("/stool reload 重载插件配置");
                sender.sendMessage("");
            }
            else {
                switch (args[0]) {
                    case "uuid":
                        if (sender instanceof Player) {
                            Player p = (Player) sender;
                            if (sender.hasPermission("startool.uuid")) {
                                sender.sendMessage(Config.plugin_prefix + "§c正在获取您的UUID...");
                                sender.sendMessage(Config.plugin_prefix + "§e你的UUID为:" + p.getUniqueId());
                            } else sender.sendMessage(Utils.color(Config.nopermission));
                        } else
                            sender.sendMessage(Utils.color(Config.plugin_prefix + Utils.getCfg().getString("messages.not-a-player")));
                        break;
                    case "version":
                        if (sender instanceof ConsoleCommandSender || sender.hasPermission("startool.commands.version")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.plugin_prefix + "&e目前版本: " + Version));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.plugin_prefix + "&e服务端版本: " + Bukkit.getBukkitVersion()));
                            if (UpdateChecker.isLatest()) {
                                sender.sendMessage(Utils.color(Config.plugin_prefix + "&r你当前正在使用最新版本 " + StarToolStartup.getInstance().getDescription().getVersion()));
                            } else {
                                sender.sendMessage(Utils.color(Config.plugin_prefix + "&r你目前使用的不是最新版本! 最新版本为 " + UpdateChecker.getLatestVer()));
                                sender.sendMessage(Utils.color(Config.plugin_prefix + "&r请至 https://github.com/StarWishsama/StarTool/releases 下载最新版!"));
                            }
                        } else sender.sendMessage(Utils.color(Config.nopermission));
                        break;
                    case "clear":
                        if (sender.hasPermission("startool.clearscreen") || sender instanceof ConsoleCommandSender) {
                            if (args.length == 1) {
                                IntStream.rangeClosed(0, 60).mapToObj(i -> "       ").forEach(Bukkit::broadcastMessage);
                                sender.sendMessage(Config.plugin_prefix + "§e全服清屏成功~");
                            } else {
                                if (sender.hasPermission("startool.clearscreen.other") || sender instanceof ConsoleCommandSender) {
                                    Player otherp = Bukkit.getPlayer(args[1]);
                                    if (args.length == 2) {
                                        if (Utils.isExist(args[1])) {
                                            IntStream.rangeClosed(0, 60).forEach(i -> {
                                                otherp.sendMessage("        ");
                                                otherp.sendMessage(Config.plugin_prefix + "§e您已被管理员清屏!");
                                                sender.sendMessage(Config.plugin_prefix + "§e成功为 §c" + otherp.getName() + " §e清屏!");
                                            });
                                        } else
                                            sender.sendMessage(Utils.color(Config.plugin_prefix + "&c玩家不在线!"));
                                    } else
                                        sender.sendMessage(Config.plugin_prefix + "§c执行命令时发生了错误");
                                } else
                                    sender.sendMessage(Utils.color(Config.nopermission));
                            }
                        } else sender.sendMessage(Utils.color(Config.nopermission));
                        break;
                    case "reload":
                        if (sender.hasPermission("startool.commands.reload") || sender instanceof ConsoleCommandSender) {
                            Config.loadConfig();
                            sender.sendMessage(Utils.color(Config.plugin_prefix + "&e重载完成."));
                        } else
                            sender.sendMessage(Utils.color(Config.plugin_prefix + Config.nopermission));
                        break;
                    default:
                        sender.sendMessage(Utils.color(Config.plugin_prefix + "&r请使用 /startool 获取帮助."));
                       break;
                } return true;
            }
        } return true;
    }
}