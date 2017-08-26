package top.starwish.startool.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * 未使用
 */

public class BiuCommand
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("biu"))
        {

            if (!(sender instanceof Player))
            {
                    sender.sendMessage("§bStarTool > §e你必须在游戏内使用该命令!");
            }

            else if  (!sender.hasPermission("startool.biu")) sender.sendMessage("§bStarTool > §c你没有权限来执行这条命令!");
            
            else {
                Player player = (Player) sender;

                player.setHealth(0);
                sender.sendMessage("§bStarTool > §e你被苟管理Biu了一下!惊喜吧!");
                return true;

            }
        }
        return false;
    }
}
