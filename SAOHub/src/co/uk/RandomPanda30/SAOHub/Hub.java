package co.uk.RandomPanda30.SAOHub;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.SAOHub.Commands.CMD_Setspawn;
import co.uk.RandomPanda30.SAOHub.Commands.CMD_Spawn;

public class Hub extends JavaPlugin implements Listener, CommandExecutor {

	/*
	 * Help - Help override
	 * 
	 * Set spawn - Add to config
	 * 
	 * 
	 */

	public Hub plugin;
	public PluginDescriptionFile pdfFile;

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		this.saveDefaultConfig();
		addDefaults();

		if (getConfig().getBoolean("enabled") == false) {
			Bukkit.getServer().getPluginManager().disablePlugin(this);
			// Message here
			return;
		}

		register();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		return true;
	}

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onPlayerCommandPreprocessEvent(
			PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();

		if (message.contains("plugins")) {
			if (getConfig().getBoolean("pl.block")) {
				if (!player.hasPermission("hub.plugins")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', plugin.getConfig()
									.getString("messages.pluginsblocked")));
					event.setCancelled(true);
				}
			}
		} else if (message.contains("pl")) {
			if (getConfig().getBoolean("pl.block")) {
				if (!player.hasPermission("hub.plugins")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', plugin.getConfig()
									.getString("messages.pluginsblocked")));
					event.setCancelled(true);
				}
			}
		} else if (message.contains("help")) {
			if (plugin.getConfig().getBoolean("help.override")) {
				for (String s : (ArrayList<String>) plugin.getConfig()
						.get("help.list")) {
					player.sendMessage(
							ChatColor.translateAlternateColorCodes('&', s));
				}
			}
		} else if (message.equals("spawn")) {
			if (plugin.getConfig().contains("spawn")) {
				Location location = (Location) plugin.getConfig().get("spawn");
				player.teleport(location);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig()
								.getString("messages.sendingtospawn")));
			} else {
				player.teleport(
						player.getLocation().getWorld().getSpawnLocation());
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("messages.nospawnset")));
			}
		} else if (message.contains("setspawn")) {
			if (player.hasPermission("hub.spawnset")) {
				plugin.getConfig().set("spawn", player.getLocation());
				plugin.saveConfig();

				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("messages.spawnset")));
			}
		} else if (message.contains("reload")) {
			if (getConfig().getBoolean("rl.block")) {
				if (!player.hasPermission("hub.reload")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', getConfig().getString("messages.noreload")));
				}
			}
		} else if (message.contains("rl")) {
			if (getConfig().getBoolean("rl.block")) {
				if (!player.hasPermission("hub.reload")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', getConfig().getString("messages.noreload")));
				}
			} 
		} else if (message.equals("fly")) {
			if (player.hasPermission("hub.fly")) {
				if (player.getAllowFlight()) {
					player.setAllowFlight(false);
					player.setFlying(false);
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							getConfig().getString("messages.stoppedflying")));
				} else {
					player.setAllowFlight(true);
					player.setFlying(true);
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							getConfig().getString("messages.startedflying")));
				}
			}
		} else {
			if (!player.hasPermission("hub.allcommands")) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						getConfig().getString("messages.nothubcommand")));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		event.setJoinMessage("");

		Player player = event.getPlayer();

		if (plugin.getConfig().contains("spawn")) {
			Location location = (Location) plugin.getConfig().get("spawn");
			player.teleport(location);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfig().getString("messages.sendingtospawn")));
		} else {
			player.teleport(player.getLocation().getWorld().getSpawnLocation());
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfig().getString("messages.nospawnset")));
		}
	}

	@EventHandler
	public void onPlayerLeaveEvent(PlayerQuitEvent event) {
		event.setQuitMessage("");
	}

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		DamageCause cause = event.getCause();

		if (entity instanceof Player) {
			Player player = (Player) entity;
			if (cause.equals(DamageCause.VOID)) {
				if (plugin.getConfig().contains("spawn")) {
					Location location = (Location) plugin.getConfig()
							.get("spawn");
					player.teleport(location);
				} else {
					player.teleport(
							player.getLocation().getWorld().getSpawnLocation());
				}
			}
		}
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();

		if (!player.hasPermission("hub.build")) {

			event.setCancelled(true);
		}
	}

	private void addDefaults() {
		if (!this.getConfig().contains("enabled")) {
			this.getConfig().set("enabled", true);
		}

		if (!this.getConfig().contains("void.death")) {
			this.getConfig().set("void.death", false);
		}

		if (!this.getConfig().contains("void.teleporttospawn")) {
			this.getConfig().set("void.teleporttospawn", true);
		}

		if (!this.getConfig().contains("help.override")) {
			this.getConfig().set("help.override", true);
		}

		if (!this.getConfig().contains("help.list")) {
			this.getConfig()
					.set("help.list",
							new ArrayList<String>(Arrays.asList(
									"This is the help menu",
									"Add commands and stuff in the config")));
		}

		if (!this.getConfig().contains("pl.block")) {
			this.getConfig().set("pl.block", true);
		}

		if (!this.getConfig().contains("rl.block")) {
			this.getConfig().set("rl.block", true);
		}

		/*
		 * Messages here
		 */

		if (!this.getConfig().contains("messages.nospawnset")) {
			this.getConfig().set("messages.nospawnset",
					"&BSending you to the default world location");
		}

		if (!this.getConfig().contains("messages.sendingtospawn")) {
			this.getConfig().set("messages.sendingtospawn",
					"&BSending you to spawn");
		}

		if (!this.getConfig().contains("messages.incorrectsyntax")) {
			this.getConfig().set("messages.incorrectsyntax",
					"&CIncorrect syntax. Do /help for a list of commands");
		}

		if (!this.getConfig().contains("messages.playeronly")) {
			this.getConfig().set("messages.playeronly",
					"&CThis command can only be done by a player");
		}

		if (!this.getConfig().contains("messages.spawnset")) {
			this.getConfig().set("messages.spawnset", "&BSpawn has been set");
		}

		if (!this.getConfig().contains("messages.pluginsblocked")) {
			this.getConfig().set("messages.pluginsblocked",
					"&BAll our plugins are custom coded :) &5~ Panda");
		}

		if (!this.getConfig().contains("messages.noreload")) {
			this.getConfig().set("messages.noreload",
					"&BHahahaha nice try! :) &5~ Panda");
		}

		if (!this.getConfig().contains("messages.nothubcommand")) {
			this.getConfig().set("messages.nothubcommand",
					"&CThis command has not been found. Do /help for a list of hub commands");
		}

		if (!this.getConfig().contains("messages.stoppedflying")) {
			this.getConfig().set("messages.stoppedflying",
					"&CYou have stopped flying");
		}

		if (!this.getConfig().contains("messages.startedflying")) {
			this.getConfig().set("messages.startedflying",
					"&AYou have started flying");
		}

		if (!this.getConfig().contains("messages.nobuiild")) {
			this.getConfig().set("messages.nobuiild", "&CNope.");
		}

		saveConfig();
	}

	private void register() {
		new CMD_Spawn(this);
		new CMD_Setspawn(this);
	}
}