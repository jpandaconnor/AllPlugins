package co.uk.RandomPanda30.Murge.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Commands.Start;
import co.uk.RandomPanda30.Murge.Executor.PurgeExecutor;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class CommandsStart implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length < 0) {
			StringMethods.sendUnknownMessage(
					(String) Murge.mMap.getStat(MessagesValues.HELP_BADSYNTAX),
					sender);
			return true;
		}

		if (Start.getCollection().isEnabled()) {
			if (!(sender instanceof Player)) {
				if (!MurgeData.isPurge()) {
					PurgeExecutor pe = new PurgeExecutor();
					pe.execute();
				} else {
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.PURGE_ALREADYON), null);
				}
			} else {
				Player player = (Player) sender;
				if (player.hasPermission("murge.start")) {
					if (!MurgeData.isPurge()) {
						PurgeExecutor pe = new PurgeExecutor();
						pe.execute();
					} else {
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.PURGE_ALREADYON),
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