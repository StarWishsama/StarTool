package top.starwish.startool.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;

/**
 * 
 * 小喇叭功能
 * V0.1.3 新增
 * 
 * TODO List:
 * 与 Vault 对接, 游戏币发送
 * 
 * @author StarWish_
 *
 */

public class LabaCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("laba"))
		{
			if (sender.hasPermission("startool.laba"))
			{
				if (args.length == 0)
				{
					sender.sendMessage("§bStarTool > §e使用方法: /laba <内容>");
					return true;
				}
				
				if (args.length == 1)
				{ 
						Bukkit.broadcastMessage("§b玩家 §e" + sender.getName() +" §b发送了一条小喇叭信息!");
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
