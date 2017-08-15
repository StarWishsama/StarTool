package com.starwish.plugin;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
* 本插件基于 GPL-3.0 开源.
* 作者 StarWish
**/

public class starmain extends JavaPlugin
{
	
    @Override
	public void onEnable() 
    {
		getServer().getConsoleSender().sendMessage("§bStarPlugin > §f欢迎使用 StarPlugin, 版本" + version +", 作者StarWish");
		getServer().getConsoleSender().sendMessage("§bStarPlugin > §f感谢您的使用!");
		saveDefaultConfig();
    }
    
    @Override
    public void onDisable()
    {    	
    	getServer().getConsoleSender().sendMessage("§bStarPlugin > §7正在关闭插件...");  	
    }

	String prefix = getConfig().getString("PluginPrefix");
	String version = getConfig().getString("Version");
	
	public boolean onCommand1(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("biu"))
	    {
			if (sender.hasPermission("starplugin.biu"))
			{	
		        if (!(sender instanceof Player)) 
		        {
		        sender.sendMessage("§bStarPlugin > §e你必须在游戏内使用该命令!");
		        return true;
		        }
		    
		        Player player = (Player) sender;
	    	  		
	    	    player.setHealth(0);
	    	    sender.sendMessage("§bStarPlugin > §e你被苟管理biu了一下!惊喜吧!");
	    	    return true;
			}
			else {
				sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
			}
	    }
		return false;		
	}
	
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("starplugin"))
		{			
		    if (args.length == 0 || sender instanceof ConsoleCommandSender)
		    {
			sender.sendMessage("§bStarPlugin " + version +", By StarWish_");
			sender.sendMessage("§f");
			sender.sendMessage("/biu [玩家] 让一个玩家立即去世");
			sender.sendMessage("/starp uuid 获取你的UUID");
			sender.sendMessage("/starp clear 清屏聊天栏");
			sender.sendMessage("/starp version 显示插件目前版本号");
			sender.sendMessage("/starp reload 重载插件配置");
			sender.sendMessage("/starp updatelog 查看更新日志");
			sender.sendMessage("§f");
			return true;
		    }
		    
		    if (args[0].equalsIgnoreCase("uuid"))
		    {
		    	
		    	Player p = (Player) sender;
		    	
		    	if (sender.hasPermission("starplugin.uuid"))
		    	{
			    	
			    		sender.sendMessage("§b" + prefix + " §b> §c正在获取您的UUID...");
				    	sender.sendMessage("§b" + prefix + " §b> §e你的UUID为:" + p.getUniqueId());	
				    	return true;
			    }
		    	else 
		    	{
		    		sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
		    		return true;
		    	}	    	
		    }
		    
		    if (args[0].equalsIgnoreCase("version"))
		    {
		    	if (sender.hasPermission("starplugin.version") || sender instanceof ConsoleCommandSender)
		    	{
					sender.sendMessage("§b" + prefix + " §b> §e目前版本为: §a" + version);					
		    	}
		    	else 
		    	{
		    		sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
		    	}
		      return true;	
		   }
		    
		   if (args[0].equalsIgnoreCase("updatelog") || sender instanceof ConsoleCommandSender)
		   {
			   sender.sendMessage("§b " + version +"更新日志:");
			   sender.sendMessage("修复配置.");
		   }
		    
		   if (args[0].equalsIgnoreCase("clear"))
		   {
			    if (!(sender instanceof Player)) 
			    {
			    sender.sendMessage("§b" + prefix + "§b> §e你必须在游戏内使用该命令!");
			    return true;
			    }
			    
			    if (sender.hasPermission("starplugin.clearscreen"))
					   {
				         sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
					   } 
				         for(int i = 0; i <= 50; i++)
				         sender.sendMessage(" ");
				         sender.sendMessage("§b" + prefix + " §b> §e清屏成功~"); 
				         return true;
		   }
		   
		   if (args[0].equalsIgnoreCase("reload"))
		   {
			   if (!(sender instanceof ConsoleCommandSender))
			   {
			   reloadConfig();
			   saveConfig();  
			   sender.sendMessage("§b" + prefix + " §b> §e重载完成.");
			   }
			   else
			   {
				   sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
			   }
		   }
	   }
        return false;
    }   
}
