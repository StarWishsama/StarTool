package io.github.starwishsama.StarTool;

import cc.moecraft.icq.sender.IcqHttpApi;

import io.github.starwishsama.StarTool.Bot.PicqBotXUtills;
import io.github.starwishsama.StarTool.CheckUpdate.UpdateChecker;
import io.github.starwishsama.StarTool.Commands.*;
import io.github.starwishsama.StarTool.Config.Config;
import io.github.starwishsama.StarTool.Listeners.*;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

import static cn.hutool.core.lang.Console.log;

public class StarToolStartup extends JavaPlugin {
    private static StarToolStartup instance;
    public static Thread bot_thread;
    public static PicqBotXUtills bot;
    private boolean BotStatus = true;

    @Override
    public void onEnable() {
        instance = this;

        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists())
            try {
                Files.copy(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("config.yml")), config.toPath());
            } catch (Exception e){
                e.printStackTrace();
            }

        Config.loadConfig();

        if (!BotStatus){
            bot();
            BotStatus = true;
            if (Config.onServerStartUp != null){
                getApi().sendGroupMsg(Config.group, Config.onServerStartUp);
            }
        }

        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTips(), this);
        Bukkit.getPluginManager().registerEvents(new AutoWelcome(), this);
        Bukkit.getPluginManager().registerEvents(new CommandHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeadLocation(), this);

        registerCommands();

        getLogger().info("正在检查服务器是否安装 Vault 以启用小喇叭..");
        if (Bukkit.getPluginManager().getPlugin("Vault") == null){
            getLogger().warning("未发现服务器安装了 Vault, 将自动禁用小喇叭指令!");
        } else {
            Objects.requireNonNull(Bukkit.getPluginCommand("laba")).setExecutor(new LabaCommand());
            getLogger().info("已发现 Vault 插件!");
        }

        getLogger().info("欢迎使用 StarTool, 版本 " + getDescription().getVersion());
        UpdateChecker.CheckUpdate();
    }

    @Override
    public void onDisable() {
        if (Config.onServerStop != null){
            getApi().sendGroupMsg(Config.group, Config.onServerStop);
        }
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("正在关闭插件...");
        saveDefaultConfig();
    }

    public static StarToolStartup getInstance() {
        return instance;
    }

    private void registerCommands(){
        Bukkit.getPluginCommand("curse").setExecutor(new CurseCommand());
        Bukkit.getPluginCommand("startool").setExecutor(new StarToolCommand());
        Bukkit.getPluginCommand("gc").setExecutor(new GcCommand());
        Bukkit.getPluginCommand("tpa").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("tpaccept").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("tpdeny").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("bed").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("sethome").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("gm").setExecutor(new GameModeCommand());
        Bukkit.getPluginCommand("enderchest").setExecutor(new PortableEnderChestCommand());
        Bukkit.getPluginCommand("msg").setExecutor(new PrivateMsgCommand());
        Bukkit.getPluginCommand("back").setExecutor(new BackCommand());
        Bukkit.getPluginCommand("debug").setExecutor(new DebugCommand());
        Bukkit.getPluginCommand("fly").setExecutor(new FlyCommand());
    }

    /**
     * From https://github.com/nitu2003/ConnectionRe/
     * @author nitu2003
     */
    void bot() {
        int botPort  = Config.bot_port;
        int coolqPort = Config.coolq_port;
        String coolqLink = Config.coolq_link;

        log("酷Q机器人 > "+ coolqLink + ":" + coolqPort);
        log("本地机器人 > 127.0.0.1:" + botPort);

        bot = new PicqBotXUtills(botPort, coolqPort, coolqLink);

        bot_thread = new Thread(bot);
        bot_thread.start();
    }

    public static IcqHttpApi getApi() {
        return bot.getApi();
    }
}
