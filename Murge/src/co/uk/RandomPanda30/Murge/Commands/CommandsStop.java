package co.uk.RandomPanda30.Murge.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Commands.Stop;
import co.uk.RandomPanda30.Murge.Executor.DayExecutor;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class CommandsStop implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length < 0) {
			StringMethods.sendUnknownMessage(
					(String) Murge.mMap.getStat(MessagesValues.HELP_BADSYNTAX),
					sender);
			return true;
		}

		if (Stop.getCollection().isEnabled()) {
			if (!(sender instanceof Player)) {
				if (MurgeData.isPurge()) {
					DayExecutor de = new DayExecutor();
					de.execute();
					Murge.purgeChecker.cancel();
				} else {
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.PURGE_ALREADYOFF), null);
				}
			} else {
				Player player = (Player) sender;
				if (player.hasPermission("murge.stop")) {
					if (MurgeData.isPurge()) {
						DayExecutor de = new DayExecutor();
						de.execute();
						Murge.purgeChecker.cancel();
					} else {
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.PURGE_ALREADYOFF),
								player);
					}
				} else {
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.NOPERMISSION), player);
				}
			}
		} else {
			StringMethods.sendUnknownMessage((String) Murge.mMap
					.getStat(MessagesValues.COMMAND_NOTENABLED), sender);
		}
		return true;
	}
}