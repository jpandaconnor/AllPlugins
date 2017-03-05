package co.uk.RandomPanda30.FTP;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor, Listener {

	public Main plugin;
	public PluginDescriptionFile pdfFile;

	public char code = '&';
	public String tag = "";

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		tag = "&F[&B" + pdfFile.getName() + "&F] ";

		saveConfig();

		if (!getConfig().contains("Location")) {
			getConfig().set("Location.World", "world");
			getConfig().set("Location.X", 0);
			getConfig().set("Location.Y", 0);
			getConfig().set("Location.Z", 0);
			saveConfig();
		}

		getServer().getPluginManager().registerEvents(this, this);
		getCommand("ttp").setExecutor(this);

		sendMessage("&Ais being enabled", null, true);

	}

	@Override
	public void onDisable() {
		sendMessage("&Cis being disabled", null, true);
		plugin = null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!getConfig().contains(player.getUniqueId().toString())) {
				getConfig().createSection(player.getUniqueId().toString());
			} else {
				getConfig().set(player.getUniqueId().toString(), null);
			}
		}
		return true;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		UUID uuid = player.getUniqueId();

		if (getConfig().contains("Location")) {
			if (!getConfig().contains(uuid.toString())) {

				int x = getConfig().getInt("Location.X");
				int y = getConfig().getInt("Location.Y");
				int z = getConfig().getInt("Location.Z");
				World world = null;

				try {
					Bukkit.getWorld(getConfig().getString("Location.World"));
				} catch (NullPointerException e) {
					sendMessage(
							"&CTried to send to the configured location but could not find the world?",
							player, true);
					return;
				}

				run(player, player.getLocation());

				Location location = new Location(world, x, y, z);

				player.teleport(location);
			}
		}
	}

	public void run(final Player player, final Location location) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {

				player.teleport(location);

				sendMessage("&BTo toggle teleporting here on spawn, do /ttp",
						player, false);
			}
		}, 60L);
	}

	public String format(String message) {
		return ChatColor.translateAlternateColorCodes(code, message);
	}

	public void sendMessage(String message, Player player, boolean withTag) {
		if (withTag) {
			message = tag + message;
		}

		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(format(tag + message));
		} else {
			player.sendMessage(format(message));
		}
	}
}