package co.uk.RandomPanda30.MWarn.Methods;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MWarn.B;

public class XA implements CommandExecutor {

	private static HashMap<String, X> commands = new HashMap<String, X>();

	public void register(String name, X cmd) {
		commands.put(name, cmd);
	}

	public boolean exists(String name) {
		return commands.containsKey(name);
	}

	public X getExecutor(String name) {
		return commands.get(name);
	}

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = null;
		if (cmdSender instanceof Player) {
			player = (Player) cmdSender;
			if (args.length == 0) {
				getExecutor("mwarn").onCommand(cmdSender, cmd, label, args);
				return true;
			}

			if (args.length > 0) {
				if (!(Boolean) B.configC.get("PLUGIN.ENABLE")) {
					cmdSender.sendMessage(Y.formatMessage((String) B.messagesC
							.get("CRITICAL.WARNINGSOFF")));
					return true;
				}
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
			Y.sendMessage((String) B.configC.get("CRITICAL.PLAYERONLY"), player);
		}
		return false;
	}
}