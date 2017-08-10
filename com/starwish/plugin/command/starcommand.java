package com.starwish.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class starcommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("starplugin"))
		{	
		    if (args.length == 0 || sender instanceof ConsoleCommandSender)
		    {
			sender.sendMessage("§bStarPlugin V0.0.8 Dev, By StarWish_");
			sender.sendMessage("§f");
			sender.sendMessage("/biu [玩家] 让一个玩家立即去世");
			sender.sendMessage("/starp uuid 获取某位玩家的UUID");
			sender.sendMessage("/starp clear 清屏聊天栏");
			sender.sendMessage("/starp version 显示插件目前版本号");
			sender.sendMessage("/sptest Debug 命令");
			sender.sendMessage("§f");
			sender.sendMessage("§c§l请注意。本插件不支持/reload命令!");
			return true;
		    }
		    
		    if (args[0].equalsIgnoreCase("uuid")){
		    	
		    	Player p = (Player) sender;
		    	
		    	if (sender.hasPermission("starplugin.uuid"))
		    	{
			    	
			    		sender.sendMessage("§bStarPlugin > §c正在获取您的UUID...");
				    	sender.sendMessage("§bStarPlugin > §e你的UUID为:" + p.getUniqueId());	
				    	return true;
			    }
		    	else 
		    	{
		    		sender.sendMessage("§bStarPlugin > §c你没有权限来执行这条命令!");
		    		return true;
		    	}	    	
		    }
		    
		    if (args[0].equalsIgnoreCase("version"))
		    {
		    	if (sender.hasPermission("starplugin.version") || sender instanceof ConsoleCommandSender)
		    	{
					sender.sendMessage("§bStarPlugin > §e目前版本为: §aV0.0.8 DEV");					
		    	}
		    	else 
		    	{
		    		sender.sendMessage("§bStarPlugin > §c你没有权限来执行这条命令!");
		    	}
		      return true;	
		   }
		    
		   if (args[0].equalsIgnoreCase("updatelog") || sender instanceof ConsoleCommandSender)
		   {
			   sender.sendMessage("§bV0.0.8 DEV 更新日志:");
			   sender.sendMessage("修复 Bug");
			   sender.sendMessage("将/biu命令单独归类方便未来 Coding.");
			   sender.sendMessage("修复参数Bug.");
		   }
		    
		   if (args[0].equalsIgnoreCase("clear"))
		   {
			    if (!(sender instanceof Player)) 
			    {
			    sender.sendMessage("§bStarPlugin > §e你必须在游戏内使用该命令!");
			    return true;
			    }
			    
			    if (sender.hasPermission("starplugin.clearscreen"))
					   {
				         sender.sendMessage("§bStarPlugin > §c你没有权限来执行这条命令!");
					   } 
				         for(int i = 0; i <= 50; i++)
				         sender.sendMessage(" ");
				         sender.sendMessage("§bStarPlugin > §e清屏成功~"); 
				         return true;
		   }
	   }
        return false;
    }
}	