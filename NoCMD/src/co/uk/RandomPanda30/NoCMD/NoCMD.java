package co.uk.RandomPanda30.NoCMD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoCMD extends JavaPlugin implements Listener {

	public NoCMD plugin;

	public boolean blocked;

	@Override
	public void onEnable() {
		plugin = this;

		Bukkit.getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&BNo command..."));

		getServer().getPluginManager().registerEvents(this, this);
		getCommand("nocmd").setExecutor(new NoCMD());
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&BNo command..."));
		plugin = null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (args.length < 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&CUsage: /nocmd"));
			return true;
		}

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("nocmd.command")) {
				if (!blocked) {
					blocked = true;
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&CYou have blocked all commands"));
				} else {
					blocked = false;
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&CYou have unblocked all commands"));
				}
			}
		} else {
			if (blocked) {
				blocked = true;
				Bukkit.getConsoleSender().sendMessage(
						ChatColor.translateAlternateColorCodes('&',
								"&CYou have blocked all commands"));
			} else {
				blocked = false;
				Bukkit.getConsoleSender().sendMessage(
						ChatColor.translateAlternateColorCodes('&',
								"&CYou have unblocked all commands"));
			}
		}
		return true;
	}

	@EventHandler
	public void onPreprocess(PlayerCommandPreprocessEvent event) {
		if (event.getMessage().equals("nocmd")) {
			return;
		}
		if (blocked) {
			event.getPlayer().sendMessage(
					ChatColor.translateAlternateColorCodes('&',
							"&CCommands are disabled"));
			event.setCancelled(true);
		}
	}
}