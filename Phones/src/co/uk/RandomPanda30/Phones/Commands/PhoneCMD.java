package co.uk.RandomPanda30.Phones.Commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Phones.Files.Config;
import co.uk.RandomPanda30.Phones.Files.Messages;
import co.uk.RandomPanda30.Phones.Items.PhoneI;
import co.uk.RandomPanda30.Phones.Methods.PhonesMethods;

public class PhoneCMD implements CommandExecutor {

	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = (Player) cmdSender;
		if ((Boolean) Config.PHONES_ENABLED.value == true) {
			if (player.hasPermission("phones.command")) {
				if (args.length > 1) {
					PhonesMethods.sendPlayerMessage(player,
							(String) Messages.SYNTAX_PHONE.value);
					return true;
				}
				if (!player.getInventory().contains(PhoneI.phoneI())) {
					player.getInventory().addItem(PhoneI.phoneI());
					PhonesMethods.sendPlayerMessage(player,
							(String) Messages.PHONES_GIVENPHONE.value);
					player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
				} else {
					PhonesMethods
							.sendPlayerMessage(
									player,
									(String) Messages.CRITICAL_ALREADYCONTAINSPHONE.value);
				}
			}
		}
		return true;
	}
}
