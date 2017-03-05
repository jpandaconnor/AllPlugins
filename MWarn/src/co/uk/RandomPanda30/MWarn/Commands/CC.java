package co.uk.RandomPanda30.MWarn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.Methods.W;
import co.uk.RandomPanda30.MWarn.Methods.X;
import co.uk.RandomPanda30.MWarn.Methods.Y;

public class CC implements X {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if ((Boolean) B.configC.get("CMD.CLEAR") == true) {
			if (player.hasPermission("mwarn.clear")) {
				if (args.length < 2) {
					Y.sendMessage(
							(String) B.messagesC.get("CRITICAL.INVALIDCOMMAND"),
							player);
					return true;
				}
				Player target = Bukkit.getPlayer(args[1]);
				if (target.isOnline()) {
					W.removeAllWarnings(target);
					Y.sendMessage(
							(String) B.messagesC
									.get("MWARN.PLAYERCLEARED")
									.toString()
									.replaceAll("'playername'",
											target.getName()), player);
					Y.sendMessage(
							(String) B.messagesC
									.get("MWARN.TARGETCLEARED")
									.toString()
									.replaceAll("'playername'",
											player.getName()), target);
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