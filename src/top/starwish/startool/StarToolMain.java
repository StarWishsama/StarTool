package top.starwish.startool;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import top.starwish.startool.command.BiuCommand;
import top.starwish.startool.command.LabaCommand;
import top.starwish.startool.listener.*;


public class StarToolMain extends JavaPlugin
{

    String prefix = getConfig().getString("PluginPrefix");
    String version = getConfig().getString("Version");
    
    @Override
    public void onEnable()
    {
    	//加载文件
    	getLogger().info("正在载入文件...");
    	File config = new File(getDataFolder(), "config.yml");
        FileConfiguration configchange = YamlConfiguration
                .loadConfiguration(config);
        if (!config.exists()) 
        {
            try 
            {
                configchange.set("Version", "0.1.4");
            	configchange.save(config);
            } 
            catch (IOException e) 
            {
            	getLogger().warning("在文件加载时出现了错误!");
                e.printStackTrace();
            }
        }
        saveDefaultConfig();
        getLogger().info("文件载入完成.");
        
        //接入 Vault
        getLogger().info("正在 Hook Vault...");
		        
    	//注册监听器
        getLogger().info("正在载入监听器...");
        Bukkit.getPluginManager().registerEvents(new LevelChat(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTip(), this);
        getLogger().info("监听器已接入.");
        
        //注册命令
        getLogger().info("正在注册命令...");
        Bukkit.getPluginCommand("biu").setExecutor(new BiuCommand());
        Bukkit.getPluginCommand("laba").setExecutor(new LabaCommand());
        getLogger().info("命令已注册.");
        
        //载入完成提示
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > 载入成功.");
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > §f欢迎使用 StarTool, 版本 " + version +", 作者 StarWish");
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > §f感谢您的使用!");
    }

    @Override
    public void onDisable()
    {
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > §7正在关闭插件...");
        saveDefaultConfig();
    }
    

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("startool"))
        {
            if (args.length == 0)
            {
                sender.sendMessage("§bStarTool " + version +", By StarWish_");
                sender.sendMessage("§f");
                sender.sendMessage("/laba(/lb) [内容] 发送全服公告");
                sender.sendMessage("/biu <玩家> 让一个玩家立即去世");
                sender.sendMessage("/stool uuid 获取你的UUID");
                sender.sendMessage("/stool clear <玩家> 清屏全服/玩家的聊天栏");
                sender.sendMessage("/stool version 显示插件目前版本");
                sender.sendMessage("/stool reload 重载插件配置");
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
                       if (args.length == 1)
                       {	
                        for (int i = 0; i <= 60; i++)
                        Bukkit.broadcastMessage("       ");
                        sender.sendMessage("§b" + prefix + " §b> §e全服清屏成功~");
                        return true;
                       }
                       if (sender.hasPermission("startool.clearscreen.other"))
                       {
                    	   Player otherp = Bukkit.getPlayer(args[1]);
                           if (args.length == 2)
                           {
                    	    for (int i = 0; i <= 60; i++)
                    	    otherp.sendMessage("        ");
                    	    otherp.sendMessage("§b" + prefix + " §b> §e您已被管理员清屏!");
                    	    sender.sendMessage("§b" + prefix + " §b> §e成功为 §c" + otherp.getName() + " §e清屏!");
                    	    return true;
                           }
                           else if (otherp !=null) {
                    	   sender.sendMessage("§b" + prefix + " §b> §c玩家名字错误或不存在!");
                    	   return true;
                           }
                       }
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
            	if (sender instanceof ConsoleCommandSender) 
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
        }
		return false;
    }
}
