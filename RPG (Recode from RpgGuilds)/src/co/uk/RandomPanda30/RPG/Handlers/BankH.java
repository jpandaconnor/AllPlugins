package co.uk.RandomPanda30.RPG.Handlers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.RPG.RPG;

public class BankH {

	public RPG plugin;

	public BankH (RPG plugin) {
		this.plugin = plugin;
	}

	ActionH action = new ActionH(plugin);

	public boolean bankCommands(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		action.openBank(((Player) sender).getUniqueId());
		return true;
	}
}