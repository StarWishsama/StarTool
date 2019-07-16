package io.github.starwishsama.StarTool.Files;

import io.github.starwishsama.StarTool.StarToolMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Lang {
    private static StarToolMain instance = StarToolMain.getInstance();

    public static String pluginPrefix;
    public static String noPermission;
    public static String notPlayer;
    public static String atNotifyOn;
    public static String atNotifyOff;
    public static String playerSleepMsg;
    public static String restartMsg;

    public static void loadLang(){
        File langFile = new File(instance.getDataFolder(), "lang.yml");
        FileConfiguration lang = YamlConfiguration.loadConfiguration(langFile);
        if (!langFile.exists())
            setupLang();
        if (lang.getString("restartMsg") == null)
            setupLang();
        if (lang.getDefaultSection() == null)
            setupLang();
        pluginPrefix = lang.getString("pluginPrefix");
        noPermission = lang.getString("noPermission");
        notPlayer = lang.getString("notPlayer");
        atNotifyOn = lang.getString("atNotifyOn");
        atNotifyOff = lang.getString("atNotifyOff");
        playerSleepMsg = lang.getString("playerSleepMsg");
        restartMsg = lang.getString("restartMsg");
    }

    public static void saveLang(){
        File langFile = new File(instance.getDataFolder(), "lang.yml");
        FileConfiguration lang = YamlConfiguration.loadConfiguration(langFile);
        if (langFile.exists()){
            lang.set("pluginPrefix", pluginPrefix);
            lang.set("noPermission", noPermission);
            lang.set("notPlayer", notPlayer);
            lang.set("atNotifyOn", atNotifyOn);
            lang.set("atNotifyOff", atNotifyOff);
            lang.set("playerSleepMsg", playerSleepMsg);
            lang.set("restartMsg", restartMsg);
        } else
            loadLang();
    }

    private static void setupLang(){
        File langFile = new File(instance.getDataFolder(), "lang.yml");
        FileConfiguration lang = YamlConfiguration.loadConfiguration(langFile);
        lang.set("pluginPrefix", "&bStarTool >&r ");
        lang.set("noPermission", "&c你没有权限!");
        lang.set("notPlayer", "&c你必须是一个玩家!");
        lang.set("atNotifyOn", "&a@ 提醒已开启");
        lang.set("atNotifyOff", "&a@ 提醒已关闭");
        lang.set("playerSleepMsg", "%s 想要睡觉, 推荐所有人上床睡觉以度过晚上.");
        lang.set("restartMsg", "&c服务器将在 %s 秒后重启!");
        try {
            lang.save(langFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
