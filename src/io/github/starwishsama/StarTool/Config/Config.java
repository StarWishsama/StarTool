package io.github.starwishsama.StarTool.Config;

import io.github.starwishsama.StarTool.Utils.Utils;

public class Config {
    public static int LabaPrice = 0;
    public static boolean LabaFunction = true;
    public static boolean ChatPrefix = true;
    public static int HealCursePrice = 0;
    public static String PosText = "";
    public static boolean PosTranslate = true;
    public static String PlayerJoinMsg = "";
    public static String plugin_prefix = "";
    public static String chat_format = "";

    //Local Message
    public static String notaplayer = "";
    public static String usage = "";
    public static String nopermission = "";

    public static void loadConfig(){
        LabaPrice = Utils.getCfg().getInt("labaprice");
        LabaFunction = Utils.getCfg().getBoolean("labafunction");
        ChatPrefix = Utils.getCfg().getBoolean("chatprefix");
        HealCursePrice = Utils.getCfg().getInt("healcurseprice");
        PosText = Utils.getCfg().getString("postext");
        PosTranslate = Utils.getCfg().getBoolean("postranslate");
        PlayerJoinMsg = Utils.getCfg().getString("playerjoinmsg");
        plugin_prefix = Utils.getCfg().getString("plugin-prefix");
        chat_format = Utils.getCfg().getString("chat-format");

        notaplayer = Utils.getCfg().getString("messages.not-a-player");
        usage = Utils.getCfg().getString("messages.usage");
        nopermission = Utils.getCfg().getString("messages.no-permission");
    }
}
