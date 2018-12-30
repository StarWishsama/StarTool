package top.starwish.startool.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import top.starwish.startool.setup.StarToolSetup;

// Reworked
public class Config {
    final static String Version = "Version";
    final static String VersionD = "0.1.5.4-SNAPSHOT";
    public static String DefaultVersion = VersionD;

    final static String EnableLaba = "EnableLabaFunction";
    final static boolean EnableLabaD = true;
    public static boolean DefaultEnableLaba = EnableLabaD;

    final static String LabaPrice = "LabaPrice";
    final static int LabaPriceD = 50;
    public static int DefaultLabaPrice = LabaPriceD;

    final static String EnableLevelChatPrefix = "EnableLevelChatPrefix";
    final static boolean LevelChatPrefixD = true;
    public static boolean DefaultEnablePrefix = LevelChatPrefixD;

    final static String HealCursePrice = "HealCursePrice";
    final static int HealCurseD = 120;
    public static int HCursePrice = HealCurseD;

    public static StarToolSetup p;

    public static void reloadConfig(){
        p.reloadConfig();
        p.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e重载完成."));
    }

    public static void ConfigValues(){
        if (p == null)
            return;

        p.saveDefaultConfig();

        FileConfiguration config = p.getConfig();

        config.addDefault(Version, VersionD);
        config.addDefault(EnableLaba, EnableLabaD);
        config.addDefault(LabaPrice, LabaPriceD);
        config.addDefault(EnableLevelChatPrefix, LevelChatPrefixD);
        config.addDefault(HealCursePrice, HealCurseD);

        Config.DefaultVersion = p.getConfig().getString("Version");
        Config.DefaultEnableLaba = p.getConfig().getBoolean("EnableLaba");
        Config.DefaultLabaPrice = p.getConfig().getInt("DefaultLabaPrice");
        Config.DefaultEnablePrefix = p.getConfig().getBoolean("DefaultEnablePrefix");
        Config.HCursePrice = p.getConfig().getInt("HealCursePrice");
    }
}
