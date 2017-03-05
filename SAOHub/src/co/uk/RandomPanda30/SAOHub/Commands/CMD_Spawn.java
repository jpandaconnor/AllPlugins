package co.uk.RandomPanda30.SAOHub.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.SAOHub.Hub;

public class CMD_Spawn implements CommandExecutor {

	public Hub plugin;

	public CMD_Spawn(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("spawn").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfig().getString("messages.playeronly")));
			return true;
		}
		return true;
	}
}