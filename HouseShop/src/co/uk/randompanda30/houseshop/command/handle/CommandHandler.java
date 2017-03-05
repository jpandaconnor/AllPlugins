package co.uk.randompanda30.houseshop.command.handle;

import co.uk.randompanda30.houseshop.CommandTpdir;
import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.command.*;
import co.uk.randompanda30.houseshop.command.friend.CommandFriendAdd;
import co.uk.randompanda30.houseshop.command.friend.CommandFriendDelete;
import co.uk.randompanda30.houseshop.command.list.CommandList;
import co.uk.randompanda30.houseshop.command.list.CommandListA;
import co.uk.randompanda30.houseshop.command.list.CommandListO;
import co.uk.randompanda30.houseshop.command.request.CommandRequest;
import co.uk.randompanda30.houseshop.command.request.CommandRequestCancel;
import co.uk.randompanda30.houseshop.command.request.CommandRequestDone;
import co.uk.randompanda30.houseshop.command.request.CommandRequestList;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.Format;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 02/05/16.
 */
public class CommandHandler {

    HouseShop plugin;

    public CommandHandler(HouseShop plugin) {
        this.plugin = plugin;
    }

    private Command getCommandMatch(org.bukkit.command.Command command, String[] args) {
        if (command.getName().equals("houseshop")) {
            if (args.length == 0) return new CommandHelp();

            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("block"))
                    return new CommandBlock();
                if (args[0].equalsIgnoreCase("configure"))
                    return new CommandConfigure();

                if (args[0].equalsIgnoreCase("purchase") || args[0].equalsIgnoreCase("buy"))
                    return new CommandPurchase();
                if (args[0].equalsIgnoreCase("sell"))
                    return new CommandSell();
                if (args[0].equalsIgnoreCase("menu"))
                    return new CommandMenu();
                if (args[0].equalsIgnoreCase("delete"))
                    return new CommandDelete();

                if (args[0].equalsIgnoreCase("help"))
                    return new CommandHelp();

                if(args[0].equalsIgnoreCase("tpdir"))
                    return new CommandTpdir();

                if(args[0].equalsIgnoreCase("list")) {
                    if(args.length > 1) {
                        if (args[0].equalsIgnoreCase("list") && args[1].equalsIgnoreCase("a"))
                            return new CommandListA();
                        if (args[0].equalsIgnoreCase("list") && args[1].equalsIgnoreCase("o"))
                            return new CommandListO();
                    } else {
                        if(args[0].equalsIgnoreCase("list"))
                            return new CommandList();
                    }
                }

                if(args[0].equalsIgnoreCase("tp"))
                    return new CommandTP();
                if(args[0].equalsIgnoreCase("toggle"))
                    return new CommandToggle();
                if(args[0].equalsIgnoreCase("home"))
                    return new CommandHome();

                if(args[0].equalsIgnoreCase("random"))
                    return new CommandRandom();

                if(args[0].equalsIgnoreCase("request")) {
                    if (args.length > 1) {
                        if (args[0].equalsIgnoreCase("request") && args[1].equalsIgnoreCase("cancel"))
                            return new CommandRequestCancel();
                        if (args[0].equalsIgnoreCase("request") && args[1].equalsIgnoreCase("done"))
                            return new CommandRequestDone();
                        if (args[0].equalsIgnoreCase("request") && args[1].equalsIgnoreCase("list"))
                            return new CommandRequestList();
                    } else {
                        if(args[0].equalsIgnoreCase("request"))
                            return new CommandRequest();
                    }
                }

                // if (args[0].equalsIgnoreCase("kickout"))
                // return new CommandKickout();
                // if (args[0].equalsIgnoreCase("visit"))
                // return new CommandVisit();

                    /*
                    Friends here
                     */


                if (args[0].equalsIgnoreCase("friend") && args[1].equalsIgnoreCase("add"))
                    return new CommandFriendAdd();
                if (args[0].equalsIgnoreCase("friend") && (args[1].equalsIgnoreCase("delete") ||
                        args[1].equalsIgnoreCase("remove")))
                    return new CommandFriendDelete();
            }
        }
        return null;
    }

    public List<Command> getCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new CommandHelp());
        commands.add(new CommandBlock());
        commands.add(new CommandConfigure());
        commands.add(new CommandPurchase());
        commands.add(new CommandSell());

        commands.add(new CommandList());
        commands.add(new CommandTP());
        commands.add(new CommandToggle());
        // commands.add(new CommandKickout());
        // commands.add(new CommandVisit());

        // commands.add(new CommandFriendAdd());
        // commands.add(new CommandFriendDelete());
        return commands;
    }

    public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Command command = getCommandMatch(cmd, args);

        if (command == null) return false;

        if (!command.isConsoleAllowed() && !(sender instanceof Player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_NOTFORCONSOLE.value, null);
            return true;
        }

        if (!command.getPermission().equals("") && !sender.hasPermission(command.getPermission())) {
            sender.sendMessage(Format.format(Messages.MessagesValues.HS_NOPERM.value.toString().
                    replace("%perm", command.getPermission())));
            return true;
        }

        command.runCommand(sender, args);
        return true;
    }
}