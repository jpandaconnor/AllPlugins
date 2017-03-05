package co.uk.randompanda30.backup;

import co.uk.randompanda30.backup.commands.handle.CommandHandler;
import co.uk.randompanda30.backup.config.Config;
import co.uk.randompanda30.backup.config.Messages;
import co.uk.randompanda30.backup.string.Dispatch;
import co.uk.randompanda30.backup.util.BackupUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Backup extends JavaPlugin {

    static Backup plugin;
    static CommandHandler commandHandler;

    public PluginDescriptionFile pdfFile;

    @Override
    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();

        new Config(this);
        new Messages(this);

        commandHandler = new CommandHandler(this);

        new BackupUtil(this);

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

    public static Backup getPlugin() {
        return plugin;
    }
}