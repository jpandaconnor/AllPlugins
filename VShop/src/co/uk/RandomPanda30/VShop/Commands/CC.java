package co.uk.RandomPanda30.VShop.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.VShop.B;
import co.uk.RandomPanda30.VShop.Events.EA;
import co.uk.RandomPanda30.VShop.Files.Config;
import co.uk.RandomPanda30.VShop.Files.Messages;
import co.uk.RandomPanda30.VShop.Methods.U;
import co.uk.RandomPanda30.VShop.Methods.V;
import co.uk.RandomPanda30.VShop.Methods.X;
import co.uk.RandomPanda30.VShop.Methods.Y;

public class CC implements Y {

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = (Player) cmdSender;
		if ((Boolean) Config.CMD_CREATE.value == true) {
			if (player.hasPermission("vshop.create")) {
				if (args.length > 3) {
					X.sendMessage((String) Messages.CRITICAL_INVALIDCMD.value,
							player);
					return true;
				}
				if (EA.getPos1() != null && EA.getPos2() != null) {
					if (args[1] != null && args[2] != null) {
						if (args[1].toString().equals("safezone")) {
							U.createSafeZone(EA.getPos1(), EA.getPos2());
							return true;
						}
						if (!B.shopsC.contains(args[1])) {
							V.createPlot(args[1], EA.getPos1(), EA.getPos2(),
									Integer.parseInt(args[2]));
							X.sendMessage(
									(String) Messages.VSHOPS_PLOTCREATED.value
											.toString().replaceAll("'plot'",
													args[1]), player);
						} else {
							X.sendMessage(
									(String) Messages.CRITICAL_ALREADYEXISTS.value,
									player);
						}
					} else {
						X.sendMessage(
								(String) Messages.CRITICAL_INVALIDCMD.value,
								player);
					}
				} else {
					X.sendMessage((String) Messages.CRITICAL_NOTSELECTED.value,
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
