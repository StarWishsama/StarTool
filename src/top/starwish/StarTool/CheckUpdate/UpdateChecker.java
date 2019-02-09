package top.starwish.StarTool.CheckUpdate;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import top.starwish.StarTool.StarToolStartup;
import top.starwish.StarTool.Utils.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {
    public static String getLatestVer(){
        String version = null;
        try {
            URL url = new URL("https://raw.githubusercontent.com/StarWishsama/StarTool/master/UpdateCheck.txt");
            InputStream a = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(a, "UTF-8"));
            version = br.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }
        return version;
    }
    public static boolean isLatest(){
        boolean isLatest = false;
        String latestver = getLatestVer();
        String current = StarToolStartup.getInstance().getDescription().getVersion();
        if (latestver.equalsIgnoreCase(current)){
            isLatest = true;
        }
        return isLatest;
    }
    public static void CheckUpdate(){
        new BukkitRunnable(){
            public void run(){
                if(isLatest()){
                    Bukkit.getConsoleSender().sendMessage(Utils.color("&bStarTool > &e您当前正在使用最新版本!"));
                } else Bukkit.getConsoleSender().sendMessage(Utils.color("&bStarTool > &e新版本 " + getLatestVer() +" 已发布, 请至 Github Releases 界面下载."));
            }
        }.runTaskAsynchronously(StarToolStartup.getInstance());
    }
}
