package org.jpanda.cityrp.setup;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jpanda.cityrp.CityRP;
import org.jpanda.cityrp.config.Messages;
import org.jpanda.cityrp.string.Dispatch;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class DatabaseInstall implements Listener, CommandExecutor {

    CityRP plugin;
    String ip;
    String dbname;
    String dbuser;
    String epass;
    int dbport;
    boolean confirmed;
    private Steps step;

    public DatabaseInstall(CityRP plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("jpanda").setExecutor(this);

        start();
    }

    @SuppressWarnings("unchecked")
    public void start() {
        step = Steps.DBIP;

        for (String s : (List<String>) Messages.MessagesValues.SETUP_DATABASE_DETAILS.value) {
            Dispatch.sendMessage(s, null);
        }

        Bukkit.getLogger().setLevel(Level.OFF);

        Dispatch.sendMessage((String) Messages.MessagesValues.SETUP_DATABASE_STEP1.value,
                null);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {
        return true;
    }

    public boolean isLoggable(LogRecord line) {
        return !(line.getMessage().contains("INFO"));
    }

    enum Steps {
        DBIP, DBNAME, DBUSER, DBPASS, DBPORT, CONFIRMED;
    }
}