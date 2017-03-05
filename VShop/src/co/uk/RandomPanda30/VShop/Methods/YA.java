package co.uk.RandomPanda30.VShop.Methods;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class YA implements CommandExecutor {

	private static HashMap<String, Y> commands = new HashMap<String, Y>();

	public void register(String name, Y cmd) {
		commands.put(name, cmd);
	}

	public boolean exists(String name) {
		return commands.containsKey(name);
	}

	public Y getExecutor(String name) {
		return commands.get(name);
	}

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = null;
		if (cmdSender instanceof Player) {
			player = (Player) cmdSender;
			if (args.length == 0) {
				getExecutor("vshop").onCommand(cmdSender, cmd, label, args);
				return true;
			}

			if (args.length > 0) {
				if (exists(args[0])) {
					getExecutor(args[0]).onCommand(cmdSender, cmd, label, args);
					return true;
				} else {
					// BlockHuntMethods.sendPlayerMessage(
					// (String) BlockHunt.messagesC
					// .get("CRITICAL.COMMANDNULL"), player);
					return true;
				}
			}
		} else {
			// sendConsoleMessage((String) CRITICAL_PLAYERONLY.value);
		}
		return false;
	}

}
