package io.github.starwishsama.StarTool.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static boolean isExist(String p) {
        return Bukkit.getPlayer(p) != null;
    }
}
