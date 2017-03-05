package co.uk.RandomPanda30.Wild.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.Wild.Main;
import co.uk.RandomPanda30.Wild.Handler.MessageH;

public class Command_fb implements CommandExecutor {

	public Command_fb (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("fb")
				|| cmd.getName().equalsIgnoreCase("facebook")) {
			sender.sendMessage(MessageH.fbMessage);
		}
		return true;
	}
}
