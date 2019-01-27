package top.starwish.StarTool.Utils;

import org.bukkit.ChatColor;

public class TranlateColorCode {
    public static String tcc(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
