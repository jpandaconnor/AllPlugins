package co.uk.RandomPanda30.Wild.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.Wild.Main;
import co.uk.RandomPanda30.Wild.Handler.MessageH;

public class Command_donate implements CommandExecutor {

	public Command_donate (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equals("donate")) {
			sender.sendMessage(MessageH.donateMessage);
		}
		return true;
	}

}
