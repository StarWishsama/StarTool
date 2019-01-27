package top.starwish.StarTool.Commands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import top.starwish.StarTool.StarToolStartup;
import top.starwish.StarTool.Utils.SPlayer;
import top.starwish.StarTool.Utils.TranlateColorCode;

public class StarToolCommand implements CommandExecutor, Listener {

    String Version = StarToolStartup.getInstance().getDescription().getVersion();

    @EventHandler
    public void CommandChecker(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().equalsIgnoreCase("/help startool")) {
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &r请使用 /startool 获取帮助."));
            e.setCancelled(true);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("startool")){
            if (args.length == 0){
                sender.sendMessage("§bStarTool " + Version +", By StarWishsama");
                sender.sendMessage("§f");
                sender.sendMessage("/laba [内容] 发送全服公告");
                sender.sendMessage("/curse [玩家] 诅咒一名玩家(不能诅咒你自己)");
                sender.sendMessage(TranlateColorCode.tcc("/gc 获取服务器信息"));
                sender.sendMessage("/stool uuid 获取你的UUID");
                sender.sendMessage("/stool clear <玩家> 清屏全服/玩家的聊天栏");
                sender.sendMessage("/stool version 显示插件目前版本");
                sender.sendMessage("/stool reload 重载插件配置");
                sender.sendMessage("§f");
                return true;
            }
            else if (args.length > 0) {
                if (args[0].equalsIgnoreCase("uuid")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        if (sender.hasPermission("startool.uuid")) {
                            sender.sendMessage("§bStarTool > §c正在获取您的UUID...");
                            sender.sendMessage("§bStarTool > §e你的UUID为:" + p.getUniqueId());
                        } else sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
                    } else sender.sendMessage("§bStarTool > §c你必须是一个玩家!");
                }

                else if (args[0].equalsIgnoreCase("version")) {
                    if (sender instanceof ConsoleCommandSender || sender.hasPermission("startool.commands.version")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e目前版本: " + Version));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e服务端版本: " + Bukkit.getBukkitVersion()));
                    } else sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
                }

                else if (args[0].equalsIgnoreCase("clear")) {
                        if (sender.hasPermission("startool.clearscreen") || sender instanceof ConsoleCommandSender) {
                            if (args.length == 1) {
                                for (int i = 0; i <= 60; i++)
                                    Bukkit.broadcastMessage("       ");
                                sender.sendMessage("§bStarTool > §e全服清屏成功~");
                                return true;
                            }
                            else if (args.length >= 2) {
                                if (sender.hasPermission("startool.clearscreen.other") || sender instanceof ConsoleCommandSender) {
                                        Player otherp = Bukkit.getPlayer(args[1]);
                                        if (args.length == 2) {
                                            if (SPlayer.isExist(args[1])) {
                                                for (int i = 0; i <= 60; i++) ;
                                                otherp.sendMessage("        ");
                                                otherp.sendMessage("§bStarTool > §e您已被管理员清屏!");
                                                sender.sendMessage("§bStarTool > §e成功为 §c" + otherp.getName() + " §e清屏!");
                                                return true;
                                        } else sender.sendMessage(TranlateColorCode.tcc("&bStarTool > &c玩家不在线!"));
                                    } else sender.sendMessage("§bStarTool > §e你必须在游戏内使用该命令!");
                                } else sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
                            } else sender.sendMessage("§bStarTool > §c执行命令时发生了错误"); return true;
                        } else sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
                }
                else if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("startool.commands.reload") || sender instanceof ConsoleCommandSender){
                        StarToolStartup.getInstance().reloadConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e重载完成."));
                    } else sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
                }
                else return true;
            }
        } return true;
    }
}