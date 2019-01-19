package top.starwish.StarTool.Files;

import top.starwish.StarTool.StarToolStartup;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

// Reworked
public class Config {
    final static String Version = "Version";
    final static String DefaultVersion = "0.1.7-SNAPSHOT";
    public static String getVersion = DefaultVersion;

    final static String LabaFunction = "EnableLabaFunction";
    final static boolean DefaultEnableLaba = true;
    public static boolean getEnableLaba = DefaultEnableLaba;

    final static String LabaPrice = "LabaPrice";
    final static int DefaultLabaPrice = 50;
    public static int getLabaPrice = DefaultLabaPrice;

    final static String LevelChatPrefix = "EnableLevelChatPrefix";
    final static boolean DefaultLevelChatPrefix = true;
    public static boolean getEnablePrefix = DefaultLevelChatPrefix;

    final static String HealCursePrice = "HealCursePrice";
    final static int DefaultHealCursePrice = 120;
    public static int getCursePrice = DefaultHealCursePrice;

    final static String PosText = "PosText";
    final static String DefaultPosText = "pos";
    public static String getPosText = DefaultPosText;

    final static String PosTranlate = "PosTranlate";
    final static boolean DefaultPosTranlate = true;
    public static boolean getPosTranlate = DefaultPosTranlate;

    final static String JoinMessage = "PlayerJoinMessage";
    final static String DefaultJoinMessage = "&b欢迎来到服务器!";
    public static String getJoinMessage = DefaultJoinMessage;

    public static StarToolStartup p;

    public static void reloadConfig(){
        p.reloadConfig();
        ReadConfig();
        p.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e重载完成."));
    }

    public static void ReadConfig(){
        if (p == null) return;
        FileConfiguration config = p.getConfig();
        p.saveDefaultConfig();

        config.addDefault(Version, DefaultVersion);
        config.addDefault(LabaFunction, DefaultEnableLaba);
        config.addDefault(LabaPrice, DefaultLabaPrice);
        config.addDefault(LevelChatPrefix, DefaultLevelChatPrefix);
        config.addDefault(HealCursePrice, DefaultHealCursePrice);
        config.addDefault(PosTranlate, DefaultPosTranlate);
        config.addDefault(PosText, DefaultPosText);
        config.addDefault(JoinMessage, DefaultJoinMessage);

        Config.getVersion = p.getConfig().getString("Version");
        Config.getEnableLaba = p.getConfig().getBoolean("EnableLaba");
        Config.getLabaPrice = p.getConfig().getInt("LabaPrice");
        Config.getEnablePrefix = p.getConfig().getBoolean("EnableLevelChatPrefix");
        Config.getCursePrice = p.getConfig().getInt("HealCursePrice");
        Config.getPosText = p.getConfig().getString("PosText");
        Config.getPosTranlate = p.getConfig().getBoolean("PosTranlate");
        Config.getJoinMessage = p.getConfig().getString("PlayerJoinMessage");
    }
}
