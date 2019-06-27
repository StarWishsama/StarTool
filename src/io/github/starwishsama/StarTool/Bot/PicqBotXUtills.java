package io.github.starwishsama.StarTool.Bot;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.command.CommandManager;
import cc.moecraft.icq.sender.IcqHttpApi;
import cc.moecraft.logger.environments.ColorSupportLevel;
import io.github.starwishsama.StarTool.Bot.BotCommands.VersionCommand;
import io.github.starwishsama.StarTool.Config.Config;

public class PicqBotXUtills implements Runnable {
    /**
     * From https://github.com/nitu2003/ConnectionRe/
     * @author nitu2003
     */
    final cc.moecraft.icq.PicqBotX bot;

    public PicqBotXUtills(int in, int out, String url) {
        PicqConfig config = new PicqConfig(in).setColorSupportLevel(ColorSupportLevel.DISABLED);
        bot = new cc.moecraft.icq.PicqBotX(config);
        bot.addAccount("MinatoAqua", url, out);
        bot.enableCommandManager(Config.bot_cmd_prefix);
        //注册机器人监听器
        //bot.getEventManager().registerListener();
        //注册机器人命令
        bot.getCommandManager().registerCommands(new VersionCommand());
    }

    public CommandManager getCommandManager() {
        return bot.getCommandManager();
    }

    public void run() {
        bot.startBot();
    }

    public IcqHttpApi getApi() {
        return bot.getAccountManager().getNonAccountSpecifiedApi();
    }
}
