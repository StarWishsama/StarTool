package top.starwish.StarTool.Config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.bukkit.entity.Player;
import top.starwish.StarTool.Entity.BotUser;
import top.starwish.StarTool.StarToolStartup;
import top.starwish.StarTool.Utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CheckinData {
    public static Map<Long, BotUser> botUser = new HashMap<>();
    public static HashMap<Player, Long> isBind = new HashMap<>();
    static String checkinLoc = Utils.getCfg().getString("checkin-file-location");
    static String bindStatusLoc = StarToolStartup.getInstance().getDataFolder() + "bindstatus.json";
    static File bindData = new File(StarToolStartup.getInstance().getDataFolder(), "bindstatus.json");

    public static void saveData(){
        JSONObject checkinData = new JSONObject();
        checkinData.put("checkinUsers", botUser);
        Utils.createFile(checkinLoc, checkinData.toJSONString());

        JSONObject bindStatus = new JSONObject();
        bindStatus.put("bindStatus", isBind);
        Utils.createFile(bindStatusLoc, bindStatus.toJSONString());
    }

    public static void reloadData(){
        if (!Utils.getCfg().getString("checkin-file-location").isEmpty()){
            try {
                JSONObject botUsers = JSONObject.parseObject(Utils.readFile(Utils.getCfg().getString("checkin-file-location")));
                CheckinData.botUser = JSON.parseObject(botUsers.getString("checkinUsers"), new TypeReference<Map<Long, BotUser>>(){});
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if (bindData.exists()){
            try {
                JSONObject bindStatus = JSONObject.parseObject(bindStatusLoc);
                CheckinData.isBind = JSON.parseObject(bindStatus.getString("bindStatus"), new TypeReference<HashMap<Player, Long>>(){});
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
