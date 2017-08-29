package top.starwish.startool.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BiuCommand implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("biu"))
        {
        	
            if (sender instanceof Player)
            {
            
              if (args.length == 0)
              {        
            	 Player player = (Player) sender;
                 player.setHealth(0);
                 sender.sendMessage("§bStarTool > §e你被苟管理Biu了一下!惊喜吧!");
                 return true;
             }            
            
             if (args.length == 1)
             {                      
                Player otherp = Bukkit.getPlayer(args[0]);
                
                 otherp.setHealth(0);
                 otherp.sendMessage("§bStarTool > §e你被苟管理 §c" + sender.getName() + " §eBiu了一下!惊喜吧!");
                 sender.sendMessage("§bStarTool > §e成功击杀玩家" + otherp.getName() + "§e!"); 
                 return true;
             }
	     
	     if (args[0] == null)
	     {
	       sender.sendMessage("§bStarTool > §c无效参数! 请检查此玩家是否在线/是否存在!")
	     }
           } 
            }
            else {
            sender.sendMessage("§bStarTool > §c你必须在游戏内使用该命令!");
            return true;
            }
            
         }
          else 
          {
        	sender.sendMessage("§bStarTool > §c你没有权限!");
        	return true;
          }
	   return false;
    }
}
