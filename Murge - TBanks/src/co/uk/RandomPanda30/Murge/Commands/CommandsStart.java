package co.uk.RandomPanda30.Murge.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Collection.Commands.Start;

public class CommandsStart implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length < 0) {

			return true;
		}
		
		if (Start.getCollection().isEnabled()) {
			if (!(sender instanceof Player)) {
				
			} else {

			}
		}
		return true;
	}
}