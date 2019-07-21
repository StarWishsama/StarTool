package io.github.starwishsama.StarTool;

import io.github.starwishsama.StarTool.Commands.*;
import io.github.starwishsama.StarTool.Files.*;
import io.github.starwishsama.StarTool.Listeners.*;

import io.github.starwishsama.StarTool.Utils.Utils;

import io.github.starwishsama.StarTool.Utils.VaultSupport;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class StarToolMain extends JavaPlugin {
    private static StarToolMain instance;
    public static boolean isVault = false;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("[/] 正在载入配置与语言文件...");
        Config.loadCfg();
        Config.loadPlayerData();
        Lang.loadLang();
        BlockData.loadCfg();

        getLogger().info("[/] 正在注册监听器与命令...");
        registerEvents();
        registerCommands();

        if (VaultSupport.setupEconomy()){
            getLogger().info("[√] 已接入 Vault!");
            isVault = true;
        } else
            getLogger().warning("[X] 无法接入 Vault, 你将会失去某些功能!");

        getLogger().info("[√] 欢迎使用 StarTool, 版本 " + getDescription().getVersion());
        UpdateChecker.CheckUpdate();
    }

    public void onDisable() {
        RestartCommand.timeToRestart = -1L;
        getLogger().info("[/] 正在禁用 StarTool...");
        getLogger().info("[/] 正在取消所有运行中的任务...");
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("[/] 正在保存配置文件...");
        Config.saveCfg();
        Config.savePlayerData();
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
        if (Config.antiExplode) Bukkit.getPluginManager().registerEvents(new AntiExplodeListener(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(Bukkit.getPluginCommand("startool")).setExecutor(new StarToolCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("startool")).setTabCompleter(new StarToolCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("at")).setExecutor(new AtCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("at")).setTabCompleter(new AtCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("sgc")).setExecutor(new GcCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("sgc")).setTabCompleter(new GcCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("srestart")).setExecutor(new RestartCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("srestart")).setTabCompleter(new RestartCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("dustbin")).setExecutor(new DustBinCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("pay")).setExecutor(new PayCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("pay")).setTabCompleter(new PayCommand());
    }

    public static StarToolMain getInstance() {
        return instance;
    }

    public static void doRestart(){
        new BukkitRunnable() {
            public void run() {
                    long ttr = RestartCommand.timeToRestart;
                    while (ttr >= new Date().getTime()) {
                        if ((ttr - new Date().getTime()) / 1000 > 0) {
                            try {
                                if ((ttr - new Date().getTime()) / 1000 == 90) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "90")));
                                    TimeUnit.SECONDS.sleep(30L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 60) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "60")));
                                    TimeUnit.SECONDS.sleep(30L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 30) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "30")));
                                    TimeUnit.SECONDS.sleep(15L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 15) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "15")));
                                    TimeUnit.SECONDS.sleep(5L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 10) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "10")));
                                    TimeUnit.SECONDS.sleep(5L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 5) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "5")));
                                    TimeUnit.SECONDS.sleep(1L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 4) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "4")));
                                    TimeUnit.SECONDS.sleep(1L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 3) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "3")));
                                    TimeUnit.SECONDS.sleep(1L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 2) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "2")));
                                    TimeUnit.SECONDS.sleep(1L);
                                } else if ((ttr - new Date().getTime()) / 1000 == 1) {
                                    Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + Lang.restartMsg.replaceAll("%s", "1")));
                                    TimeUnit.SECONDS.sleep(1L);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (ttr == -1L){
                            if (!isCancelled()) {
                                this.cancel();
                                Thread.yield();
                                break;
                            }
                        } else {
                            Bukkit.broadcastMessage(Utils.color(Lang.pluginPrefix + "&c服务器正在重启!"));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
                            this.cancel();
                            Thread.yield();
                        }
                    }
            }
        }.runTask(instance);
    }
}