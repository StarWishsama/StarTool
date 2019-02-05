package top.starwish.StarTool.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import top.starwish.StarTool.Utils.Utils;

public class TeleportCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("tp")){
            if (sender instanceof Player){
                if (sender.hasPermission("startool.commands.teleport")) {
                    if (args.length == 1) {
                        sender.sendMessage(Utils.color("&bStarTool > &e用法: /tp <玩家名字>"));
                    } else if (args.length == 2) {
                        if (Utils.isExist(args[0])) {
                            Location l = Bukkit.getPlayer(args[0]).getLocation();
                            ((Player) sender).teleport(l);
                        } else sender.sendMessage(Utils.color("&bStarTool > &c该玩家不在线或不存在!"));
                    } else sender.sendMessage(Utils.color("&bStarTool > &e用法: /tp <玩家名字>"));
                } else sender.sendMessage(Utils.color("&bStarTool > &c你没有权限!"));
            } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
        }
        else if (cmd.getName().equalsIgnoreCase("/tpa")){
            if (sender instanceof Player){
                if (sender.hasPermission("startool.commands.teleport.tpa")) {
                    if (args.length == 1) {
                        sender.sendMessage(Utils.color("&bStarTool > &e用法: /tpa <玩家名字>"));
                    } else if (args.length == 2) {
                        if (Utils.isExist(args[0])){
                            Player op = Bukkit.getPlayer(args[0]);
                            sender.sendMessage(Utils.color("&6传送请求已发送给 &a" + op));
                            op.sendMessage(Utils.color("&a" + sender.getName() + " &6请求传送到你的位置"));
                            op.sendMessage(Utils.color(""));
                            op.sendMessage(Utils.color("&a输入 /tpaccept 以接受传送请求"));
                            op.sendMessage(Utils.color("&c输入 /tpdeny 以拒绝传送请求"));
                            op.sendMessage(Utils.color(""));
                            // WIP
                        } else sender.sendMessage(Utils.color("&bStarTool > &c该玩家不在线或不存在!"));
                    } else sender.sendMessage(Utils.color("&bStarTool > &e用法: /tpa <玩家名字>"));
                } else sender.sendMessage(Utils.color("&bStarTool > &c你没有权限!"));
            } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
        }
        else sender.sendMessage(Utils.color("&bStarTool > &c请检查命令是否有误."));
        return true;
    }
    @EventHandler
    public void getAcceptStatus(PlayerCommandPreprocessEvent e){
        if (e.getMessage().equalsIgnoreCase("/tpaccept")){
            if (e.getPlayer().getName().equalsIgnoreCase("test")){

            }
        }
        else if (e.getMessage().equalsIgnoreCase("/tpdeny")){

        }
    }
}
