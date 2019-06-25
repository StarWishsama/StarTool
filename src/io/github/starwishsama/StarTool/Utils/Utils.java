package io.github.starwishsama.StarTool.Utils;

import io.github.starwishsama.StarTool.StarToolStartup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("deprecation")
public class Utils {
    public static boolean isExist(String p) {
        return Bukkit.getPlayer(p) != null;
    }
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static FileConfiguration getCfg(){
        return StarToolStartup.getInstance().getConfig();
    }
}