package co.uk.RandomPanda30.ItemHandlerPlus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.ItemHandlerPlus.Displays.D;

public class XA implements XX {

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = (Player) cmdSender;
		if (player.hasPermission("ihp.menu")) {
			D.openIHPMenu(player);
		}
		return true;
	}
}