package co.uk.randompanda30.fileguilds.commands.handle;

import co.uk.randompanda30.fileguilds.Guilds;
import org.bukkit.command.CommandSender;

public abstract class Command {

    private String help;
    private String permission;
    private String usage;

    private boolean console;

    private Guilds plugin;

    protected Command(String usage, String permission, String help, boolean console) {
        plugin = Guilds.getPlugin();

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