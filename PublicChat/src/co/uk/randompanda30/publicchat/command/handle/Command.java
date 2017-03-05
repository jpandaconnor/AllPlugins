package co.uk.randompanda30.publicchat.command.handle;

import co.uk.randompanda30.publicchat.PublicChat;
import org.bukkit.command.CommandSender;

public abstract class Command {

    private String help;
    private String permission;
    private String usage;

    private boolean console;

    private PublicChat plugin;

    protected Command(String usage, String permission, String help, boolean console) {
        plugin = PublicChat.getPlugin();

        this.usage = usage;
        this.permission = permission;
        this.help = help;

        this.console = console;
    }

    public String getUsage() {
        return usage;
    }

    public String getPermission() {
        return permission;
    }

    public String getHelp() {
        return help;
    }

    protected boolean isConsoleAllowed() {
        return console;
    }

    public abstract boolean runCommand(CommandSender sender, String[] args);
}