package top.starwish.startool;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import top.starwish.startool.command.BiuCommand;
import top.starwish.startool.listener.*;

public class StarToolMain extends JavaPlugin implements Listener
{

    String prefix = getConfig().getString("PluginPrefix");
    String version = getConfig().getString("Version");
    
    @Override
    public void onEnable()
    {
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > 正在载入监听器...");
        Bukkit.getPluginManager().registerEvents(new LevelChat(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTip(), this);
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > 正在注册命令...");
        Bukkit.getPluginCommand("biu").setExecutor(new BiuCommand());
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > 载入成功.");
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > §f欢迎使用 StarTool, 版本 " + version +", 作者 StarWish");
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > §f感谢您的使用!");
        saveDefaultConfig();
    }

    @Override
    public void onDisable()
    {
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > §7正在关闭插件...");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("startool"))
        {
            if (args.length == 0)
            {
                sender.sendMessage("§bStarTool " + version +", By StarWish_");
                sender.sendMessage("§f");
                sender.sendMessage("/biu <玩家> 让一个玩家立即去世");
                sender.sendMessage("/stool uuid 获取你的UUID");
                sender.sendMessage("/stool clear 清屏聊天栏");
                sender.sendMessage("/stool version 显示插件目前版本号");
                sender.sendMessage("/stool reload 重载插件配置");
                sender.sendMessage("/stool info 查看部分信息");
                sender.sendMessage("§f");
                return true;
            }           

            if (args[0].equalsIgnoreCase("uuid")) 
            {
              if (sender instanceof Player)
              {	
                 Player p = (Player) sender;
            	  
                if (!(sender.hasPermission("startool.uuid")))
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
              else 
              {
            	  sender.sendMessage("§b" + prefix + " §b> §c你必须是一个玩家!");
              }
            }

            if (args[0].equalsIgnoreCase("version"))
            {
                sender.sendMessage("§b" + prefix + " §b> §e目前版本为: §a§n" + version);
                return true;
            }

           if (args[0].equalsIgnoreCase("clear"))
            {
                if ((sender instanceof Player)) 
                {
                    if (sender.hasPermission("startool.clearscreen"))
                    {
                        for (int i = 0; i <= 50; i++)
                        	Bukkit.broadcastMessage("       ");
                        sender.sendMessage("§b" + prefix + " §b> §e全服清屏成功~");
                        return true;
                    }

                    else
                    {
                        sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
                        return true;
                    }
                }                
                else {
                    sender.sendMessage("§b" + prefix + " §b> §e你必须在游戏内使用该命令!");
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("reload"))
            {
            	if ((sender instanceof ConsoleCommandSender)) 
            	{
                    if (sender.hasPermission("startool.reload")) 
                        {
                        reloadConfig();
                        saveConfig();
                        sender.sendMessage("§b" + prefix + " §b> §e重载完成.");
                        return true;
                        }

                    else 
                    {
                    sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
                    return true;
                    }
            	}
            }    
            	
            if (args[0].equalsIgnoreCase("info"))
            {
            	    if ((sender instanceof Player))
            	    {         	
            		sender.sendMessage("§a服务端版本: §f" + sender.getServer().getBukkitVersion());
            		sender.sendMessage("§a你的IP地址: §f" + sender.getServer().getIp());
            		return true;
            	    }
               }	                 
          }
		return false;
    }
}
