package io.github.starwishsama.StarTool.Files;

import io.github.starwishsama.StarTool.StarToolMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private final static File cfgFile = new File(StarToolMain.getInstance().getDataFolder(), "config.yml");
    private final static File playerDataFile = new File(StarToolMain.getInstance().getDataFolder(), "playerdata.yml");

    public static String posText;
    public static boolean antiExplode = true;
    public static List<PlayerData> playerDataList = new ArrayList<>();

    public static void loadCfg(){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);

        if (!cfgFile.exists()) {
            cfg.set("postext", "pos");
            cfg.set("antiexplode", true);
            try {
                cfg.save(cfgFile);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        posText = StarToolMain.getInstance().getConfig().getString("postext");
        antiExplode = StarToolMain.getInstance().getConfig().getBoolean("antiexplode");
    }

    public static void saveCfg(){
        StarToolMain.getInstance().saveDefaultConfig();
    }

    public static void loadPlayerData(){
        if (playerDataFile.exists()){
            FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
            playerDataList = (List<PlayerData>) playerData.getList("playerData");
        }
    }

    public static void savePlayerData(){
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        if (!playerDataFile.exists()){
            try {
                playerData.set("playerData", playerDataList);
                playerData.save(playerDataFile);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                playerData.set("playerData", playerDataList);
                playerData.save(playerDataFile);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
