package com.starwish.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 指令 Biu的命令部分.
 * 太复杂了所以弄出来写 XD
 */

public class starcommandbiu implements CommandExecutor
{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (cmd.getName().equalsIgnoreCase("biu"))
	    {
		    if (!(sender instanceof Player)) 
		    {
		    sender.sendMessage("§bStarPlugin > §e你必须在游戏内使用该命令!");
		    return true;
		    }
		    
		    Player player = (Player) sender;
	    	  		
	    	player.setHealth(0);
	    	sender.sendMessage("§bStarPlugin > §e你被苟管理Biu了一下!惊喜吧!");
	    	return true;
	    }
		return false;		
	}
}