package co.uk.RandomPanda30.MWarn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.W;
import co.uk.RandomPanda30.MWarn.Methods.X;
import co.uk.RandomPanda30.MWarn.Methods.Y;

public class CB implements X {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if ((Boolean) B.configC.get("CMD.WARN") == true) {
			if (player.hasPermission("mwarn.warn")) {
				if (args.length < 2) {
					Y.sendMessage(
							(String) B.messagesC.get("CRITICAL.INVALIDCOMMAND"),
							player);
					return true;
				}
				Player target = Bukkit.getPlayer(args[1]);
				String reason = Y.buildMessage(args, 2);
				if (target.isOnline()) {
					if ((Boolean) B.configC.get("WARN.NOREASON") == true) {
						if (reason == null) {
							reason = "No reason";
						}
					} else {
						Y.sendMessage((String) B.messagesC
								.get("CRITICAL.REASONCANNOTBENULL"), player);
						return true;
					}
					W.addWarning(target, player, reason);
					Y.sendMessage(
							(String) B.messagesC.get("MWARN.PLAYERWARNED")
									.toString()
									.replaceAll("'playername'", args[1])
									.replaceAll("'reason'", reason), player);
					Y.sendMessage(
							(String) B.messagesC
									.get("MWARN.TARGETWARNED")
									.toString()
									.replaceAll("'playername'",
											player.getName())
									.replaceAll("'reason'", reason), target);
					if ((Boolean) B.configC.get("BROADCAST.WARNING") == true) {
						String message = (String) B.messagesC.get(
								"MWARN.BROADCASTWARNED").toString();
						message = message.replaceAll("'playername'",
								target.getName());
						message = message.replaceAll("'playername2'",
								player.getName());
						message = message.replaceAll("'reason'", reason);
						Y.broadcastMessage(message);
					}
				} else {
					Y.sendMessage(
							(String) B.messagesC.get("CRITICAL.PLAYERINVALID"),
							player);
				}
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