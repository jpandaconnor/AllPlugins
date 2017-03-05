package co.uk.randompanda30.petstorage.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    private static HashMap<String, CommandInterface> commands = new HashMap<>();

    public static boolean isPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            // Send message here
            return false;
        }
        return true;
    }

    public void register(String name, CommandInterface cmd) {
        commands.put(name, cmd);
    }

    public boolean exists(String name) {
        return commands.containsKey(name);
    }

    public CommandInterface getExecutor(String name) {
        return commands.get(name);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            getExecutor(cmd.getName()).onCommand(sender, cmd, label, args);
            return true;
        }

        if (args.length > 0) {
            if (exists(args[0])) {
                getExecutor(args[0]).onCommand(sender, cmd, label, args);
                return true;
            } else {
                // error here
                return true;
            }
        }
        return false;
    }
}