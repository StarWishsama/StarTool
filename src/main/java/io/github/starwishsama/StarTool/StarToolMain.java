package io.github.starwishsama.StarTool;

import io.github.starwishsama.StarTool.Commands.*;
import io.github.starwishsama.StarTool.Files.*;
import io.github.starwishsama.StarTool.Listeners.*;

import io.github.starwishsama.StarTool.Utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;
import java.util.Objects;

public class StarToolMain extends JavaPlugin {
    private static StarToolMain instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("[/] 正在载入配置与语言文件...");
        Config.loadCfg();
        Lang.loadLang();
        BlockData.loadCfg();

        getLogger().info("[/] 正在注册监听器与命令...");
        registerEvents();
        registerCommands();

        getLogger().info("[/] 正在检查是否安装了 ProtocolLib...");
        if (Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ProtocolLib")).isEnabled()) {
            getLogger().info("[√] 已检测到 ProtocolLib!");
        } else
            getLogger().info("[X] 服务器未安装 ProtocolLib, 你将会失去某些功能!");

        getLogger().info("[√] 欢迎使用 StarTool, 版本 " + getDescription().getVersion());
        UpdateChecker.CheckUpdate();
    }

    public void onDisable() {
        RestartCommand.timeToRestart = 0L;
        getLogger().info("[/] 正在禁用 StarTool...");
        getLogger().info("[/] 正在取消所有运行中的任务...");
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("[/] 正在保存配置文件...");
        Config.saveCfg();
        Lang.saveLang();
        BlockData.saveCfg();
        getLogger().info("[√] 再见!");
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new BedEnterListener(), this);
        Bukkit.getPluginManager().registerEvents(new ColorSignListener(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTips(), this);
        Bukkit.getPluginManager().registerEvents(new CampFireListener(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(Bukkit.getPluginCommand("startool")).setExecutor(new StarToolCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("startool")).setTabCompleter(new StarToolCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("at")).setExecutor(new AtCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("at")).setTabCompleter(new AtCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("sgc")).setExecutor(new GcCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("sgc")).setTabCompleter(new GcCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("srestart")).setExecutor(new RestartCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("dustbin")).setExecutor(new DustBinCommand());
    }

    public static StarToolMain getInstance() {
        return instance;
    }

    public static void doRestart(){
        new BukkitRunnable(){
            public void run(){
                long ttr = RestartCommand.timeToRestart;
                while (ttr >= new Date().getTime()){
                    if ((ttr - new Date().getTime()) / 1000 > 0) {
                        try {
                            if ((ttr - new Date().getTime()) / 1000 == 90){
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 90 秒后重启!"));
                                Thread.sleep(30 * 1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 60) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 60 秒后重启!"));
                                Thread.sleep(30 * 1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 30) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 30 秒后重启!"));
                                Thread.sleep(15 * 1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 15) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 15 秒后重启!"));
                                Thread.sleep(5 * 1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 10) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 10 秒后重启!"));
                                Thread.sleep(5 * 1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 5) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 5 秒后重启!"));
                                Thread.sleep(1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 4) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 4 秒后重启!"));
                                Thread.sleep(1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 3) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 3 秒后重启!"));
                                Thread.sleep(1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 2) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 2 秒后重启!"));
                                Thread.sleep(1000L);
                            } else if ((ttr - new Date().getTime()) / 1000 == 1) {
                                Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器将在 1 秒后重启!"));
                                Thread.sleep(1000L);
                            }
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    } else {
                        Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器正在重启!"));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
                        break;
                    }
                }
            }
        }.runTask(StarToolMain.getInstance());
    }
}