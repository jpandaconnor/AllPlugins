package co.uk.RandomPanda30.SAdminO.Commands;

import co.uk.RandomPanda30.SAdminO.Data;
import co.uk.RandomPanda30.SAdminO.Files.Messages.MessagesValues;
import co.uk.RandomPanda30.SAdminO.Util.Basic.MUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    private static HashMap<String, CommandInterface> commands = new HashMap<>();

    public void register(String name, CommandInterface cmd) {
        commands.put(name, cmd);
    }

    public boolean exists(String name) {
        return commands.containsKey(name);
    }

    public CommandInterface getExecutor(String name) {
        return commands.get(name);
    }

	/*
     * Console will do one argument commands whilst players can do whatever else
	 */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = null;

        if (sender instanceof org.bukkit.entity.Player) {
            player = (org.bukkit.entity.Player) sender;

            if (!Data.initialised) {
                // Message here
                return true;
            }

            if (args.length == 0) {
                getExecutor("saoa").onCommand(sender, cmd, label, args);
                return true;
            }

            if (args.length > 0) {
                if (exists(args[0])) {
                    getExecutor(args[0]).onCommand(sender, cmd, label, args);
                    return true;
                } else {
                    MUtil.sendMessage((String) MessagesValues.COMMAND_NULL.value, player);
                    return true;
                }
            }
        } else {
            MUtil.sendMessage((String) MessagesValues.COMMAND_PLAYERONLY.value, null);
        }
        return false;
    }
}