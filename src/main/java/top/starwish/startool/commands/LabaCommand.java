package top.starwish.startool.commands;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.starwish.startool.econemy.VaultLib;
import top.starwish.startool.config.configsetup;

public class LabaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("laba")) {
            if (sender instanceof Player) {
                if (!Bukkit.getServer().getPluginManager().getPlugin("Vault").isEnabled()) {
                    sender.sendMessage("§bStarTool > §c服务器未安装经济插件, 小喇叭已被禁用!");
                    if (sender.hasPermission("startool.laba")) {
                        if (args.length == 0) {
                            sender.sendMessage("§bStarTool > §e使用方法: /laba <内容>");
                            return true;
                        }
                        if (args.length == 1) {
                            EconomyResponse s = VaultLib.getEconomy().bankWithdraw(sender.getName(), configsetup.LabaPrice);
                            if (s.transactionSuccess()) {
                                Bukkit.broadcastMessage("§b玩家 §e" + sender.getName() + " §b发送了一条小喇叭信息!");
                                Bukkit.broadcastMessage("§d§l" + sender.getName() + " §e§l说: " + args[0]);
                                return true;
                            } else {
                                sender.sendMessage("§b StarTool §c> 你没有足够的金钱!");
                            }
                        }
                    } else {
                        sender.sendMessage("§bStarTool §c> 你没有权限!");
                    }
                }
            }
        }
        return false;
    }
}
