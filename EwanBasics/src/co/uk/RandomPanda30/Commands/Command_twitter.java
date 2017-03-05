package co.uk.RandomPanda30.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.Basics.Main;
import co.uk.RandomPanda30.Handler.MessageH;

public class Command_twitter implements CommandExecutor {

	public Command_twitter (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("twitter")) {
			sender.sendMessage(MessageH.twMessage);
		}
		return true;
	}

}
