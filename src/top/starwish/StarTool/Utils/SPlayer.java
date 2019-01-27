package top.starwish.StarTool.Utils;

import org.bukkit.Bukkit;

public class SPlayer {
    public static boolean isExist(String p) {
        return Bukkit.getPlayer(p) != null;
    }
}