package co.uk.RandomPanda30.FunSao;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {

	public Main plugin;
	public PluginDescriptionFile pdfFile;

	static String tag = "&F[&BFun SAO&F] ";

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = plugin.getDescription();

		sendMessage(tag + "&AIs being enabled", null);

		getCommand("ueat").setExecutor(new CMD_ueat());
		getCommand("ufab").setExecutor(new CMD_ufab());
	}

	@Override
	public void onDisable() {
		sendMessage(tag + "&CIs being disabled", null);

		plugin = null;
	}

	public static void sendMessage(String message, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(format(message));
		} else {
			player.sendMessage(format(message));
		}
	}

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static class Sender {

		private CommandSender sender;

		boolean player = false;
		public Player sendp = null;

		public Sender (CommandSender sender) {
			this.sender = sender;
			check();
		}

		public void check() {
			if (sender instanceof Player) {
				player = true;
				sendp = (Player) sender;
			}
		}

		public void sendMessage(String message) {
			if (!player) {
				Main.sendMessage(message, null);
			} else {
				Main.sendMessage(message, sendp);
			}
		}

		public CommandSender getSender() {
			return sender;
		}

	}

}
