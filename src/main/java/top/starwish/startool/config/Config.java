package top.starwish.startool.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import top.starwish.startool.startup.StarToolStartup;

import java.util.logging.Logger;

// Reworked
public class Config {
    final static String Version = "Version";
    final static String VersionD = "0.1.5.4-SNAPSHOT";
    public static String DefaultVersion = VersionD;

    final static String EnableLaba = "EnableLabaFunction";
    final static boolean EnableLabaD = true;
    public static boolean DefaultEnableLaba = EnableLabaD;

    final static String LabaPrice = "LabaPrice";
    final static int LabaPriceD = 0;
    public static int DefaultLabaPrice = LabaPriceD;

    final static String EnableLevelChatPrefix = "EnableLevelChatPrefix";
    final static boolean LevelChatPrefixD = true;
    public static boolean DefaultEnablePrefix = LevelChatPrefixD;

    public static StarToolStartup p;

    public static void reloadConfig(){
        p.reloadConfig();
        p.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&bStarTool > &e重载完成."));
    }

    public static void ConfigValues(){
        if (p == null)
            return;

        p.saveDefaultConfig();

        FileConfiguration config = p.getConfig();
        Logger logger = p.getLogger();

        config.addDefault(Version, VersionD);
        config.addDefault(EnableLaba, EnableLabaD);
        config.addDefault(LabaPrice, LabaPriceD);
        config.addDefault(EnableLevelChatPrefix, LevelChatPrefixD);

        Config.DefaultVersion = p.getConfig().getString("Version");
        Config.DefaultEnableLaba = p.getConfig().getBoolean("EnableLaba");
        Config.DefaultLabaPrice = p.getConfig().getInt("DefaultLabaPrice");
        Config.DefaultEnablePrefix = p.getConfig().getBoolean("DefaultEnablePrefix");
    }
}
