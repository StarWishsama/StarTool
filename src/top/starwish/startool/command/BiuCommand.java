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
        	
        	Player player = (Player) sender;

            if (!(sender instanceof Player))
            {
                    sender.sendMessage("§bStarTool > §e你必须在游戏内使用该命令!");
                    return true;
            }
           
            if (!sender.hasPermission("startool.biu"))
            {            	

                player.setHealth(0);
                sender.sendMessage("§bStarTool > §e天降正义!");
                return true;
            }            
            
            if (args.length == 2)
            {
            	if (!sender.hasPermission("startool.biu"))
            	{

            	player.sendMessage("§bStarTool > §c参数错误! 正确用法: /biu 或者 /biu [玩家]");
            	return true;
                }
            	
                Player otherp = Bukkit.getPlayer(args[1]);
                
                otherp.setHealth(0);
                otherp.sendMessage("§bStarTool > §e你被苟管理 §c" + sender.getName() + " §eBiu了一下!惊喜吧!");
                sender.sendMessage("§bStarTool > §e成功击杀玩家" + otherp.getName() + "§e!");              
            }
        }
        return false;
    }
}
