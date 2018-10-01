package top.starwish.startool.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class configsetup {
    public static String Version = null;
    public static boolean EnableLaba = true;
    public static int LabaPrice = 0;

    public static FileConfiguration getConfig(){
       LabaPrice = getConfig().getInt("LabaPrice");
       Version = getConfig().getString("Version");
       EnableLaba = getConfig().getBoolean("EnableLabaFunction");
       return getConfig();
    }
}
