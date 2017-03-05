package co.uk.RandomPanda30.VShop.Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.VShop.Files.Messages;
import co.uk.RandomPanda30.VShop.Methods.X;
import co.uk.RandomPanda30.VShop.Methods.Y;

public class CD implements Y {

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if (args.length > 1) {
			X.sendMessage((String) Messages.CRITICAL_INVALIDCMD.value, player);
			return true;
		}
		ArrayList<String> m = (ArrayList<String>) Messages.VSHOP_COMMANDS.value;
		ArrayList<String> mn = new ArrayList<String>();

		for (String message : m) {
			mn.add(X.formatMessage(message));
		}

		for (String newMessage : mn) {
			X.sendMessage(newMessage, player);
		}

		return true;
	}

}
