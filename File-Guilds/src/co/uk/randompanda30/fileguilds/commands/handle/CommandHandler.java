package co.uk.randompanda30.fileguilds.commands.handle;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.commands.*;
import co.uk.randompanda30.fileguilds.commands.nohandle.CommandAChat;
import co.uk.randompanda30.fileguilds.commands.nohandle.CommandAlChat;
import co.uk.randompanda30.fileguilds.commands.nohandle.CommandGChat;
import co.uk.randompanda30.fileguilds.commands.plot.CommandPlotCreate;
import co.uk.randompanda30.fileguilds.commands.plot.CommandPlotDelete;
import co.uk.randompanda30.fileguilds.commands.plot.CommandPlotHome;
import co.uk.randompanda30.fileguilds.commands.plot.CommandPlotSpawn;
import co.uk.randompanda30.fileguilds.commands.ranks.*;
import co.uk.randompanda30.fileguilds.commands.war.CommandWarDeclare;
import co.uk.randompanda30.fileguilds.commands.war.CommandWarSurrender;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import co.uk.randompanda30.fileguilds.string.Format;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    Guilds plugin;

    public CommandHandler(Guilds plugin) {
        this.plugin = plugin;

        new CommandGChat(plugin);
        new CommandAChat(plugin);
        new CommandAlChat(plugin);
    }

    private Command getCommandMatch(org.bukkit.command.Command command, String[] args) {
        if (command.getName().equals("guild")) {
            if (args.length == 0) return new CommandHelp();

            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("c"))
                    return new CommandCreate();
                if (args[0].equalsIgnoreCase("kick") || args[0].equalsIgnoreCase("k"))
                    return new CommandKick();
                if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("i"))
                    return new CommandInvite();
                if (args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("a"))
                    return new CommandAccept();
                if (args[0].equalsIgnoreCase("deny") || args[0].equalsIgnoreCase("d"))
                    return new CommandDeny();

                if (args[0].equalsIgnoreCase("ally") || args[0].equalsIgnoreCase("al"))
                    return new CommandGInvite();
                if (args[0].equalsIgnoreCase("unally") || args[0].equalsIgnoreCase("ual"))
                    return new CommandUnally();

                if (args[0].equalsIgnoreCase("gaccept") || args[0].equalsIgnoreCase("ga"))
                    return new CommandGAccept();
                if (args[0].equalsIgnoreCase("gdeny") || args[0].equalsIgnoreCase("gd"))
                    return new CommandGDeny();

                if ((args[0].equalsIgnoreCase("rank") && args[1].equalsIgnoreCase("create")) ||
                        args[0].equalsIgnoreCase("rc"))
                    return new CommandRankCreate();
                if ((args[0].equalsIgnoreCase("rank") && args[1].equalsIgnoreCase("delete")) ||
                        args[0].equalsIgnoreCase("rd"))
                    return new CommandRankDelete();
                if ((args[0].equalsIgnoreCase("rank") && args[1].equalsIgnoreCase("set")) ||
                        args[0].equalsIgnoreCase("rs"))
                    return new CommandRankSet();
                if ((args[0].equalsIgnoreCase("rank") && args[1].equalsIgnoreCase("title")) ||
                        args[0].equalsIgnoreCase("rt"))
                    return new CommandRankTitle();
                if ((args[0].equalsIgnoreCase("rank") && args[1].equalsIgnoreCase("list")) ||
                        args[0].equalsIgnoreCase("rl"))
                    return new CommandRankList();
                if ((args[0].equalsIgnoreCase("rank") && args[1].equalsIgnoreCase("perms")) ||
                        args[0].equalsIgnoreCase("rp"))
                    return new CommandRankPerms();

                if ((args[0].equalsIgnoreCase("war") && args[1].equalsIgnoreCase("declare")) ||
                        args[0].equalsIgnoreCase("wd"))
                    return new CommandWarDeclare();
                if ((args[0].equalsIgnoreCase("war") && args[1].equalsIgnoreCase("surrender")) ||
                        args[0].equalsIgnoreCase("ws"))
                    return new CommandWarSurrender();

                if ((args[0].equalsIgnoreCase("plot") && args[1].equalsIgnoreCase("create")) ||
                        args[0].equalsIgnoreCase("pc"))
                    return new CommandPlotCreate();
                if ((args[0].equalsIgnoreCase("plot") && args[1].equalsIgnoreCase("delete")) ||
                        args[0].equalsIgnoreCase("pd"))
                    return new CommandPlotDelete();
                if ((args[0].equalsIgnoreCase("plot") && args[1].equalsIgnoreCase("home")) ||
                        args[0].equalsIgnoreCase("ph"))
                    return new CommandPlotHome();
                if ((args[0].equalsIgnoreCase("plot") && args[1].equalsIgnoreCase("spawn")) ||
                        args[0].equalsIgnoreCase("ps"))
                    return new CommandPlotSpawn();

                // if (args[0].equalsIgnoreCase("pvp"))
                   // return new CommandGuildPVP();
                if (args[0].equalsIgnoreCase("motd"))
                    return new CommandMOTD();
                if (args[0].equalsIgnoreCase("transowner"))
                    return new CommandTO();

                if(args[0].equalsIgnoreCase("disband")) {
                    return new CommandDisband();
                }

                if (args[0].equalsIgnoreCase("info")) {
                    return new CommandInfo();
                }

                if(args[0].equalsIgnoreCase("list")) {
                    return new CommandList();
                }

                if(args[0].equalsIgnoreCase("lookup")) {
                    return new CommandLookup();
                }

                if (args[0].equalsIgnoreCase("leave")) {
                    return new CommandLeave();
                }

                if (args[0].equalsIgnoreCase("port")) {

                    // return new CommandPort();
                }

                if (args[0].equalsIgnoreCase("help"))
                    return new CommandHelp();
            }
        }
        return null;
    }

    public List<Command> getCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new CommandHelp());
        commands.add(new CommandLookup());
        commands.add(new CommandList());
        commands.add(new CommandCreate());
        commands.add(new CommandKick());
        commands.add(new CommandLeave());
        commands.add(new CommandDisband());
        commands.add(new CommandInvite());
        commands.add(new CommandAccept());
        commands.add(new CommandDeny());
        commands.add(new CommandGInvite());
        commands.add(new CommandUnally());
        commands.add(new CommandGAccept());
        commands.add(new CommandGDeny());
        commands.add(new CommandRankCreate());
        commands.add(new CommandRankDelete());
        commands.add(new CommandRankSet());
        commands.add(new CommandRankTitle());
        commands.add(new CommandRankList());
        commands.add(new CommandRankPerms());
        commands.add(new CommandWarDeclare());
        commands.add(new CommandWarSurrender());
        commands.add(new CommandPlotCreate());
        commands.add(new CommandPlotDelete());
        commands.add(new CommandPlotHome());
        commands.add(new CommandPlotSpawn());
        commands.add(new CommandGuildPVP());
        commands.add(new CommandMOTD());
        commands.add(new CommandTO());
        commands.add(new CommandInfo());
        return commands;
    }

    public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Command command = getCommandMatch(cmd, args);

        if (command == null) return false;

        if (!command.isConsoleAllowed() && !(sender instanceof Player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTFORCONSOLE.value, null);
            return true;
        }

        if (!command.getPermission().equals("") && !sender.hasPermission(command.getPermission())) {
            sender.sendMessage(Format.format(Messages.MessagesValues.GUILDS_NOPERM.value.toString().
                    replace("%perm", command.getPermission())));
            return true;
        }

        command.runCommand(sender, args);
        return true;
    }
}