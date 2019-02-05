package top.starwish.StarTool.Commands;

import org.apache.commons.lang.StringUtils;
import top.starwish.StarTool.PluginHook.Vault;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.starwish.StarTool.StarToolStartup;
import top.starwish.StarTool.Utils.Utils;

public class LabaCommand implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (StarToolStartup.getInstance().getConfig().getBoolean("EnableLaba")){
            if (sender.hasPermission("startool.laba.use")|| sender.isOp()) {
                if (args.length == 0){
                    sender.sendMessage(Utils.color("&bStarTool > &e/laba <内容>"));
                    sender.sendMessage(Utils.color("&bStarTool > &e或者 /laba title <主标题> [副标题]"));
                    sender.sendMessage(Utils.color("&bStarTool > &e你可以用下划线 _ 来代替空格"));
                }
                else if (args.length >= 1){
                    if (!args[0].equalsIgnoreCase("title")){
                        if (!sender.hasPermission("startool.laba.bypass")) {
                            EconomyResponse s = Vault.getEconomy().bankWithdraw(sender.getName(), StarToolStartup.getInstance().getConfig().getInt("LabaPrice"));
                            if (s.transactionSuccess()) {
                                sender.sendMessage("§b你已成功发送了一条小喇叭!");
                                Bukkit.broadcastMessage(Utils.color("&b" + sender.getName() + " &e说: " + args[0]));
                            } else sender.sendMessage(Utils.color("§b StarTool §c> 你没有足够的金钱!"));
                        } else {
                            sender.sendMessage("§b由于你与服主完成了某种交易, 本次发送无需费用!");
                            Bukkit.broadcastMessage(Utils.color("&b" + sender.getName() + " &e说: " + args[0]));
                        }
                    }
                    else if (args[0].equalsIgnoreCase("title")){
                        if (args.length > 1) {
                            /**
                             * title - 标题文本
                             * subtitle - 副标题文本
                             * fadeIn - 标题淡入时间,以tick为单位.默认值取10.
                             * stay - 标题停留/展示时长,以tick为单位.默认值取70.
                             * fadeOut - 标题淡出时间,以tick为单位.默认值取20.
                             */

                            String title = args[1].replaceAll("\\_", " ");
                            String subtitle = args[2].replaceAll("\\_", " ");


                            if (args.length == 2) {
                                StarToolStartup.getInstance().getServer().getOnlinePlayers().forEach((Player a) -> a.sendTitle(Utils.color(title), ""));
                                sender.sendMessage(Utils.color("&bStarTool > &e发送成功."));
                            }
                            else if (args.length == 3) {
                                    StarToolStartup.getInstance().getServer().getOnlinePlayers().forEach((Player a) -> a.sendTitle(Utils.color(title), Utils.color(subtitle)));
                                    sender.sendMessage(Utils.color("&bStarTool > &e发送成功."));
                            }
                            else if (args.length >= 4) {
                                if (StringUtils.isNumeric(args[3]) && StringUtils.isNumeric(args[4]) && StringUtils.isNumeric(args[5])){
                                    Integer fadeIn = Integer.parseInt(args[3]);
                                    Integer stay = Integer.parseInt(args[4]);
                                    Integer fadeOut = Integer.parseInt(args[5]);
                                    StarToolStartup.getInstance().getServer().getOnlinePlayers().forEach((Player a) -> a.sendTitle(Utils.color(title), Utils.color(subtitle), fadeIn, stay, fadeOut));
                                    sender.sendMessage(Utils.color("&bStarTool > &e发送成功."));
                                } else {
                                    sender.sendMessage(Utils.color("&bStarTool > &c输入了错误的参数!"));
                                }
                            }
                            else sender.sendMessage(Utils.color("&bStarTool > &c输入了错误的参数!"));
                        } else {
                            sender.sendMessage(Utils.color("&bStarTool > &e/laba title <主标题> [副标题]"));
                            sender.sendMessage(Utils.color("&bStarTool > &e你可以用下划线 _ 来代替空格"));
                        }
                    }
                    else {
                        sender.sendMessage(Utils.color("&bStarTool > &e/laba <内容>"));
                        sender.sendMessage(Utils.color("&bStarTool > &e或者 /laba title <主标题> [副标题]"));
                        sender.sendMessage(Utils.color("&bStarTool > &e你可以用下划线 _ 来代替空格"));
                    }
                }
            } else sender.sendMessage("§bStarTool §c> 你没有权限!");
        } else sender.sendMessage("§bStarTool > §c服务器未启用小喇叭!");
        return true;
    }
}