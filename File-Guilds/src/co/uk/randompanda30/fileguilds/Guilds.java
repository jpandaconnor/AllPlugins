package co.uk.randompanda30.fileguilds;

import co.uk.randompanda30.fileguilds.commands.handle.CommandHandler;
import co.uk.randompanda30.fileguilds.config.Config;
import co.uk.randompanda30.fileguilds.config.Data;
import co.uk.randompanda30.fileguilds.config.Guild;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.events.*;
import co.uk.randompanda30.fileguilds.plot.PlotsGenerator;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Guilds extends JavaPlugin {

    static Guilds plugin;
    static Economy economy;
    static CommandHandler commandHandler;

    static boolean file;

    public PluginDescriptionFile descriptionFile;

    public static Guilds getPlugin() {
        return plugin;
    }

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static boolean isUsingFile() {
        return file;
    }

    @Override
    public void onEnable() {
        plugin = this;
        initBasic();

        new Config(this);
        new Messages(this);
        new Data(this);
        new Guild(this);

        commandHandler = new CommandHandler(this);

        new OnInventoryClickEvent();
        new OnPlayerQuitEvent();
        new OnEntityDamageByEntityEvent();
        new OnBlockPlaceEvent();
        new OnBlockBreakEvent();
        new OnPlayerJoinEvent();
        new OnAsyncPlayerChatEvent();

        new Dispatch.Start();

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                doConfig();
            }
        }, 200L);
    }

    @Override
    public void onDisable() {
        new Dispatch.Stop();

        plugin = null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.handleCommand(sender, command, label, args);
    }

    private void initBasic() {
        descriptionFile = this.getDescription();

        initEconomy();
    }

    private boolean initEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer()
                .getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        economy = rsp.getProvider();
        return economy != null;
    }

    public void doConfig() {

    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new PlotsGenerator();
    }
}