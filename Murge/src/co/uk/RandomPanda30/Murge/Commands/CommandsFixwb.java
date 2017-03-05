package co.uk.RandomPanda30.Murge.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Commands.Start;
import co.uk.RandomPanda30.Murge.Collection.World.WorldBorderCollection;
import co.uk.RandomPanda30.Murge.Handlers.WorldBorderHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class CommandsFixwb implements CommandInterface {

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
			WorldBorderCollection wbc = new WorldBorderCollection();
			if (!(sender instanceof Player)) {
				if (wbc.isEnabled()) {
					wbc.setEnabled(false);
					WorldBorderHandler.createWorldBorder(MurgeData.getWorld()
							.getSpawnLocation(), 60000000, 5);
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.WB_RESET), null);
				} else {
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.WB_ALREADYDISABLED), null);
				}
			} else {
				Player player = (Player) sender;
				if (player.hasPermission("murge.fixwb")) {
					if (wbc.isEnabled()) {
						wbc.setEnabled(false);
						WorldBorderHandler.createWorldBorder(MurgeData
								.getWorld().getSpawnLocation(), 60000000, 5);
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.WB_RESET), player);
					} else {
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.WB_ALREADYDISABLED),
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