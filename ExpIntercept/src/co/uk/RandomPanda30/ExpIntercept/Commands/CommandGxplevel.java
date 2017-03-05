package co.uk.RandomPanda30.ExpIntercept.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;
import co.uk.RandomPanda30.ExpIntercept.Methods.IO;
import co.uk.RandomPanda30.ExpIntercept.Methods.StringMethods;

public class CommandGxplevel implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length > 1) {
			if (!(sender instanceof Player)) {
				StringMethods.sendMessage((String) ExpInterceptData.messagesC
						.get("CRITICAL.WRONGARGS"), null);
			} else {
				Player player = (Player) sender;
				StringMethods.sendMessage((String) ExpInterceptData.messagesC
						.get("CRITICAL.WRONGARGS"), player);
			}
			return true;
		}

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission("gxplevel.change")) {

				return true;
			}
		}

		String s = args[0];
		s = s.replace("%", "");

		try {
			int i = Integer.parseInt(s);
			IO.setAmount(i);
			if (!(sender instanceof Player)) {
				StringMethods
						.sendMessage(
								(String) ExpInterceptData.messagesC
										.get("EXPINTERCEPT.CHANGED").toString()
										.replace("%arg", Integer.toString(i)),
								null);
			} else {
				Player player = (Player) sender;
				StringMethods
						.sendMessage(
								(String) ExpInterceptData.messagesC
										.get("EXPINTERCEPT.CHANGED").toString()
										.replace("%arg", Integer.toString(i)),
								player);
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}

		return true;
	}
}