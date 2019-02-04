package top.starwish.StarTool.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.math.BigDecimal;

// https://blog.csdn.net/u013066244/article/details/53197756 部分引用
@SuppressWarnings("deprecation")
public class Utils {
    public static boolean isExist(String p) {
        return Bukkit.getPlayer(p) != null;
    }
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
