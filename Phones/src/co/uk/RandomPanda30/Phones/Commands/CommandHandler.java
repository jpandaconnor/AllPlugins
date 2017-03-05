package co.uk.RandomPanda30.Phones.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Phones.Files.Messages;
import co.uk.RandomPanda30.Phones.Methods.PhonesMethods;

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
		Player player = null;
		if (cmdSender instanceof Player) {
			player = (Player) cmdSender;
			if (args.length == 0) {
				getExecutor("phones").onCommand(cmdSender, cmd, label, args);
				return true;
			}

			if (args.length > 0) {
				if (exists(args[0])) {
					getExecutor(args[0]).onCommand(cmdSender, cmd, label, args);
					return true;
				} else {
					PhonesMethods.sendPlayerMessage(player,
							(String) Messages.CRITICAL_COMMANDNULL.value);
					return true;
				}
			}
		} else {
			PhonesMethods
					.sendConsoleMessage((String) Messages.CRITICAL_PLAYERONLY.value);
		}
		return false;
	}
}
