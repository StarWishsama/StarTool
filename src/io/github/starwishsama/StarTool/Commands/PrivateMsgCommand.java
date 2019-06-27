package io.github.starwishsama.StarTool.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.starwishsama.StarTool.Utils.Utils;

import java.util.Objects;

public class PrivateMsgCommand implements CommandExecutor {
    @Override
    @Deprecated
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (cmd.getName().equalsIgnoreCase("msg")){
            switch (args.length){
                default:
                    sender.sendMessage(Utils.color("&bPrivateChat > &e/msg <玩家名> <内容>"));
                    break;
                case 2:
                    if (Utils.isExist(args[0]) && !args[1].isEmpty()){
                        Player msgPlayer = Bukkit.getPlayer(args[0]);
                        String msg = args[1];
                        Objects.requireNonNull(msgPlayer).sendMessage(Utils.color("&5" + msgPlayer.getName() + " > " + msg));
                    } else
                        sender.sendMessage(Utils.color("&bPrivateChat > &c玩家不存在/不在线哟w"));
                    break;
            }
        }
        return false;
    }
}
