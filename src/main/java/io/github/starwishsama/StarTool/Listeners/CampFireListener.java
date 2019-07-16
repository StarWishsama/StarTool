package io.github.starwishsama.StarTool.Listeners;

import io.github.starwishsama.StarTool.Files.BlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CampFireListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onPlaceFire(BlockPlaceEvent bp){
        if (bp.getBlock().getType() == Material.CAMPFIRE){
            BlockData.campFires.add(bp.getBlock().getLocation());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBreakFire(BlockBreakEvent bb){
        if (bb.getBlock().getType() == Material.CAMPFIRE){
            BlockData.campFires.remove(bb.getBlock().getLocation());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(PlayerMoveEvent pm){
        Location pl = pm.getPlayer().getLocation();
        for (int i = 0; i < BlockData.campFires.size(); i++){
            if (BlockData.campFires.get(i).distance(pl) <= 5){
                Player p = pm.getPlayer();
                PotionEffect pe = new PotionEffect(PotionEffectType.REGENERATION, 100, 0, true, true, true);
                if (!p.hasPotionEffect(PotionEffectType.HEAL)) {
                    p.addPotionEffect(pe);
                }
            }
        }
    }
}
