package co.uk.RandomPanda30.GBlock.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.GBlock.GBlock;
import co.uk.RandomPanda30.GBlock.Plot.PlotHandler;
import net.md_5.bungee.api.ChatColor;

public class CommandGuild implements CommandExecutor {

	public GBlock plugin;
	public PlotHandler ph;

	public CommandGuild (GBlock plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender()
					.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&CYou cannot do this command as console"));
			return true;
		}
		
		if(args.length == 0) {
			return false;
		}

		return true;
	}

}