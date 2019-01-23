package top.starwish.StarTool.Misc;

import org.bukkit.Bukkit;

public class SPlayer {
    public static boolean isExist(String PlayerName) {return Bukkit.getPlayer(PlayerName) != null;}
}