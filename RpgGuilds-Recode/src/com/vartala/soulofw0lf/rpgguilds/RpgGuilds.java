package com.vartala.soulofw0lf.rpgguilds;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import net.milkbowl.vault.economy.Economy;

@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class RpgGuilds extends JavaPlugin implements Listener {

	public static RpgGuilds plugin;
	public static PluginDescriptionFile pdfFile;

	public static List<UUID> gbanks = new ArrayList();
	// public static WorldGuardPlugin wgPlugin = null;
	public static Economy econ = null;

	public static BukkitTask checker;

	private String tag = "&F[&CRpgGuilds&F] ";

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		getCommand("guild").setExecutor(new GuildHandler(this));
		getCommand("g").setExecutor(new gcHandler(this));
		getCommand("o").setExecutor(new oHandler(this));
		getCommand("gtp").setExecutor(new gTP(this));
		getCommand("guilds").setExecutor(new guildslist(this));
		getCommand("lookup").setExecutor(new lookUpCommand(this));
		getCommand("hq").setExecutor(new hqCommand(this));
		getCommand("grank").setExecutor(new gRankHandler(this));
		getCommand("aguild").setExecutor(new Aguild(this));

		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new SignListener(), this);

		sendMessage(getTag() + "RpgGuilds has been enabled!", null);

		// for (Player player : Bukkit.getOnlinePlayers()) {
		// player.setDisplayName(player.getName());
		// }

		saveDefaultConfig();

		if (!getConfig().contains("TP")) {
			getConfig().set("TP", Boolean.valueOf(false));
			saveConfig();
		}

		if (!getConfig().contains("Rewards")) {
			getConfig().set("Rewards", new ArrayList<String>());
			saveConfig();
		}

		if (!getConfig().contains("RewardDelay")) {
			getConfig().set("RewardDelay", "10");
			saveConfig();
		}

		if (!getConfig().contains("Guild Names in Chat")) {
			getConfig().set("Guild Names in Chat", Boolean.valueOf(true));
			saveConfig();
		}

		if (!getConfig().contains("Chat")) {
			getConfig().set("Chat", Boolean.valueOf(true));
			saveConfig();
		}

		if (!getConfig().contains("No Build")) {
			getConfig().set("No Build", Boolean.valueOf(true));
			saveConfig();
		}

		if (!getConfig().contains("Signs")) {
			getConfig().set("Signs", new ArrayList<String>());
			saveConfig();
		}

		if (!getConfig().contains("NoHQWorlds")) {
			getConfig().set("NoHQWorlds", new ArrayList<String>());
			saveConfig();
		}

		if (!getConfig().contains("Patch1")) {
			getConfig().set("Patch1", false);
			saveConfig();
		}

		if (!getConfig().contains("Patch2")) {
			getConfig().set("Patch2", false);
			saveConfig();
		}

		if (!getConfig().contains("Patch3")) {
			getConfig().set("Patch3", true);
			saveConfig();
		}

		if (!getConfig().contains("Patch4")) {
			getConfig().set("Patch4", true);
			saveConfig();
		}

		if (!getConfig().contains("Patch5")) {
			getConfig().set("Patch5", true);
			saveConfig();
		}

		if (getConfig().getBoolean("Patch1") == true) {
			sendMessage(getTag() + "Resetting HQs. This may lag!", null);
			if (getConfig().getConfigurationSection("Guilds") != null) {
				for (String s : getConfig().getConfigurationSection("Guilds")
						.getKeys(false)) {
					getConfig().set(s + ".HQ", null);
					saveConfig();
				}
			}

			sendMessage(getTag() + "Resetting invites. This may lag!", null);
			getConfig().set("Pending", null);
			saveConfig();

			sendMessage(getTag() + "Patch #1 done!", null);
			getConfig().set("Patch1", false);
			saveConfig();
		}

		if (getConfig().getBoolean("Patch2") == true) {
			if (getConfig().contains("167c4ffd-2ba6-4b57-aa44-1bc705917e6d")) {
				getConfig().set("167c4ffd-2ba6-4b57-aa44-1bc705917e6d", null);
				saveConfig();
			}

			if (getConfig().contains("2fd0b806-f415-4004-b1b3-580c1ab38a37")) {
				getConfig().set("2fd0b806-f415-4004-b1b3-580c1ab38a37", null);
				saveConfig();
			}

			sendMessage(getTag() + "Patch #2 done!", null);
			getConfig().set("Patch2", false);
			saveConfig();
		}

		if (getConfig().getBoolean("Patch3") == true) {
			Bukkit.getConsoleSender()
					.sendMessage(ChatColor.translateAlternateColorCodes('&', tag
							+ "&d&lUpdating permissions for plots. This may take a while"));
			if (getConfig().contains("Guilds")) {
				ConfigurationSection guilds = getConfig()
						.getConfigurationSection("Guilds");
				Set<String> keysg = guilds.getKeys(false);

				ConfigurationSection ranks;
				Set<String> keysr;

				for (String s : keysg) {

					if (s.equals("Vongola")) {
						getConfig().set("Guilds.Vongola", null);
						saveConfig();
					}

					if (getConfig().contains("Guilds." + s + ".Plot")) {
						getConfig().set("Guilds." + s + ".Plot", null);
					}

					Bukkit.getConsoleSender()
							.sendMessage(ChatColor.DARK_PURPLE + " ");
					ranks = getConfig()
							.getConfigurationSection("Guilds." + s + ".Ranks");
					keysr = ranks.getKeys(false);
					for (String s2 : keysr) {
						s2 = ChatColor.stripColor(s2);

						if (s2.equals("Leader")) {
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotCreate", true);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotDelete", true);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotReset", true);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotHome", true);
							saveConfig();
						} else {
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotCreate", false);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotDelete", false);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotReset", false);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotHome", false);
							saveConfig();
						}
						Bukkit.getConsoleSender()
								.sendMessage(ChatColor
										.translateAlternateColorCodes('&',
												tag + "&dUpdating permissions for guild: &a"
														+ s + " &d. "
														+ "Rank: &6" + s2));
					}
				}
			}

			sendMessage(getTag() + "Patch #3 done!", null);
			getConfig().set("Patch3", false);
			saveConfig();
		}

		if (getConfig().getBoolean("Patch4") == true) {
			Bukkit.getConsoleSender()
					.sendMessage(ChatColor.translateAlternateColorCodes('&', tag
							+ "&d&lUpdating permissions for plots. This may take a while"));
			if (getConfig().contains("Guilds")) {
				ConfigurationSection guilds = getConfig()
						.getConfigurationSection("Guilds");
				Set<String> keysg = guilds.getKeys(false);

				ConfigurationSection ranks;
				Set<String> keysr;

				for (String s : keysg) {
					Bukkit.getConsoleSender()
							.sendMessage(ChatColor.DARK_PURPLE + " ");
					ranks = getConfig()
							.getConfigurationSection("Guilds." + s + ".Ranks");
					keysr = ranks.getKeys(false);
					for (String s2 : keysr) {
						s2 = ChatColor.stripColor(s2);

						if (s2.equals("Leader")) {
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotCreate", true);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotDelete", true);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotReset", true);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotHome", true);
							saveConfig();
						} else {
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotCreate", false);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotDelete", false);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotReset", false);
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PlotHome", false);
							saveConfig();
						}

						Bukkit.getConsoleSender()
								.sendMessage(ChatColor
										.translateAlternateColorCodes('&',
												tag + "&dUpdating permissions for guild: &a"
														+ s + " &d. "
														+ "Rank: &6" + s2));
					}
				}
			}

			sendMessage(getTag() + "Patch #4 done!", null);
			getConfig().set("Patch4", false);
			saveConfig();
		}

		if (getConfig().getBoolean("Patch5") == true) {
			Bukkit.getConsoleSender()
					.sendMessage(ChatColor.translateAlternateColorCodes('&', tag
							+ "&d&lUpdating permissions for pvp permissions. This may take a while"));
			if (getConfig().contains("Guilds")) {
				ConfigurationSection guilds = getConfig()
						.getConfigurationSection("Guilds");
				Set<String> keysg = guilds.getKeys(false);

				ConfigurationSection ranks;
				Set<String> keysr;

				for (String s : keysg) {
					Bukkit.getConsoleSender()
							.sendMessage(ChatColor.DARK_PURPLE + " ");
					getConfig().set("Guilds." + s + ".Pvp", false);
					
					if(getConfig()
							.getConfigurationSection("Guilds." + s + ".Ranks") == null) {
						break;
					}
					
					ranks = getConfig()
							.getConfigurationSection("Guilds." + s + ".Ranks");
					keysr = ranks.getKeys(false);
					for (String s2 : keysr) {
						s2 = ChatColor.stripColor(s2);

						if (s2.equals("Leader")) {
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PvpToggle", true);
							saveConfig();
						} else {
							getConfig().set("Guilds." + s + ".Ranks." + s2
									+ ".PvpToggle", false);
							saveConfig();
						}

						Bukkit.getConsoleSender()
								.sendMessage(ChatColor
										.translateAlternateColorCodes('&',
												tag + "&dUpdating permissions for guild: &a"
														+ s + " &d. "
														+ "Rank: &6" + s2));
					}
				}
			}

			sendMessage(getTag() + "Patch #5 done!", null);
			getConfig().set("Patch5", false);
			saveConfig();
		}

		String value = this.getConfig().getString("RewardDelay");
		long newValue = Long.parseLong(value);

		checker = new SignListener.ChestTimer(this).runTaskTimer(this,
				newValue * 60 * 20, newValue * 60 * 20);

		/*
		if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			wgPlugin = (WorldGuardPlugin) Bukkit.getPluginManager()
					.getPlugin("WorldGuard");
			sendMessage(getTag() + "Hooked Onto WorldGuard", null);
		}
		*/

		if (!setupEconomy()) {
			sendMessage(getTag() + "Disabled due to no Vault dependency found!",
					null);
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		List<ItemStack> list = (List<ItemStack>) RpgGuilds.plugin.getConfig()
				.getList("Rewards");
		ItemStack[] items = new ItemStack[list.size()];
		if (items != null) {
			GuildHandler.rewards = list.toArray(items);
		}

		SignListener.ESignMethods.load();
	}

	@Override
	public void onDisable() {
		SignListener.ESignMethods.save();
		checker = null;

		if (GuildHandler.rewards != null) {
			RpgGuilds.plugin.getConfig().set("Rewards", GuildHandler.rewards);
		} else {
			RpgGuilds.plugin.getConfig().set("Rewards",
					new ArrayList<ItemStack>());
		}

		RpgGuilds.plugin.saveConfig();

		if (!GuildHandler.requests.isEmpty()) {
			for (InviteRequests ir : GuildHandler.requests) {
				if (ir != null) {
					ir.cut();
				}
			}
		}

		sendMessage(getTag() + "RpgGuilds has been Disabled!", null);
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (command.getName().equalsIgnoreCase("gbank")) {
			if (!(sender instanceof Player)) {
				sendMessage(
						getTag() + "&CError, you can only do this command in-game.",
						null);
				return true;
			}
			Player player = (Player) sender;
			gbanks.add(player.getUniqueId());
			return BankCommands.bankCommands(sender, args);
		}
		return true;
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}

		econ = (Economy) rsp.getProvider();
		return econ != null;
	}

	public void sendMessage(String message, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(
					ChatColor.translateAlternateColorCodes('&', message));
		} else {
			player.sendMessage(
					ChatColor.translateAlternateColorCodes('&', message));
		}
	}

	public String getTag() {
		return tag;
	}

	@EventHandler
	public void guildBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();

		if (player.getWorld().getName().equals("plots")) {
			if (!getConfig().contains(player.getUniqueId().toString())) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&CYou must be in a guild to do this"));
				event.setCancelled(true);
			} else {
				String guild = getConfig().getString(
						player.getUniqueId().toString() + ".Guild.Name");
				Location loc = event.getBlock().getLocation();

				VectorUtil vec = new VectorUtil(
						new Location(Bukkit.getWorld("plots"),
								getConfig().getInt(
										"Guilds." + guild + ".Plot.1.X"),
						getConfig().getInt("Guilds." + guild + ".Plot.1.Y"),
						getConfig().getInt("Guilds." + guild + ".Plot.1.Z"))
								.toVector(),
						new Location(Bukkit.getWorld("plots"),
								getConfig().getInt(
										"Guilds." + guild + ".Plot.2.X"),
								256,
								getConfig().getInt(
										"Guilds." + guild + ".Plot.2.Z"))
												.toVector());

				if (player.hasPermission("guilds.plotbypass")) {
					event.setCancelled(false);
					return;
				}

				if (!vec.contains(loc)) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&CYou cannot build outside your plot"));
					event.setCancelled(true);
				} else {
					event.setCancelled(false);
				}
			}
		}

		if (getConfig().getBoolean("No Build")) {

			if (!getConfig().isConfigurationSection("Guilds")) {
				return;
			}

			/*
			 * for (String guild : getConfig().getConfigurationSection("Guilds")
			 * .getKeys(false)) { if (getConfig().getConfigurationSection(
			 * "Guilds." + guild + ".HQ") != null) { double x = getConfig()
			 * .getDouble("Guilds." + guild + ".HQ.X"); double y = getConfig()
			 * .getDouble("Guilds." + guild + ".HQ.Y"); double z = getConfig()
			 * .getDouble("Guilds." + guild + ".HQ.Z"); String world1 =
			 * getConfig() .getString("Guilds." + guild + ".HQ.World"); if
			 * (player.getWorld().getName().equalsIgnoreCase(world1)) { Location
			 * gloc = new Location(Bukkit.getWorld(world1), x, y, z); if
			 * (loc.distance(gloc) <= 50.0D) { if (!getConfig().contains(
			 * player.getUniqueId().toString())) { sendMessage( getTag() +
			 * "You cannot build in the headquarters of " + guild + ".",
			 * player); event.setCancelled(true); return; }
			 * 
			 * if (!getConfig() .getString(player.getUniqueId().toString() +
			 * ".Guild.Name") .equalsIgnoreCase(guild)) { sendMessage( getTag()
			 * + "You cannot build in the headquarters of " + guild + ".",
			 * player); event.setCancelled(true); return; } } } } }
			 */
		}
	}

	@EventHandler
	public void guildUse(PlayerBucketEmptyEvent event) {
		Player player = event.getPlayer();
		if (getConfig().getBoolean("No Build")) {
			Location loc = player.getLocation();
			for (String guild : getConfig().getConfigurationSection("Guilds")
					.getKeys(false)) {
				if (getConfig().getConfigurationSection(
						"Guilds." + guild + ".HQ") != null) {
					double x = getConfig()
							.getDouble("Guilds." + guild + ".HQ.X");
					double y = getConfig()
							.getDouble("Guilds." + guild + ".HQ.Y");
					double z = getConfig()
							.getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = getConfig()
							.getString("Guilds." + guild + ".HQ.World");
					if (player.getWorld().getName().equalsIgnoreCase(world1)) {
						Location gloc = new Location(Bukkit.getWorld(world1), x,
								y, z);
						if (loc.distance(gloc) <= 50.0D) {
							if (!getConfig().contains(
									player.getUniqueId().toString())) {
								sendMessage(
										getTag() + "You cannot build in the headquarters of "
												+ guild + ".",
										player);
								event.setCancelled(true);

								return;
							}

							if (!getConfig()
									.getString(player.getUniqueId().toString()
											+ ".Guild.Name")
									.equalsIgnoreCase(guild)) {
								sendMessage(
										getTag() + "You cannot build in the headquarters of "
												+ guild + ".",
										player);
								event.setCancelled(true);
								return;
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void guildUse(PlayerBucketFillEvent event) {
		Player player = event.getPlayer();
		if (getConfig().getBoolean("No Build")) {
			Location loc = player.getLocation();
			for (String guild : getConfig().getConfigurationSection("Guilds")
					.getKeys(false)) {
				if (getConfig().getConfigurationSection(
						"Guilds." + guild + ".HQ") != null) {
					double x = getConfig()
							.getDouble("Guilds." + guild + ".HQ.X");
					double y = getConfig()
							.getDouble("Guilds." + guild + ".HQ.Y");
					double z = getConfig()
							.getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = getConfig()
							.getString("Guilds." + guild + ".HQ.World");
					if (player.getWorld().getName().equalsIgnoreCase(world1)) {
						Location gloc = new Location(Bukkit.getWorld(world1), x,
								y, z);
						if (loc.distance(gloc) <= 50.0D) {
							if (!getConfig().contains(
									player.getUniqueId().toString())) {
								sendMessage(
										getTag() + "You cannot build in the headquarters of "
												+ guild + ".",
										player);
								event.setCancelled(true);
								return;
							}

							if (!getConfig()
									.getString(player.getUniqueId().toString()
											+ ".Guild.Name")
									.equalsIgnoreCase(guild)) {
								sendMessage(
										getTag() + "You cannot build in the headquarters of "
												+ guild + ".",
										player);
								event.setCancelled(true);
								return;
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void guildPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();

		if (player.getWorld().getName().equals("plots")) {
			if (!getConfig().contains(player.getUniqueId().toString())) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&CYou must be in a guild to do this"));
				event.setCancelled(true);
			} else {
				String guild = getConfig().getString(
						player.getUniqueId().toString() + ".Guild.Name");
				Location loc = event.getBlock().getLocation();

				VectorUtil vec = new VectorUtil(
						new Location(Bukkit.getWorld("plots"),
								getConfig().getInt(
										"Guilds." + guild + ".Plot.1.X"),
						getConfig().getInt("Guilds." + guild + ".Plot.1.Y"),
						getConfig().getInt("Guilds." + guild + ".Plot.1.Z"))
								.toVector(),
						new Location(Bukkit.getWorld("plots"),
								getConfig().getInt(
										"Guilds." + guild + ".Plot.2.X"),
								256,
								getConfig().getInt(
										"Guilds." + guild + ".Plot.2.Z"))
												.toVector());

				if (player.hasPermission("guilds.plotbypass")) {
					event.setCancelled(false);
					return;
				}

				if (!vec.contains(loc)) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&CYou cannot build outside your plot"));
					event.setCancelled(true);
				} else {
					event.setCancelled(false);
				}
			}
		}

		if (getConfig().getBoolean("No Build")) {
			Location loc = player.getLocation();
			for (String guild : getConfig().getConfigurationSection("Guilds")
					.getKeys(false)) {
				if (getConfig().getConfigurationSection(
						"Guilds." + guild + ".HQ") != null) {
					double x = getConfig()
							.getDouble("Guilds." + guild + ".HQ.X");
					double y = getConfig()
							.getDouble("Guilds." + guild + ".HQ.Y");
					double z = getConfig()
							.getDouble("Guilds." + guild + ".HQ.Z");
					String world1 = getConfig()
							.getString("Guilds." + guild + ".HQ.World");
					if (player.getWorld().getName().equalsIgnoreCase(world1)) {
						Location gloc = new Location(Bukkit.getWorld(world1), x,
								y, z);
						if (loc.distance(gloc) <= 50.0D) {
							if (!getConfig().contains(
									player.getUniqueId().toString())) {
								sendMessage(
										getTag() + "You cannot build in the headquarters of "
												+ guild + ".",
										player);
								event.setCancelled(true);
								return;
							}

							if (!getConfig()
									.getString(player.getUniqueId().toString()
											+ ".Guild.Name")
									.equalsIgnoreCase(guild)) {
								sendMessage(
										getTag() + "You cannot build in the headquarters of "
												+ guild + ".",
										player);
								event.setCancelled(true);
								return;
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player killed = event.getEntity();
		if (killed.getKiller() instanceof Player) {
			Player killer = killed.getKiller();
			Integer kills = Integer.valueOf(getConfig()
					.getInt("Kills." + killer.getUniqueId().toString()));
			kills = Integer.valueOf(kills.intValue() + 1);
			getConfig().set("Kills." + killer.getUniqueId().toString(), kills);
			saveConfig();
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (getConfig().contains(player.getUniqueId().toString())) {
			String guildn = getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String rank = null;
			for (String key : getConfig()
					.getConfigurationSection("Guilds." + guildn + ".Ranks")
					.getKeys(false)) {
				if (event.getInventory().getTitle().contains(key)) {
					rank = key;
				}
			}

			if (rank != null) {
				event.setCancelled(true);
				event.setResult(Event.Result.DENY);
				ItemStack item = event.getCurrentItem();
				if ((item == null) || (item.getTypeId() == 0)) {
					return;
				}

				ItemMeta im = event.getCurrentItem().getItemMeta();
				if (im.hasDisplayName()) {
					String iname = im.getDisplayName();
					Short green = Short.valueOf((short) 5);
					Short red = Short.valueOf((short) 14);
					if (!getConfig().getBoolean("Guilds." + guildn + ".Ranks."
							+ rank + "." + iname)) {
						getConfig().set("Guilds." + guildn + ".Ranks." + rank
								+ "." + iname, Boolean.valueOf(true));
						ArrayList<String> lore = new ArrayList();
						lore.add("True");
						im.setLore(lore);
						item.setItemMeta(im);
						item.setDurability(green.shortValue());
						saveConfig();
					} else {
						getConfig().set("Guilds." + guildn + ".Ranks." + rank
								+ "." + iname, Boolean.valueOf(false));
						ArrayList<String> lore = new ArrayList();
						lore.add("False");
						im.setLore(lore);
						item.setItemMeta(im);
						item.setDurability(red.shortValue());
						saveConfig();
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDmg(EntityDamageByEntityEvent event) {
		try {
			if (event.getEntity() instanceof Player) {
				Player a = (Player) event.getEntity();
				Player b = null;
				if ((event.getDamager() instanceof Player)) {
					b = (Player) event.getDamager();
				} else if (((event.getDamager() instanceof Arrow))
						&& ((((Arrow) event.getDamager())
								.getShooter() instanceof Player))) {
					b = (Player) ((Arrow) event.getDamager()).getShooter();
				} else {
					return;
				}

				if ((b != null)
						&& (getConfig().contains(a.getUniqueId().toString()))
						&& (getConfig().contains(b.getUniqueId().toString()))) {
					String guild = getConfig().getString(
							a.getUniqueId().toString() + ".Guild.Name");
					String guild1 = getConfig().getString(
							b.getUniqueId().toString() + ".Guild.Name");
					if (guild.equalsIgnoreCase(guild1)) {
						if (getConfig().getBoolean(
								"Guilds." + guild + ".Pvp") == false) {
							event.setCancelled(true);
						} else {
							event.setCancelled(false);
						}
					}
				}

				if (!getConfig().contains(b.getUniqueId().toString())) {
					return;
				}

				String guildn = getConfig()
						.getString(b.getUniqueId().toString() + ".Guild.Name");
				String fGuildn = null;
				if (getConfig()
						.getStringList("Guilds." + guildn + ".Ally") != null) {
					List<String> allies = getConfig()
							.getStringList("Guilds." + guildn + ".Ally");
					if (getConfig().getString(a.getUniqueId().toString()
							+ ".Guild.Name") != null) {
						fGuildn = getConfig().getString(
								a.getUniqueId().toString() + ".Guild.Name");
						if (allies.contains(fGuildn)) {
							event.setCancelled(true);
						}
					}
				}
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String guild = getConfig()
				.getString(player.getUniqueId().toString() + ".Guild.Name");
		String tag = getConfig().getString("Guilds." + guild + ".Tag");
		if (getConfig().getBoolean("Chat")) {
			if (tag != null) {
				event.setMessage(ChatColor.translateAlternateColorCodes('&',
						"&F[&A" + tag + "&F] ") + event.getMessage());
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		Integer newkills = Integer.valueOf(0);
		if (getConfig().getConfigurationSection(
				"Kills." + player.getUniqueId().toString()) == null) {
			getConfig().set("Kills." + player.getUniqueId().toString(),
					newkills);
		}

		if (getConfig().contains(player.getUniqueId().toString())) {
			String guild = getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			for (String key : getConfig()
					.getConfigurationSection("Guilds." + guild + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key) != null) {
					if (Bukkit.getPlayer(key).getUniqueId().toString() == player
							.getUniqueId().toString()) {
						if (getConfig().getString(
								"Guilds." + guild + ".Gmotd") != null) {
							sendMessage(
									getConfig().getString(
											"Guilds." + guild + ".Gmotd"),
									player);
						} else {
							sendMessage("You are a part of " + guild, player);
						}
					} else {
						Player p = Bukkit.getPlayer(key);
						sendMessage(
								"&3" + player.getName() + "&2 Has come online!",
								p);
					}
				}
			}
		}
	}

	@EventHandler
	public void onKick(PlayerKickEvent event2) {
		event2.setLeaveMessage(null);
	}

	public void teleport(Player p, Player p2) {
		final Player player = p;
		final Player player2 = p2;
		final Location loc = player.getLocation();
		final Location Loc = player2.getLocation();
		sendMessage("About to teleport, don't move!", player);
		sendMessage("About to teleport, don't move!", player2);
		new BukkitRunnable() {
			int count = 8;

			public void run() {
				sendMessage("Wait " + this.count + " Seconds.", player);
				sendMessage("Wait " + this.count + " Seconds.", player2);
				this.count -= 1;
				if (player.getLocation().getX() != loc.getX()) {
					sendMessage("Cancelled teleport, don't move!", player);
					sendMessage("Cancelled teleport, don't move!", player2);
					cancel();
				}

				if (player.getLocation().getZ() != loc.getZ()) {
					sendMessage("Cancelled teleport, don't move!", player);
					sendMessage("Cancelled teleport, don't move!", player2);
					cancel();
				}

				if (player2.getLocation().getX() != Loc.getX()) {
					sendMessage("Cancelled teleport, don't move!", player);
					sendMessage("Cancelled teleport, don't move!", player2);
					cancel();
				}

				if (player2.getLocation().getZ() != Loc.getZ()) {
					sendMessage("Cancelled teleport, don't move!", player);
					sendMessage("Cancelled teleport, don't move!", player2);
					cancel();
				}

				if (this.count == 0) {
					player2.teleport(loc);
					cancel();
				}
			}
		}.runTaskTimer(plugin, 20L, 20L);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event1) {
		Player player = event1.getPlayer();
		event1.setQuitMessage(null);
		if (getConfig().contains(player.getUniqueId().toString())) {
			String guild = getConfig()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			for (String key : getConfig()
					.getConfigurationSection("Guilds." + guild + ".Players")
					.getKeys(false)) {
				if (Bukkit.getPlayer(key) != null) {
					Player p = Bukkit.getPlayer(key);
					sendMessage(
							"&3" + player.getName() + "&2 Has gone offline!",
							p);
				}
			}
		}
	}
}