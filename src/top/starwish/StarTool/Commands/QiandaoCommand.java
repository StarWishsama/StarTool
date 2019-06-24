package top.starwish.StarTool.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.starwish.StarTool.Config.CheckinData;
import top.starwish.StarTool.Entity.BotUser;
import top.starwish.StarTool.Utils.Utils;

import java.util.Date;
import java.util.Random;


public class QiandaoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        switch (cmd.getName().toLowerCase()){
            case "qd":
            case "checkin":
            case "签到":
                if (sender instanceof Player) {
                    if (CheckinData.isBind.containsKey(sender)) {
                        if (CheckinData.botUser.containsKey(CheckinData.isBind.get(sender))) {
                            BotUser user = CheckinData.botUser.get(CheckinData.isBind.get(sender));
                            if (Utils.isCheckInReset(new Date(), user.getLastCheckInTime())) {
                                double point = Double.parseDouble(String.format("%.1f", new Random().nextInt(10) * Utils.checkInPointBonus(user.getCheckInTime())));
                                user.setCheckInPoint(user.getCheckInPoint() + point);
                                user.setLastCheckInTime(new Date());
                                user.setCheckInTime(user.getCheckInTime() + 1);
                                sender.sendMessage(Utils.color("&bStarTool > &a签到成功! 获得 " + point + " 点积分."));
                            } else sender.sendMessage(Utils.color("&bStarTool > &c你今天已经签到过了!"));
                        } else {
                            double point = new Random().nextInt(10);
                            BotUser user = new BotUser();
                            user.setUserQQ(CheckinData.isBind.get(sender));
                            user.setCheckInTime(1);
                            user.setLastCheckInTime(new Date());
                            user.setCheckInPoint(point);
                            CheckinData.botUser.put(CheckinData.isBind.get(sender), user);
                            sender.sendMessage(Utils.color("&bStarTool > &a签到成功! 获得 " + point + " 点积分."));
                        }
                    } else sender.sendMessage(Utils.color("&bStarTool > &c你还没有绑定无名 Bot 账号! 使用 /bind <QQ号> 来绑定."));
                } else sender.sendMessage(Utils.color("&bStarTool > &c你必须是游戏内的玩家!"));
                break;
            case "bind":
                if (CheckinData.isBind.containsKey(sender)){
                    sender.sendMessage(Utils.color("&bStarTool > &c你已经绑定过账号了!"));
                } else {
                    if (!args[0].isEmpty()){
                        CheckinData.isBind.put((Player) sender, Long.parseLong(args[0]));
                        sender.sendMessage(Utils.color("&bStarTool > &a绑定成功!"));
                    } else sender.sendMessage(Utils.color("&bStarTool > &c请输入你的QQ号!"));
                }
                break;
        }
        return true;
    }
}
