package top.starwish.startool;

import java.util.logging.Logger;
import java.io.File;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import top.starwish.startool.listener.*;
import top.starwish.startool.commands.*;

public class StarToolMain extends JavaPlugin {
    String prefix = getConfig().getString("PluginPrefix");
    String version = getConfig().getString("Version");
    int LabaPrice = getConfig().getInt("LabaPrice");
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    //Vault
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

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

    @Override
    public void onEnable() {
        //�����ļ�
        getLogger().info("���ڼ����ļ�...");
        File config = new File(getDataFolder(), "config.yml");
        FileConfiguration configchange = YamlConfiguration
                .loadConfiguration(config);
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
            reloadConfig();
        }
        getLogger().info("�ļ��������.");

        //���� Vault
        getLogger().info("���� Hook Vault...");
        if (!setupEconomy()) {
            getLogger().warning("������δ��װ Vault! ���ڽ���...");
            setEnabled(false);
        }
        setupPermissions();
        setupChat();

        //ע�������
        getLogger().info("���ڼ��ؼ�����...");
        Bukkit.getPluginManager().registerEvents(new LevelChatPrefix(), this);
        Bukkit.getPluginManager().registerEvents(new LevelUpTips(), this);
        Bukkit.getPluginManager().registerEvents(new ChatSendMyPos(), this);
        getLogger().info("�������Ѽ���.");

        //ע������
        getLogger().info("����ע������...");
        Bukkit.getPluginCommand("biu").setExecutor(new BiuCommand());
        //Bukkit.getPluginCommand("laba").setExecutor(new LabaCommand());
        getLogger().info("������ע��.");

        //���������ʾ
        getLogger().info("��b" + prefix + " > ���سɹ�.");
        getLogger().info("��b" + prefix + " > ��f��ӭʹ�� StarTool, �汾 " + version + ", ���� StarWish");
        getLogger().info("��b" + prefix + " > ��f��л����ʹ��!");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        getServer().getConsoleSender().sendMessage("��b" + prefix + " > ��7���ڹرղ��...");
        saveDefaultConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("laba")) {
            {
                if (sender instanceof Player) {
                    if (sender.hasPermission("startool.laba")) {
                        if (args.length == 0) {
                            sender.sendMessage("��bStarTool > ��eʹ�÷���: /laba <����>");
                            return true;
                        }

                        if (args.length == 1) {
                            EconomyResponse s = econ.bankWithdraw(sender.getName(), LabaPrice);
                            if (s.transactionSuccess()) {
                                Bukkit.broadcastMessage("��b��� ��e" + sender.getName() + " ��b������һ��С������Ϣ!");
                                Bukkit.broadcastMessage("��d��l" + sender.getName() + " ��e��l˵: " + args[0]);
                                return true;
                            } else {
                                sender.sendMessage("��b" + prefix + " ��c> ��û���㹻�Ľ�Ǯ!");
                            }
                        }
                    } else {
                        sender.sendMessage("��b" + prefix + " ��c> ��û��Ȩ��!");
                    }
                }
            }

            if (cmd.getName().equalsIgnoreCase("startool")) {
                if (args.length == 0) {
                    sender.sendMessage("��bStarTool " + version + ", By StarWish_");
                    sender.sendMessage("��f");
                    sender.sendMessage("/laba(/lb) [����] ����ȫ������");
                    sender.sendMessage("/biu <���> ��һ���������ȥ��");
                    sender.sendMessage("/stool uuid ��ȡ���UUID");
                    sender.sendMessage("/stool clear <���> ����ȫ��/��ҵ�������");
                    sender.sendMessage("/stool version ��ʾ���Ŀǰ�汾");
                    sender.sendMessage("/stool reload ���ز������");
                    sender.sendMessage("��f");
                    return true;
                }

                if (args[0].equalsIgnoreCase("uuid")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        if (!(sender.hasPermission("startool.uuid"))) {
                            sender.sendMessage("��b" + prefix + " ��b> ��c���ڻ�ȡ����UUID...");
                            sender.sendMessage("��b" + prefix + " ��b> ��e���UUIDΪ:" + p.getUniqueId());
                            return true;
                        } else {
                            sender.sendMessage("��b" + prefix + " ��b> ��c��û��Ȩ����ִ����������!");
                            return true;
                        }
                    } else {
                        sender.sendMessage("��b" + prefix + " ��b> ��c�������һ�����!");
                    }
                }

                if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage("��b" + prefix + " ��b> ��eĿǰ�汾Ϊ: ��a��n" + version);
                    return true;
                }

                if (args[0].equalsIgnoreCase("clear")) {
                    if ((sender instanceof Player)) {
                        if (sender.hasPermission("startool.clearscreen")) {
                            if (args.length == 1) {
                                for (int i = 0; i <= 60; i++)
                                    Bukkit.broadcastMessage("       ");
                                sender.sendMessage("��b" + prefix + " ��b> ��eȫ�������ɹ�~");
                                return true;
                            }
                            if (sender.hasPermission("startool.clearscreen.other")) {
                                Player otherp = Bukkit.getPlayer(args[1]);
                                if (args.length == 2) {
                                    if (otherp == null) {
                                        sender.sendMessage("��b" + prefix + " ��b> ��c������ִ���򲻴���!");
                                        return true;
                                    }
                                    for (int i = 0; i <= 60; i++);
                                        otherp.sendMessage("        ");
                                    otherp.sendMessage("��b" + prefix + " ��b> ��e���ѱ�����Ա����!");
                                        sender.sendMessage("��b" + prefix + " ��b> ��e�ɹ�Ϊ ��c" + otherp.getName() + " ��e����!");
                                        return true;
                                    }
                                } else {
                                    sender.sendMessage("��b" + prefix + " ��b> ��c������ִ���򲻴���!");
                                    return true;
                                }
                            }
                        } else {
                            sender.sendMessage("��b" + prefix + " ��b> ��c��û��Ȩ����ִ����������!");
                            return true;
                        }
                    } else {
                        sender.sendMessage("��b" + prefix + " ��b> ��e���������Ϸ��ʹ�ø�����!");
                        return true;
                    }
                }

                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender instanceof ConsoleCommandSender) {
                        if (sender.hasPermission("startool.reload")) {
                            reloadConfig();
                            saveConfig();
                            sender.sendMessage("��b" + prefix + " ��b> ��e�������.");
                            return true;
                        } else {
                            sender.sendMessage("��b" + prefix + " ��b> ��c��û��Ȩ����ִ����������!");
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

        public static Permission getPermissions() {
            return perms;
        }
    }