package io.github.starwishsama.StarTool.Bot.BotCommands;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

import java.util.ArrayList;

public class VersionCommand implements EverywhereCommand {
    @Override
    public CommandProperties properties(){
        return new CommandProperties("version", "v", "版本");
    }

    @Override
    public String run(EventMessage e, User sender, String cmd, ArrayList<String> args){
        return "Bot > Nameless-Bot 版本号: 0.0.1-DEV";
    }
}
