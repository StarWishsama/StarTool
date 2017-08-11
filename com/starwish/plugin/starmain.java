package com.starwish.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.starwish.plugin.command.biucmd;

/**
* 本插件基于 GPL-3.0 开源.
* 作者 StarWish
**/


public class starmain extends JavaPlugin
{
	
    @Override
	public void onEnable() 
    {
		getServer().getConsoleSender().sendMessage("§f[§bStarPlugin§f]欢迎使用 StarPlugin, 作者:StarWish");
		Bukkit.getPluginCommand("biu").setExecutor(new biucmd());	
		saveDefaultConfig();
    }
    
    @Override
    public void onDisable()
    {    	
    	getServer().getConsoleSender().sendMessage("§f[§bStarPlugin§f]§7正在关闭插件...");
    	saveConfig();    	
    }
    
	String prefix = getConfig().getString("config.PluginPrefix");
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("starplugin"))
		{			
		    if (args.length == 0 || sender instanceof ConsoleCommandSender)
		    {
			sender.sendMessage("§bStarPlugin V0.0.9 Dev, By StarWish_");
			sender.sendMessage("§f");
			sender.sendMessage("/biu [玩家] 让一个玩家立即去世");
			sender.sendMessage("/starp uuid 获取你的UUID");
			sender.sendMessage("/starp clear 清屏聊天栏");
			sender.sendMessage("/starp version 显示插件目前版本号");
			sender.sendMessage("/sptest Debug 命令");
			sender.sendMessage("/starp reload 重载插件配置");
			sender.sendMessage("/starp updatelog 查看更新日志");
			sender.sendMessage("§f");
			sender.sendMessage("§c§l请注意。本插件不支持/reload命令!");
			return true;
		    }
		    
		    if (args[0].equalsIgnoreCase("uuid")){
		    	
		    	Player p = (Player) sender;
		    	
		    	if (sender.hasPermission("starplugin.uuid"))
		    	{
			    	
			    		sender.sendMessage("§b" + prefix + "§b> §c正在获取您的UUID...");
				    	sender.sendMessage("§b" + prefix + "§b> §e你的UUID为:" + p.getUniqueId());	
				    	return true;
			    }
		    	else 
		    	{
		    		sender.sendMessage("§b" + prefix + "§b> §c你没有权限来执行这条命令!");
		    		return true;
		    	}	    	
		    }
		    
		    if (args[0].equalsIgnoreCase("version"))
		    {
		    	if (sender.hasPermission("starplugin.version") || sender instanceof ConsoleCommandSender)
		    	{
					sender.sendMessage("§b" + prefix + "§b> §e目前版本为: §aV0.0.8 DEV");					
		    	}
		    	else 
		    	{
		    		sender.sendMessage("§b" + prefix + "§b> §c你没有权限来执行这条命令!");
		    	}
		      return true;	
		   }
		    
		   if (args[0].equalsIgnoreCase("updatelog") || sender instanceof ConsoleCommandSender)
		   {
			   sender.sendMessage("§bV0.0.9 DEV 更新日志:");
			   sender.sendMessage("将主命令重新返回了 Main.");
			   sender.sendMessage("新增配置文件, 可以自由配置插件前缀");
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
				         sender.sendMessage("§b" + prefix + "§b> §c你没有权限来执行这条命令!");
					   } 
				         for(int i = 0; i <= 50; i++)
				         sender.sendMessage(" ");
				         sender.sendMessage("§b" + prefix + "§b> §e清屏成功~"); 
				         return true;
		   }
		   
		   if (args[0].equalsIgnoreCase("reload"))
		   {
			   reloadConfig();
			   sender.sendMessage("§b" + prefix + "§b> §e重载完成.");
		   }
	   }
        return false;
    }
   
}
