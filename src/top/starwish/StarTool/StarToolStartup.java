package top.starwish.StarTool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import top.starwish.StarTool.CheckUpdate.UpdateChecker;
import top.starwish.StarTool.Config.CheckinData;
import top.starwish.StarTool.Entity.BotUser;
import top.starwish.StarTool.Listeners.*;
import top.starwish.StarTool.Commands.*;
import top.starwish.StarTool.Listeners.AutoWelcome;
import top.starwish.StarTool.Listeners.ChatSendMyPos;
import top.starwish.StarTool.Listeners.LevelChatPrefix;
import top.starwish.StarTool.Listeners.LevelUpTips;
import top.starwish.StarTool.Utils.Utils;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StarToolStartup extends JavaPlugin {
    private static StarToolStartup instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists())
            config.mkdirs();


        Bukkit.getPluginManager().registerEvents(new LevelChatPrefix(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTips(), this);
        Bukkit.getPluginManager().registerEvents(new ChatSendMyPos(), this);
        Bukkit.getPluginManager().registerEvents(new AutoWelcome(), this);
        Bukkit.getPluginManager().registerEvents(new CommandHandler(), this);

        Bukkit.getPluginCommand("curse").setExecutor(new CurseCommand());
        Bukkit.getPluginCommand("startool").setExecutor(new StarToolCommand());
        Bukkit.getPluginCommand("gc").setExecutor(new GcCommand());
        Bukkit.getPluginCommand("tp").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("tphere").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("tpa").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("tpyes").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("tpno").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("tpaccept").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("tpdeny").setExecutor(new TeleportCommand());
        //Bukkit.getPluginCommand("home").setExecutor(new HomeCommand());
        Bukkit.getPluginCommand("gm").setExecutor(new GameModeCommand());
        Bukkit.getPluginCommand("qd").setExecutor(new QiandaoCommand());
        Bukkit.getPluginCommand("checkin").setExecutor(new QiandaoCommand());
        Bukkit.getPluginCommand("info").setExecutor(new QiandaoCommand());
        Bukkit.getPluginCommand("shop").setExecutor(new QiandaoCommand());

        getLogger().info("正在检查服务器是否安装 Vault 以启用小喇叭..");
        if (Bukkit.getPluginManager().getPlugin("Vault") == null){
            getLogger().warning("未发现服务器安装了 Vault, 将自动禁用小喇叭指令!");
        } else {
            Bukkit.getPluginCommand("laba").setExecutor(new LabaCommand());
            getLogger().info("已发现 Vault 插件!");
        }

        getLogger().info("欢迎使用 StarTool, 版本 " + getDescription().getVersion());
        UpdateChecker.CheckUpdate();

        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            CheckinData.saveData();
            CheckinData.reloadData();
        },15*60*1000L,15*60*1000L);


        File bindData = new File(getDataFolder(), "bindstatus.json");
        if (!Utils.getCfg().getString("checkin-file-location").isEmpty()){
            try {
                JSONObject botUsers = JSONObject.parseObject(Utils.readFile(Utils.getCfg().getString("checkin-file-location")));
                CheckinData.botUser = JSON.parseObject(botUsers.getString("checkinUsers"), new TypeReference<Map<Long, BotUser>>(){});
            } catch (Exception e){
                e.printStackTrace();
            }
        } else
            getLogger().warning("未定义签到数据存放位置(需配合Nameless-Bot)!");

        if (bindData.exists()){
            try {
                JSONObject bindStatus = JSONObject.parseObject(getDataFolder() + "bindstatus.json");
                CheckinData.isBind = JSON.parseObject(bindStatus.getString("bindStatus"), new TypeReference<HashMap<Player, Long>>(){});
            } catch (Exception e){
                e.printStackTrace();
            }
        } else bindData.mkdirs();
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("正在关闭插件...");
        saveDefaultConfig();
    }

    public static StarToolStartup getInstance() {
        return instance;
    }
}
