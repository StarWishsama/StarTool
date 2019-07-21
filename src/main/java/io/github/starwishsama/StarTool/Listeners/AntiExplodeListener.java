package io.github.starwishsama.StarTool.Listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

public class AntiExplodeListener implements Listener{
    @EventHandler(ignoreCancelled = true)
    public void onExplode (EntityExplodeEvent e){
        List<Block> b = e.blockList();
        b.clear();
    }
}
