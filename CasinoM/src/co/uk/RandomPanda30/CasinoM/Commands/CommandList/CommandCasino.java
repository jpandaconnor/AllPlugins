package co.uk.RandomPanda30.CasinoM.Commands.CommandList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.CasinoM.Commands.CommandInterface;

public class CommandCasino implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		player.sendMessage(ChatColor.AQUA + "Test");
		return true;
	}
}