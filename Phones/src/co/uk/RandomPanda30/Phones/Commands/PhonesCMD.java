package co.uk.RandomPanda30.Phones.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Phones.Methods.PhonesMethods;

public class PhonesCMD implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String args[]) {
		Player player = (Player) cmdSender;
		PhonesMethods.sendPlayerMessage(player, label);
		return true;
	}

}
