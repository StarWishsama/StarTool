package io.github.starwishsama.StarTool.Files;

import io.github.starwishsama.StarTool.StarToolMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    public static String posText;

    public static void loadCfg(){
        StarToolMain instance = StarToolMain.getInstance();
        File cfgFile = new File(instance.getDataFolder(), "config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);

        if (!cfgFile.exists()) {
            cfg.set("postext", "pos");
            try {
                cfg.save(cfgFile);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        posText = StarToolMain.getInstance().getConfig().getString("postext");

    }

    public static void saveCfg(){
        StarToolMain.getInstance().saveDefaultConfig();
    }
}
