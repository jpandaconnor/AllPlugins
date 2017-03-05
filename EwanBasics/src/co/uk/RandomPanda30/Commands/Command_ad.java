package co.uk.RandomPanda30.Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.Basics.Main;
import co.uk.RandomPanda30.Handler.MessageH;

public class Command_ad implements CommandExecutor {

	public Command_ad (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		ArrayList<String> adminNames = (ArrayList<String>) Main.plugin
				.getConfig().getStringList("adminNames");
		if (adminNames.contains(sender)
				|| sender.hasPermission("serverbasics.adminchat")
				|| sender.isOp()) {
			if (cmd.getName().equalsIgnoreCase("ad")) {
				String message = MessageH.buildMessage(args, 0);
				MessageH.sendAdminsMessage(sender.getName(), message);
			}
		} else {
			sender.sendMessage(MessageH.noPermission);
		}
		return true;
	}
}
