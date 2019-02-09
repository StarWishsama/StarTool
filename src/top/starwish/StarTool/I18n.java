package top.starwish.StarTool;

import org.bukkit.configuration.file.FileConfiguration;

public enum I18n {

    //WIP
    USAGE("usage"),
    NOT_A_PLAYER("not-a-player"),
    NO_PERMISSIONS("no-permission");

    I18n(String key) {
        FileConfiguration Config = StarToolStartup.getInstance().getConfig();
       /** if (config.isString(key)) {
            this.message = translateColorCode(config.getString(key));
        } else if (config.isList(key)) {
            this.message = translateColorCode(config.getStringList(key));
        }
        */
    }
}
