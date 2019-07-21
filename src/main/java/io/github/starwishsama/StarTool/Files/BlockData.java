package io.github.starwishsama.StarTool.Files;

import io.github.starwishsama.StarTool.StarToolMain;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockData {
    private static StarToolMain instance = StarToolMain.getInstance();
    private static File blockData = new File(instance.getDataFolder(), "blockdata.yml");
    public static List<Location> campFires = new ArrayList<>();

    public static void loadCfg(){
        FileConfiguration fc = YamlConfiguration.loadConfiguration(blockData);
        if (fc.get("campFires") != null)
            campFires = (List<Location>) fc.getList("campFires");
    }

    public static void saveCfg(){
        FileConfiguration fc = YamlConfiguration.loadConfiguration(blockData);
        fc.set("campFires", campFires);
        try {
            fc.save(blockData);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
