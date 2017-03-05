package co.uk.RandomPanda30.VShop.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.VShop.Files.Config;
import co.uk.RandomPanda30.VShop.Files.Messages;
import co.uk.RandomPanda30.VShop.Items.I;
import co.uk.RandomPanda30.VShop.Methods.X;
import co.uk.RandomPanda30.VShop.Methods.Y;

public class CB implements Y {

	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = (Player) cmdSender;
		if ((Boolean) Config.CMD_WAND.value == true) {
			if (player.hasPermission("vshop.wand")) {
				if (args.length > 1) {
					X.sendMessage((String) Messages.CRITICAL_INVALIDCMD.value,
							player);
					return true;
				}
				if (!player.getInventory().contains(I.obtainWand())) {
					player.getInventory().addItem(I.obtainWand());
					X.sendMessage((String) Messages.VSHOPS_WANDGIVEN.value,
							player);
				} else {
					X.sendMessage(
							(String) Messages.CRITICAL_ALREADYININV.value,
							player);
				}
			} else {
				X.sendMessage((String) Messages.CRITICAL_NOPERM.value, player);
			}
		} else {
			X.sendMessage((String) Messages.CRITICAL_CMDDISABLED.value
					.toString().replaceAll("'cmd'", cmd.toString()), player);
		}
		return true;
	}
}