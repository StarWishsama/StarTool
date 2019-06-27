package io.github.starwishsama.StarTool.Objects;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class Home {
    private Map<String, Location> homeLocation = new HashMap<>();
    private String homeName = "";

    public Home(){
    }

    public void setHomeLocation(Map<String, Location> locMap){
        homeLocation = locMap;
    }

    public Map<String, Location> getHomeLocation(){
        return homeLocation;
    }

    public void setHomeName(String name){
        homeName = name;
    }

    public String getHomeName(){
        return homeName;
    }
}
