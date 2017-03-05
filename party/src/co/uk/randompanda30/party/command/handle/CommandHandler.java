package co.uk.randompanda30.party.command.handle;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.command.*;
import co.uk.randompanda30.party.command.nohandle.CommandPChat;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.string.Dispatch;
import co.uk.randompanda30.party.string.Format;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 15/04/16.
 */
public class CommandHandler {

    Party plugin;

    public CommandHandler(Party plugin) {
        this.plugin = plugin;
        new CommandPChat(plugin);
    }

    private Command getCommandMatch(org.bukkit.command.Command command, String[] args) {
        if (command.getName().equals("party")) {
            if (args.length == 0) return null;

            if (args.length >= 1) {
                if(args[0].equalsIgnoreCase("create"))
                    return new CommandCreate();
                if(args[0].equalsIgnoreCase("leave"))
                    return new CommandLeave();
                if(args[0].equalsIgnoreCase("list"))
                    return new CommandList();
                if(args[0].equalsIgnoreCase("invite"))
                    return new CommandInvite();
                if(args[0].equalsIgnoreCase("accept"))
                    return new CommandAccept();
                if(args[0].equalsIgnoreCase("deny"))
                    return new CommandDeny();
                if(args[0].equalsIgnoreCase("help"))
                    return new CommandHelp();
                if (args[0].equalsIgnoreCase("regroup"))
                    return new CommandRegroup();
                if(args[0].equalsIgnoreCase("kick")) {
                    return new CommandKick();
                }
            }
        }
        return null;
    }

    public List<Command> getCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new CommandHelp());
        commands.add(new CommandCreate());
        commands.add(new CommandLeave());
        commands.add(new CommandKick());
        commands.add(new CommandList());
        commands.add(new CommandInfo());
        commands.add(new CommandInvite());
        return commands;
    }

    public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Command command = getCommandMatch(cmd, args);

        if (command == null) return false;

        if (!command.isConsoleAllowed() && !(sender instanceof Player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_NOTFORCONSOLE.value, null);
            return true;
        }

        if (!command.getPermission().equals("") && !sender.hasPermission(command.getPermission())) {
            sender.sendMessage(Format.format(Messages.MessagesValues.PARTY_NOPERM.value.toString().
                    replace("%perm", command.getPermission())));
            return true;
        }

        command.runCommand(sender, args);
        return true;
    }
}