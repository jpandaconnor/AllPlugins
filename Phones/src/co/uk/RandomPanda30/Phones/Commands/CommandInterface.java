package co.uk.RandomPanda30.Phones.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandInterface {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args);

}
