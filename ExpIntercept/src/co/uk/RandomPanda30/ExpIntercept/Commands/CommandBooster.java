package co.uk.RandomPanda30.ExpIntercept.Commands;

import java.util.Calendar;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;
import co.uk.RandomPanda30.ExpIntercept.Boosters.Booster;
import co.uk.RandomPanda30.ExpIntercept.Methods.StringMethods;

public class CommandBooster implements CommandExecutor {

	// booster <name> <time (m)> <multiplier>

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			StringMethods.sendMessage((String) ExpInterceptData.messagesC
					.get("CRITICAL.ONLYCONSOLE"), player);
			return true;
		}

		if (args.length != 3) {
			StringMethods.sendMessage(
					(String) ExpInterceptData.messagesC.get("SYNTAX.BOOSTER"),
					null);
			return true;
		}

		String name = args[0];
		int time = Integer.parseInt(args[1]);
		String multiplier = args[2].toString().replace("%", "");
		int newMulti = Integer.parseInt(multiplier);

		// Player player = null;
		@SuppressWarnings("deprecation")
		UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();

		Booster booster = new Booster(uuid, name, time, newMulti,
				Calendar.getInstance().getTimeInMillis());
		booster.start();

		return true;
	}
}