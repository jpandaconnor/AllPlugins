package co.uk.RandomPanda30.CasinoM.Commands.CommandList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.CasinoM.CasinoData;
import co.uk.RandomPanda30.CasinoM.Commands.CommandInterface;
import co.uk.RandomPanda30.CasinoM.Items.BaseItems;
import co.uk.RandomPanda30.CasinoM.Methods.ItemMethods;
import co.uk.RandomPanda30.CasinoM.Methods.StringMethods;

public class CommandWand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if ((Boolean) CasinoData.configC.get("COMMAND.WAND")) {
			if (player.hasPermission("casinom.wand")) {
				if (args.length > 1) {
					StringMethods.sendMessage((String) CasinoData.messagesC
							.get("CRITICAL.INVALIDCOMMAND"), player);
					return true;
				}

				if (!player.getInventory().contains(BaseItems.obtainWand())) {
					ItemMethods.giveItem(player, BaseItems.obtainWand());
					StringMethods.sendMessage((String) CasinoData.messagesC
							.get("GENERAL.GIVENWAND"), player);
				} else {
					StringMethods.sendMessage((String) CasinoData.messagesC
							.get("CRITICAL.ALREADYHAVE"), player);
				}
			}
		}
		return true;
	}
}