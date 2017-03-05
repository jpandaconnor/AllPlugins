package co.uk.RandomPanda30.MWarn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Display.V;
import co.uk.RandomPanda30.MWarn.Methods.X;
import co.uk.RandomPanda30.MWarn.Methods.Y;

public class CA implements X {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (player.hasPermission("mwarn.admin")) {
			V.openAdminInventory(player);
			Y.sendMessage((String) B.messagesC.get("INVENTORY.OPENED")
					.toString().replaceAll("'type'", "Admin"), player);
		} else if (player.hasPermission("mwarn.moderator")) {
			V.openAdminInventory(player);
			Y.sendMessage((String) B.messagesC.get("INVENTORY.OPENED")
					.toString().replaceAll("'type'", "Admin"), player);
		} else {
			V.openPlayerInventory(player);
			Y.sendMessage((String) B.messagesC.get("INVENTORY.OPENED")
					.toString().replaceAll("'type'", "Player"), player);
		}
		return true;
	}
}