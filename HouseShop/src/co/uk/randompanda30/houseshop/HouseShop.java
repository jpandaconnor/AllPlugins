package co.uk.randompanda30.houseshop;

import co.uk.randompanda30.houseshop.command.handle.CommandHandler;
import co.uk.randompanda30.houseshop.config.Config;
import co.uk.randompanda30.houseshop.config.Data;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.config.Request;
import co.uk.randompanda30.houseshop.events.handle.EventHandler;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.task.LocationTask;
import co.uk.randompanda30.houseshop.timer.Checker;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class HouseShop extends JavaPlugin {

    /*
    Patch notes #1
    Adding new .build value to any existing houses
     */

    static HouseShop plugin;
    static CommandHandler commandHandler;
    static EventHandler eventHandler;

    static BukkitTask locationTask;

    static Economy economy;

    static ArrayList<BlockFace> faces = new ArrayList<BlockFace>() {
        {
            add(BlockFace.NORTH);
            add(BlockFace.EAST);
            add(BlockFace.SOUTH);
            add(BlockFace.WEST);

            add(BlockFace.DOWN);
            add(BlockFace.UP);

            // add(BlockFace.NORTH_EAST);
            // add(BlockFace.NORTH_WEST);
            // add(BlockFace.SOUTH_EAST);
            // add(BlockFace.SOUTH_WEST);
        }
    };

    public PluginDescriptionFile pdfFile;
    BukkitTask checker;

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static HouseShop getPlugin() {
        return plugin;
    }

    public static Economy getEconomy() {
        return economy;
    }

    @Override
    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();

        commandHandler = new CommandHandler(this);
        eventHandler = new EventHandler(this);

        new Config(this);
        new Messages(this);
        new Data(this);
        new Request(this);

        if (!setupEconomy()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_NOVAULT.value, null);
            plugin.setEnabled(false);
        }

        checker = new Checker(this).runTaskTimerAsynchronously(this, 0, 10 * 60 * 20);
        locationTask = new LocationTask(this).runTaskTimerAsynchronously(this, 0, 20L);

        TEMP.datac.getConfigurationSection("").getKeys(false).stream().filter(s ->
                TEMP.datac.get(s + ".blocks") != null).forEach(s -> {
            TEMP.blocksPlaced.put(s, (ArrayList<String>) TEMP.datac.get(s + ".blocks"));
        });

        new Dispatch.Start();
    }

    @Override
    public void onDisable() {
        new Dispatch.Stop();

        // Check if these are acutally owrking when you can
        checker.cancel();
        locationTask.cancel();

        Iterator it = TEMP.blocksPlaced.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            TEMP.datac.set(pair.getKey() + ".blocks", pair.getValue());
        }

        Data.save();

        plugin = null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.handleCommand(sender, command, label, args);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        economy = rsp.getProvider();
        return economy != null;
    }

    public static WorldGuardPlugin getWorldGuard() {
        Plugin p = HouseShop.getPlugin().getServer().getPluginManager().getPlugin("WorldGuard");
        if(p == null || !(p instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) p;
    }
}