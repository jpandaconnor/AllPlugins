package co.uk.RandomPanda30.KnightCrates.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;
import co.uk.RandomPanda30.KnightCrates.Util.Sender;
import net.md_5.bungee.api.ChatColor;

public class CommandXyfx implements CommandExecutor {

	public enum KeyType {
		IRON, GOLD, DIAMOND;
	}

	public KnightCrates plugin;

	public CommandXyfx (KnightCrates plugin) {
		this.plugin = plugin;
		plugin.getCommand("xyfx").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Sender s = new Sender(sender);

		if (s.isPlayer()) {
			s.sendMessage(ChatColor.translateAlternateColorCodes('&',
					KnightCrates.tag + "&COnly console can do this command"));
			return true;
		}

		if (Bukkit.getPlayer(args[0]) != null
				&& Bukkit.getPlayer(args[0]).isOnline()) {
			Player player = Bukkit.getPlayer(args[0]);

			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					KnightCrates.tag + "&AYou were given a "
							+ args[1].toString() + " &akey"));

			if (plugin.getConfig().contains(player.getUniqueId().toString()
					+ "." + args[1].toString().toLowerCase())) {
				int a = plugin.getConfig()
						.getInt(player.getUniqueId().toString() + "."
								+ args[1].toString().toLowerCase());
				plugin.getConfig().set(player.getUniqueId().toString() + "."
						+ args[1].toString().toLowerCase(), a + 1);
				plugin.saveConfig();
			} else {
				plugin.getConfig().set(player.getUniqueId().toString() + "."
						+ args[1].toString().toLowerCase(), 1);
				plugin.saveConfig();
			}
		} else {
			@SuppressWarnings("deprecation")
			OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);

			if (plugin.getConfig().contains(op.getUniqueId().toString() + "."
					+ args[1].toString().toLowerCase())) {
				int a = plugin.getConfig().getInt(op.getUniqueId().toString()
						+ "." + args[1].toString().toLowerCase());
				plugin.getConfig().set(op.getUniqueId().toString() + "."
						+ args[1].toString().toLowerCase(), a + 1);
				plugin.saveConfig();
			} else {
				plugin.getConfig().set(op.getUniqueId().toString() + "."
						+ args[1].toString().toLowerCase(), 1);
				plugin.saveConfig();
			}
		}
		return true;
	}
}