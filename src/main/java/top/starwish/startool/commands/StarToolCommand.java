package top.starwish.startool.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import top.starwish.startool.config.configsetup;
import top.starwish.startool.startup.StarToolStartUp;

public class StarToolCommand {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (cmd.getName().equalsIgnoreCase("startool")) {
                if (args.length == 0) {
                    sender.sendMessage("§bStarTool " + configsetup.Version + ", By StarWishsama");
                    sender.sendMessage("§f");
                    sender.sendMessage("/laba(/lb) [内容] 发送全服公告");
                    sender.sendMessage("/biu <玩家> 让一个玩家立即去世");
                    sender.sendMessage("/stool uuid 获取你的UUID");
                    sender.sendMessage("/stool clear <玩家> 清屏全服/玩家的聊天栏");
                    sender.sendMessage("/stool version 显示插件目前版本");
                    sender.sendMessage("/stool reload 重载插件配置");
                    sender.sendMessage("§f");
                    return true;
                }

                if (args[0].equalsIgnoreCase("uuid")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        if (!(sender.hasPermission("startool.uuid"))) {
                            sender.sendMessage("§bStarTool > §c正在获取您的UUID...");
                            sender.sendMessage("§bStarTool > §e你的UUID为:" + p.getUniqueId());
                            return true;
                        } else {
                            sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
                            return true;
                        }
                    } else {
                        sender.sendMessage("§bStarTool > §c你必须是一个玩家!");
                    }
                }

                if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage("§bStarTool > §e目前版本为: §a§n" + configsetup.Version);
                    return true;
                }

                if (args[0].equalsIgnoreCase("clear")) {
                    if ((sender instanceof Player)) {
                        if (sender.hasPermission("startool.clearscreen")) {
                            if (args.length == 1) {
                                for (int i = 0; i <= 60; i++)
                                    Bukkit.broadcastMessage("       ");
                                sender.sendMessage("§bStarTool > §e全服清屏成功~");
                                return true;
                            }
                            if (sender.hasPermission("startool.clearscreen.other")) {
                                Player otherp = Bukkit.getPlayer(args[1]);
                                if (args.length == 2) {
                                    if (otherp == null) {
                                        sender.sendMessage("§bStarTool > §c玩家名字错误或不存在!");
                                        return true;
                                    }
                                    for (int i = 0; i <= 60; i++);
                                    otherp.sendMessage("        ");
                                    otherp.sendMessage("§bStarTool > §e您已被管理员清屏!");
                                    sender.sendMessage("§bStarTool > §e成功为 §c" + otherp.getName() + " §e清屏!");
                                    return true;
                                }
                            } else {
                                sender.sendMessage("§bStarTool > §c玩家名字错误或不存在!");
                                return true;
                            }
                        }
                    } else {
                        sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
                        return true;
                    }
                } else {
                    sender.sendMessage("§bStarTool > §e你必须在游戏内使用该命令!");
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("startool.reload")) {
                        configsetup.reloadConfig();
                        sender.sendMessage("§bStarTool > §e重载完成.");
                        return true;
                    } else {
                        sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
                        return true;
                    }
            }
        return false;
        }
    }
