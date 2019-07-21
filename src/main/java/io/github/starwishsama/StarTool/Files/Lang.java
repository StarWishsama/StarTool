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
    public static String wrongAmount;
    public static String notOnline;
    public static String noEnoughMoney;
    public static String noNegative;
    public static String errorMessage;
    public static String transactionSuccess;
    public static String receiveSuccess;

    public static void loadLang(){
        File langFile = new File(instance.getDataFolder(), "lang.yml");
        FileConfiguration lang = YamlConfiguration.loadConfiguration(langFile);
        if (!langFile.exists()) setupLang();
        if (lang.getString("notOnline") == null) setupLang();
        pluginPrefix = lang.getString("pluginPrefix");
        noPermission = lang.getString("noPermission");
        notPlayer = lang.getString("notPlayer");
        atNotifyOn = lang.getString("atNotifyOn");
        atNotifyOff = lang.getString("atNotifyOff");
        playerSleepMsg = lang.getString("playerSleepMsg");
        restartMsg = lang.getString("restartMsg");
        wrongAmount = lang.getString("wrongAmount");
        notOnline = lang.getString("notOnline");
        noEnoughMoney = lang.getString("noEnoughMoney");
        noNegative = lang.getString("noNegative");
        errorMessage = lang.getString("errorMessage");
        transactionSuccess = lang.getString("transactionSuccess");
        receiveSuccess = lang.getString("receiveSuccess");
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
            lang.set("wrongAmount", wrongAmount);
            lang.set("notOnline", notOnline);
            lang.set("noEnoughMoney", noEnoughMoney);
            lang.set("noNegative", noNegative);
            lang.set("errorMessage", errorMessage);
            lang.set("transactionSuccess", transactionSuccess);
            lang.set("receiveSuccess", receiveSuccess);
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
        lang.set("wrongAmount", "&c你输入了错误的金额! 请检查格式.");
        lang.set("notOnline", "&c这个玩家不在线!");
        lang.set("noEnoughMoney", "&c你没有足够的金钱!");
        lang.set("noNegative", "&c你不能透支转账!");
        lang.set("errorMessage", "&c在转账时发生了错误");
        lang.set("transactionSuccess", "&a转账给 %s 成功!");
        lang.set("receiveSuccess", "&a你收到了来自 %s 的转账, 金额: %d");
        try {
            lang.save(langFile);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
