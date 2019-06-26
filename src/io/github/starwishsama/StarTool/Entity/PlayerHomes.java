package io.github.starwishsama.StarTool.Entity;

import org.bukkit.Location;

import java.util.*;

public class PlayerHomes {
    private UUID playerUUID;
    private Map<String, Location> playerHomes = new HashMap<>();

    public PlayerHomes(){
    }

    public PlayerHomes(UUID playerUUID, Map<String, Location> playerHomes){
        this.playerUUID = playerUUID;
        this.playerHomes = playerHomes;
    }

    public void setPlayerUUID(UUID p){
        playerUUID = p;
    }

    public UUID getPlayerUUID(){
        return playerUUID;
    }

    public void setPlayerHomes(Map<String, Location> homes){
        playerHomes = homes;
    }

    public Map<String, Location> getPlayerHomes(){
        return playerHomes;
    }
}
