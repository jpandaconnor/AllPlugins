package co.uk.randompanda30.party;

import co.uk.randompanda30.party.command.handle.CommandHandler;
import co.uk.randompanda30.party.events.OnEntityDamageByEntityEvent;
import co.uk.randompanda30.party.events.OnEntityDeathEvent;
import co.uk.randompanda30.party.events.OnPlayerJoinEvent;
import co.uk.randompanda30.party.events.OnPlayerQuitEvent;
import co.uk.randompanda30.party.string.Dispatch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Party extends JavaPlugin {

    static Party plugin;
    static CommandHandler commandHandler;

    public PluginDescriptionFile pdfFile;

    @Override
    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();

        commandHandler = new CommandHandler(this);

        new OnEntityDamageByEntityEvent();
        new OnEntityDeathEvent();
        new OnPlayerJoinEvent();
        new OnPlayerQuitEvent();

        new Dispatch.Start();
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

    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static Party getPlugin() {
        return plugin;
    }
}