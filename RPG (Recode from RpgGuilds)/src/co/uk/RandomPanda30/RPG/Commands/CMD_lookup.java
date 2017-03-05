package co.uk.RandomPanda30.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.RPG.RPG;

public class CMD_lookup implements CommandExecutor {

	public RPG plugin;

	public CMD_lookup (RPG plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player p = (Player) sender;
		if (args.length != 1) {
			p.sendMessage("Improper usage! Please use /lookup {Player name}");
			return true;
		}

		if (Bukkit.getPlayer(args[0]) == null) {
			p.sendMessage("This player could not be found");
			return true;
		}

		Player player = Bukkit.getPlayer(args[0]);
		double money = plugin.getRPGEconomy().getBalance(player.getUniqueId());

		Integer kills = Integer.valueOf(plugin.getRPGValues().getConfigC()
				.getInt("Kills." + player.getUniqueId().toString()));
		// p.sendMessage("&APlayer Lookup: &e" + player.getName());
		if (plugin.getRPGValues().getConfigC()
				.contains(player.getUniqueId().toString())) {
			String guild = (String) plugin.getRPGValues().getConfigC()
					.get(player.getUniqueId().toString() + ".Guild.Name");
			// p.sendMessage("&APlayer Lookup: &5Guild - " + guild);
		}
		// p.sendMessage("�APlayer Lookup: &4Kills - " + kills);
		// p.sendMessage("�APlayer Lookup: &2Money - " + money);
		return true;
	}
}