package top.starwish.StarTool.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;


@SuppressWarnings("deprecation")
public class Utils {
    public static boolean isExist(String p) {
        return Bukkit.getPlayer(p) != null;
    }
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
