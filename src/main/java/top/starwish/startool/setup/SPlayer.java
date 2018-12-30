package top.starwish.startool.setup;

import org.bukkit.Bukkit;

public class SPlayer {
    public static boolean isExist(String p) {return Bukkit.getPlayer(p) != null;}
}