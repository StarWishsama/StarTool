package top.starwish.StarTool;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import top.starwish.StarTool.Listeners.*;
import top.starwish.StarTool.Commands.*;

public class StarToolStartup extends JavaPlugin {
    private static StarToolStartup instance;

    static Plugin plugin;

    public static StarToolStartup getInstance() {
        return instance;
    }

    public void SetupConfig(){
        FileConfiguration config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    @Override
    public void onEnable() {
        //加载文件
        getLogger().info("正在加载文件...");
        SetupConfig();
        getLogger().info("文件载入完成.");

        //注册监听器
        getLogger().info("正在加载监听器...");
        Bukkit.getPluginManager().registerEvents(new LevelChatPrefix(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTips(), this);
        Bukkit.getPluginManager().registerEvents(new ChatSendMyPos(), this);
        Bukkit.getPluginManager().registerEvents(new AutoWelcome(), this);
        Bukkit.getPluginManager().registerEvents(new StarToolCommand(), this);
        getLogger().info("监听器已加载.");

        //注册命令
        getLogger().info("正在注册命令...");
        Bukkit.getPluginCommand("curse").setExecutor(new CurseCommand());
        Bukkit.getPluginCommand("startool").setExecutor(new StarToolCommand());
        Bukkit.getPluginCommand("gc").setExecutor(new GcCommand());
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
        getLogger().info("加载成功.");
        getLogger().info("欢迎使用 StarTool, 版本 " + getDescription().getVersion() + ", 作者 StarWish");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("StarTool > 正在关闭插件...");
        saveDefaultConfig();
    }
}
