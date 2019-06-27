package io.github.starwishsama.StarTool.Config;

import com.alibaba.fastjson.*;
import io.github.starwishsama.StarTool.Objects.Home;
import io.github.starwishsama.StarTool.StarToolStartup;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Config {
    public static int labaPrice = 0;
    public static boolean labaFunction = true;
    public static int healCursePrice = 0;
    public static String posText = "pos";
    public static boolean posTranslate = true;
    public static String playerJoinMsg = "&a欢迎来到服务器!";
    public static String pluginPrefix = "&bStarTool &r> ";
    public static boolean isChatFormat = true;
    public static String chatFormat = "";

    //Local Message
    public static String notAPlayer = "";
    public static String usage = "";
    public static String noPermission = "";
    public static String onServerStartUp = "";
    public static String onServerStop = "";

    //Bot Settings
    public static String bot_cmd_prefix;
    public static int coolq_port = 0;
    public static int bot_port = 0;
    public static String coolq_link = "";
    public static long owner_QQ = 0;
    public static long group = 0;

    public static Map<UUID, Location> backLocation = new HashMap<>();
    public static Map<UUID, List<Home>> playerHomes = new HashMap<>();
    public static Map<UUID, UUID> tpRequests = new HashMap<>();
    public static Map<UUID, UUID> tpaHereRequests = new HashMap<>();

    public static void loadConfig(){
        labaPrice = Utils.getCfg().getInt("labaprice");
        labaFunction = Utils.getCfg().getBoolean("labafunction");
        isChatFormat = Utils.getCfg().getBoolean("chatformat-switch");
        chatFormat = Utils.getCfg().getString("chatformat");
        healCursePrice = Utils.getCfg().getInt("healcurseprice");
        posText = Utils.getCfg().getString("postext");
        posTranslate = Utils.getCfg().getBoolean("postranslate");
        playerJoinMsg = Utils.getCfg().getString("playerjoinmsg");
        pluginPrefix = Utils.getCfg().getString("plugin-prefix");

        notAPlayer = Utils.getCfg().getString("messages.not-a-player");
        usage = Utils.getCfg().getString("messages.usage");
        noPermission = Utils.getCfg().getString("messages.no-permission");

        bot_cmd_prefix = Utils.getCfg().getString("cmd-prefix");
        coolq_port = Utils.getCfg().getInt("coolq-port");
        bot_port = Utils.getCfg().getInt("bot-port");
        coolq_link = Utils.getCfg().getString("coolq-link");
        owner_QQ = Utils.getCfg().getLong("owner-qq");
        group = Utils.getCfg().getLong("group");

        onServerStartUp = Utils.getCfg().getString("bot-messages.on-server-startup");
        onServerStop = Utils.getCfg().getString("bot-messages.on-server-stop");

        /**try {
            JSONObject playerHomeObject = JSONObject.parseObject(Utils.readFile(StarToolStartup.getInstance().getDataFolder() + "/playerhome.json"));
            if (JSON.parseObject(playerHomeObject.getString("PlayerHomes"), new TypeReference<Map<UUID, List<Home>>>(){}) != null)
                playerHomes = JSON.parseObject(playerHomeObject.getString("PlayerHomes"), new TypeReference<Map<UUID, List<Home>>>(){});
        } catch (Exception e){
            e.printStackTrace();
        } */
    }

    public static void saveHomes(){
        JSONObject playerHomeObject = new JSONObject();
        playerHomeObject.put("PlayerHomes", playerHomes);
        Utils.createFile(StarToolStartup.getInstance().getDataFolder() + "/playerhome.json", playerHomeObject.toJSONString());
    }
}
