package top.starwish.StarTool.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class LevelUpTips implements Listener
{
    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent event)
    {
            Player p = event.getPlayer();
            if (event.getNewLevel() < event.getOldLevel()) return;
            //p.sendTitle("§e§l耶! 恭喜你升级了!", "§a当前等级为: " + event.getNewLevel(), 50, 80, 50);
            p.sendMessage("§bStarTool > §e" + event.getPlayer().getName() + "§e,恭喜你升级至 " + event.getNewLevel() + "§e级!");
            return;
    }
}
