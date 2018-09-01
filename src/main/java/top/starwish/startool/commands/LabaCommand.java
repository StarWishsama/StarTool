package top.starwish.startool.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LabaCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("laba"))
        {
            if (sender.hasPermission("startool.laba"))
            {
                //Player player = (Player) sender;
                if (args.length == 0)
                {
                        sender.sendMessage("§bStarTool > §e使用方法: /laba <内容>");
                        return true;
                }

                if (args.length == 1)
                {
                    //EconomyResponse s = econ.bankWithdraw(sender.getName(), 50);
                    //if (s.transactionSuccess()) {
                        Bukkit.broadcastMessage("§b玩家 §e" + sender.getName() + " §b发送了一条小喇叭信息!");
                        Bukkit.broadcastMessage("§d§l" + sender.getName() + " §e§l说: " + args[0]);
                        return true;
                }
            }

            else
            {
                sender.sendMessage("§bStarTool > §c你没有权限!");
            }
        }
        return false;
    }
}
