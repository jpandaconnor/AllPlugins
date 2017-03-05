package co.uk.RandomPanda30.Murge.Handlers;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.Murge.Commands.CommandInterface;

public class CommandHandler implements CommandExecutor {

	private static HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();

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
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		if (args.length == 0) {
			getExecutor("murge").onCommand(cmdSender, cmd, label, args);
			return true;
		}

		if (args.length > 0) {
			if (exists(args[0])) {
				getExecutor(args[0]).onCommand(cmdSender, cmd, label, args);
				return true;
			} else {
				// P.sendMessage(
				// (String) B.messagesC.get("CRITICAL.COMMANDNULL"),
				// player);
				return true;
			}
		}
		return false;
	}
}