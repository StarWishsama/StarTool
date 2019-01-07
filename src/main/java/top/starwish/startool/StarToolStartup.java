package top.starwish.startool;

import java.io.*;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import top.starwish.startool.Listeners.*;
import top.starwish.startool.Commands.*;
import top.starwish.startool.Files.Config;

public class StarToolStartup extends JavaPlugin {
    public void ConfigStartUp(){
        File config = new File(getDataFolder(), "config.yml");
        FileConfiguration configchange = YamlConfiguration
                .loadConfiguration(config);
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
            reloadConfig();
        }
    }

    @Override
    public void onEnable() {
        //加载文件
        getLogger().info("正在加载文件...");
        ConfigStartUp();
        getLogger().info("文件载入完成.");

        //注册监听器
        getLogger().info("正在加载监听器...");
        Bukkit.getPluginManager().registerEvents(new LevelChatPrefix(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTips(), this);
        Bukkit.getPluginManager().registerEvents(new ChatSendMyPos(), this);
        Bukkit.getPluginManager().registerEvents(new AutoWelcome(), this);
        getLogger().info("监听器已加载.");

        //注册命令
        getLogger().info("正在注册命令...");
        Bukkit.getPluginCommand("curse").setExecutor(new CurseCommand());
        Bukkit.getPluginCommand("startool").setExecutor(new StarToolCommand());
        getLogger().info("正在检查服务器是否安装 Vault 以启用小喇叭..");
        if (!Bukkit.getPluginManager().getPlugin("Vault").isEnabled()){
            getLogger().warning("未发现服务器安装了 Vault, 将自动禁用小喇叭指令!");
        }
        else {
            Bukkit.getPluginCommand("laba").setExecutor(new LabaCommand());
            getLogger().info("已发现 Vault 插件!");
        }
        getLogger().info("命令已注册.");

        //载入完成提示
        getLogger().info("StarTool  > 加载成功.");
        getLogger().info("StarTool  > 欢迎使用 StarTool, 版本 " + Config.getVersion + ", 作者 StarWish");
        getLogger().info( "StarTool  > 感谢您的使用!");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("StarTool > 正在关闭插件...");
        saveDefaultConfig();
    }
}
