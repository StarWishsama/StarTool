package top.starwish.startool;

import java.io.*;
import java.net.URL;
import java.util.logging.Logger;

import net.milkbowl.vault.Vault;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.milkbowl.vault.economy.EconomyResponse;

import top.starwish.startool.listener.*;
import top.starwish.startool.commands.*;

public class StarToolMain extends JavaPlugin {
    String prefix = getConfig().getString("PluginPrefix");
    String version = getConfig().getString("Version");
    int LabaPrice = getConfig().getInt("LabaPrice");

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    //Vault
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public String getLatestVersion(){
        String ver =null;
        try {
            URL url = new URL("http://raw.githubusercontent.com/StarWishsama/StarTool/master/UpdateCheck.txt");
            InputStream is =url.openStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
            ver =br.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ver;
    }

    public boolean isLatestVersion() {
        boolean isLatest = false;
        String latest = getLatestVersion();
        String current = getDescription().getVersion();
        if (latest.equalsIgnoreCase(current)) {
            isLatest = true;
        }
        return isLatest;
    }

    public void Checkupdate(){
        new BukkitRunnable(){
            public void run() {
                if(isLatestVersion()){
                    getLogger().info("您当前正在使用最新版本!");
                }
                else {
                    getLogger().info("最新版本已经发布!");
                }
            }
        }.runTaskAsynchronously(this);
    }

    @Override
    public void onEnable() {
        //加载文件
        getLogger().info("正在加载文件...");
        File config = new File(getDataFolder(), "config.yml");
        FileConfiguration configchange = YamlConfiguration
                .loadConfiguration(config);
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
            reloadConfig();
        }
        getLogger().info("文件载入完成.");

        //接入 Vault
        getLogger().info("正在检查服务器是否安装 Vault...");
        if (setupEconomy()) {
            getLogger().info("已发现 Vault 插件!");
        }
        if (!Bukkit.getServer().getPluginManager().getPlugin("Vault").isEnabled()){
            getLogger().warning("未发现服务器安装了 Vault, 插件即将禁用!");
            this.setEnabled(false);
        }

        //注册监听器
        getLogger().info("正在加载监听器...");
        Bukkit.getPluginManager().registerEvents(new LevelChatPrefix(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTips(), this);
        Bukkit.getPluginManager().registerEvents(new ChatSendMyPos(), this);
        getLogger().info("监听器已加载.");

        //注册命令
        getLogger().info("正在注册命令...");
        Bukkit.getPluginCommand("biu").setExecutor(new BiuCommand());
        getLogger().info("命令已注册.");

        //载入完成提示
        getLogger().info(prefix + " > 加载成功.");
        getLogger().info(prefix + " > 欢迎使用 StarTool, 版本 " + version + ", 作者 StarWish");
        getLogger().info( prefix + " > 感谢您的使用!");

        //更新检查, WIP
        //Checkupdate();
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        getServer().getConsoleSender().sendMessage("§b" + prefix + " > §7正在关闭插件...");
        saveDefaultConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("laba")) {
            {
                if (sender instanceof Player) {
                    if (sender.hasPermission("startool.laba")) {
                        if (args.length == 0) {
                            sender.sendMessage("§bStarTool > §e使用方法: /laba <内容>");
                            return true;
                        }

                        if (args.length == 1) {
                            EconomyResponse s = econ.bankWithdraw(sender.getName(), LabaPrice);
                            if (s.transactionSuccess()) {
                                Bukkit.broadcastMessage("§b玩家 §e" + sender.getName() + " §b发送了一条小喇叭信息!");
                                Bukkit.broadcastMessage("§d§l" + sender.getName() + " §e§l说: " + args[0]);
                                return true;
                            } else {
                                sender.sendMessage("§b" + prefix + " §c> 你没有足够的金钱!");
                            }
                        }
                    } else {
                        sender.sendMessage("§b" + prefix + " §c> 你没有权限!");
                    }
                }
            }

            if (cmd.getName().equalsIgnoreCase("startool")) {
                if (args.length == 0) {
                    sender.sendMessage("§bStarTool " + version + ", By StarWish_");
                    sender.sendMessage("§f");
                    sender.sendMessage("/laba(/lb) [内容] 发送全服公告");
                    sender.sendMessage("/biu <玩家> 让一个玩家立即去世");
                    sender.sendMessage("/stool uuid 获取你的UUID");
                    sender.sendMessage("/stool clear <玩家> 清屏全服/玩家的聊天栏");
                    sender.sendMessage("/stool version 显示插件目前版本");
                    sender.sendMessage("/stool reload 重载插件配置");
                    sender.sendMessage("§f");
                    return true;
                }

                if (args[0].equalsIgnoreCase("uuid")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        if (!(sender.hasPermission("startool.uuid"))) {
                            sender.sendMessage("§b" + prefix + " §b> §c正在获取您的UUID...");
                            sender.sendMessage("§b" + prefix + " §b> §e你的UUID为:" + p.getUniqueId());
                            return true;
                        } else {
                            sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
                            return true;
                        }
                    } else {
                        sender.sendMessage("§b" + prefix + " §b> §c你必须是一个玩家!");
                    }
                }

                if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage("§b" + prefix + " §b> §e目前版本为: §a§n" + version);
                    return true;
                }

                if (args[0].equalsIgnoreCase("clear")) {
                    if ((sender instanceof Player)) {
                        if (sender.hasPermission("startool.clearscreen")) {
                            if (args.length == 1) {
                                for (int i = 0; i <= 60; i++)
                                    Bukkit.broadcastMessage("       ");
                                sender.sendMessage("§b" + prefix + " §b> §e全服清屏成功~");
                                return true;
                            }
                            if (sender.hasPermission("startool.clearscreen.other")) {
                                Player otherp = Bukkit.getPlayer(args[1]);
                                if (args.length == 2) {
                                    if (otherp == null) {
                                        sender.sendMessage("§b" + prefix + " §b> §c玩家名字错误或不存在!");
                                        return true;
                                    }
                                    for (int i = 0; i <= 60; i++);
                                        otherp.sendMessage("        ");
                                    otherp.sendMessage("§b" + prefix + " §b> §e您已被管理员清屏!");
                                        sender.sendMessage("§b" + prefix + " §b> §e成功为 §c" + otherp.getName() + " §e清屏!");
                                        return true;
                                    }
                                } else {
                                    sender.sendMessage("§b" + prefix + " §b> §c玩家名字错误或不存在!");
                                    return true;
                                }
                            }
                        } else {
                            sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
                            return true;
                        }
                    } else {
                        sender.sendMessage("§b" + prefix + " §b> §e你必须在游戏内使用该命令!");
                        return true;
                    }
                }

                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender instanceof ConsoleCommandSender) {
                        if (sender.hasPermission("startool.reload")) {
                            reloadConfig();
                            saveConfig();
                            sender.sendMessage("§b" + prefix + " §b> §e重载完成.");
                            return true;
                        } else {
                            sender.sendMessage("§b" + prefix + " §b> §c你没有权限来执行这条命令!");
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    public static Economy getEconomy() {
        return econ;
    }
}
