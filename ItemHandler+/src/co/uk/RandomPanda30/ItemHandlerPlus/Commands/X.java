package co.uk.RandomPanda30.ItemHandlerPlus.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.ItemHandlerPlus.B;
import co.uk.RandomPanda30.ItemHandlerPlus.Methods.Y;

public class X implements CommandExecutor {

	private static HashMap<String, XX> commands = new HashMap<String, XX>();

	public void register(String name, XX cmd) {
		commands.put(name, cmd);
	}

	public boolean exists(String name) {
		return commands.containsKey(name);
	}

	public XX getExecutor(String name) {
		return commands.get(name);
	}

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = null;
		if (cmdSender instanceof Player) {
			player = (Player) cmdSender;
			if (args.length == 0) {
				getExecutor("ihp").onCommand(cmdSender, cmd, label, args);
				return true;
			}

			if (args.length > 0) {
				if (exists(args[0])) {
					getExecutor(args[0]).onCommand(cmdSender, cmd, label, args);
					return true;
				} else {
					Y.sendMessage(
							(String) B.messagesC.get("CRITICAL.COMMANDNULL"),
							player);
					return true;
				}
			}
		} else {
			Y.sendMessage((String) B.messagesC.get("CRITICAL.PLAYERONLY"), null);
		}
		return false;
	}
}