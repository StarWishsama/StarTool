package com.starwish.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.starwish.plugin.command.starcommand;
import com.starwish.plugin.command.starcommandbiu;


public class starmain extends JavaPlugin
{
	
    @Override
	public void onEnable() 
    {
		getServer().getConsoleSender().sendMessage("§f[§bStarPlugin§f]欢迎使用 StarPlugin, 作者:StarWish");
		getServer().getConsoleSender().sendMessage("§f[§bStarPlugin§f]请注意，本插件不支持/reload命令!");
		Bukkit.getPluginCommand("starplugin").setExecutor(new starcommand());
		Bukkit.getPluginCommand("biu").setExecutor(new starcommandbiu());
    }
    
    public void onDisable()
    {
    	getServer().getConsoleSender().sendMessage("§f[§bStarPlugin§f]§7正在关闭插件...");
    	
    }
   
}
