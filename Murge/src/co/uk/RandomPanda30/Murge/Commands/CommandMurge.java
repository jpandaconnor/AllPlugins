package co.uk.RandomPanda30.Murge.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Displays.MainMenu;

public class CommandMurge implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (!player.hasPermission("murge.admin")) {
			MainMenu.openMainMenu(player);
		} else {
			MainMenu.openMainMenuA(player);
		}
		return true;
	}
}