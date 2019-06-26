package io.github.starwishsama.StarTool.Config;

import io.github.starwishsama.StarTool.Entity.PlayerHomes;
import io.github.starwishsama.StarTool.Utils.Utils;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Config {
    public static int LabaPrice = 0;
    public static boolean LabaFunction = true;
    public static boolean ChatPrefix = true;
    public static int HealCursePrice = 0;
    public static String PosText = "";
    public static boolean PosTranslate = true;
    public static String PlayerJoinMsg = "";
    public static String plugin_prefix = "";

    //Local Message
    public static String notaplayer = "";
    public static String usage = "";
    public static String nopermission = "";

    //Bot Settings
    public static String[] bot_cmd_prefix;
    public static int coolq_port = 0;
    public static int bot_port = 0;
    public static String coolq_link = "";
    public static long owner_QQ = 0;

    public static Map<UUID, Location> backLocation = new HashMap<>();
    public static Map<UUID, PlayerHomes> playerHomes = new HashMap<>();

    public static void loadConfig(){
        LabaPrice = Utils.getCfg().getInt("labaprice");
        LabaFunction = Utils.getCfg().getBoolean("labafunction");
        ChatPrefix = Utils.getCfg().getBoolean("chatprefix");
        HealCursePrice = Utils.getCfg().getInt("healcurseprice");
        PosText = Utils.getCfg().getString("postext");
        PosTranslate = Utils.getCfg().getBoolean("postranslate");
        PlayerJoinMsg = Utils.getCfg().getString("playerjoinmsg");
        plugin_prefix = Utils.getCfg().getString("plugin-prefix");

        notaplayer = Utils.getCfg().getString("messages.not-a-player");
        usage = Utils.getCfg().getString("messages.usage");
        nopermission = Utils.getCfg().getString("messages.no-permission");

        bot_cmd_prefix = (String[]) Utils.getCfg().get("cmd-prefix");
        coolq_port = Utils.getCfg().getInt("coolq-port");
        bot_port = Utils.getCfg().getInt("bot-port");
        coolq_link = Utils.getCfg().getString("coolq-link");
        owner_QQ = Utils.getCfg().getLong("owner-qq");
    }
}
