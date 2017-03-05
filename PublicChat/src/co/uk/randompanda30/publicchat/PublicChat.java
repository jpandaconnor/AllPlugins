package co.uk.randompanda30.publicchat;

import co.uk.randompanda30.publicchat.command.handle.CommandHandler;
import co.uk.randompanda30.publicchat.config.Config;
import co.uk.randompanda30.publicchat.config.Messages;
import co.uk.randompanda30.publicchat.events.OnAsyncPlayerChatEvent;
import co.uk.randompanda30.publicchat.string.Dispatch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PublicChat extends JavaPlugin {

    static PublicChat plugin;
    static CommandHandler commandHandler;

    public PluginDescriptionFile pdfFile;

    public static PublicChat getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();

        new Config(this);
        new Messages(this);

        commandHandler = new CommandHandler(this);

        new OnAsyncPlayerChatEvent();
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
}