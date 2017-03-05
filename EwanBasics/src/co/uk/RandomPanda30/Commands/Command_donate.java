package co.uk.RandomPanda30.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.Basics.Main;
import co.uk.RandomPanda30.Handler.MessageH;

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
