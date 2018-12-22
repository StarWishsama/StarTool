package top.starwish.startool.config;

import org.bukkit.configuration.file.FileConfiguration;

// WIP
public class configsetup {
    public static String Version = "0.1.5.3-SNAPSHOT";
    public static boolean EnableLaba = true;
    public static int LabaPrice = 0;
    public static boolean EnableChatPrefix = true;

    public static FileConfiguration getConfig(){
       LabaPrice = getConfig().getInt("LabaPrice");
       Version = getConfig().getString("Version");
       EnableLaba = getConfig().getBoolean("EnableLabaFunction");
       EnableChatPrefix = getConfig().getBoolean("EnableExpChatPrefix");
       return getConfig();
    }

    public static FileConfiguration reloadConfig(){
        reloadConfig();
        return reloadConfig();
    }
}
