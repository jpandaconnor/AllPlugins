package co.uk.RandomPanda30.MWarn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.W;
import co.uk.RandomPanda30.MWarn.Methods.X;
import co.uk.RandomPanda30.MWarn.Methods.Y;

public class CD implements X {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if ((Boolean) B.configC.get("CMD.REMOVE") == true) {
			if (player.hasPermission("mwarn.remove")) {
				if (args.length < 2) {
					Y.sendMessage(
							(String) B.messagesC.get("CRITICAL.INVALIDCOMMAND"),
							player);
					return true;
				}
				int configNum = 0;
				Player target = Bukkit.getPlayer(args[1]);
				Bukkit.broadcastMessage(args[1]);
				if (target.isOnline()) {
					try {
						configNum = Integer.parseInt(args[2]);
					} catch (NumberFormatException e) {
						// Message here
						return true;
					}
				} else {
					Y.sendMessage(
							(String) B.messagesC.get("CRITICAL.PLAYERINVALID"),
							player);
					return true;
				}

				W.removeWarning(target, configNum);
				Y.sendMessage(
						(String) B.messagesC
								.get("MWARN.WARNINGREMOVED")
								.toString()
								.replaceAll("'warning'",
										Integer.toString(configNum)), player);
			} else {
				Y.sendMessage((String) B.messagesC.get("CRITICAL.NOPERM"),
						player);
			}
		} else {
			Y.sendMessage(
					(String) B.messagesC.get("CRITICAL.COMMANDNOTENABLED"),
					player);
		}
		return true;
	}
}