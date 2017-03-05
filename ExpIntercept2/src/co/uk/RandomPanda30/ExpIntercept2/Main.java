package co.uk.RandomPanda30.ExpIntercept2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener, CommandExecutor {

	public Main plugin;
	public PluginDescriptionFile pdfFile;

	public static String tag = "&F[&BExpIntercept&F] ";

	public static Booster current = null;
	/*
	 * Don't forget the log stuff Get list on login Dump stuff Events
	 */

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		sendMessage(tag + "&AIs being enabled", null);

		getCommand("booster").setExecutor(this);

		getServer().getPluginManager().registerEvents(this, this);

		saveConfig();

		if (getConfig().contains("Dump")) {

			String name = getConfig().getString("Dump.Name");
			UUID uuid = UUID.fromString(getConfig().getString("Dump.UUID"));

			long start = getConfig().getLong("Dump.ST");
			long end = getConfig().getLong("Dump.ET");
			long dump = getConfig().getLong("Dump.DUMP");
			long duration = getConfig().getLong("Dump.D");

			long a = dump - start; // Get the time already elapsed
			long b = duration - a; // Take away the elasped time from the
									// duration
			long c = end + b; // Add the remaining duration onto the end time

			int perc = getConfig().getInt("Dump.P");

			Main.current = new Booster(this, name, uuid, start, c, duration,
					perc);

			getConfig().set("Dump", null);
			saveConfig();
		}
	}

	@Override
	public void onDisable() {
		sendMessage(tag + "&CIs being disabled", null);

		if (current != null) {
			current.dump();
		}

		plugin = null;
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		int xp = event.getExpToDrop();
		if (current != null) {
			int divdedNo = current.getPercentage();
			if (!(current.getPercentage() < 0)) {
				event.setExpToDrop((((divdedNo / 100) * xp) + xp) * 2);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setExpToDrop((int) (xp - (newDiv * xp)));
			}
		}
	}

	@EventHandler
	public void onExpBottle(ExpBottleEvent event) {
		int xp = event.getExperience();
		if (current != null) {
			int divdedNo = current.getPercentage();
			if (!(current.getPercentage() < 0)) {
				event.setExperience((((divdedNo / 100) * xp) + xp) * 2);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setExperience((int) (xp - (newDiv * xp)));
			}
		}
	}

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		int xp = event.getDroppedExp();
		if (current != null) {
			int divdedNo = current.getPercentage();
			if (!(current.getPercentage() < 0)) {
				event.setDroppedExp((((divdedNo / 100) * xp) + xp) * 2);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setDroppedExp((int) (xp - (newDiv * xp)));
			}
		}
	}

	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent event) {
		int xp = event.getAmount();
		if (current != null) {
			int divdedNo = current.getPercentage();
			if (!(current.getPercentage() < 0)) {
				event.setAmount((((divdedNo / 100) * xp) + xp) * 2);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setAmount((int) (xp - (newDiv * xp)));
			}
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack is = player.getItemInHand();

		if (is == null) {
			return;
		}

		if (is.getType().equals(Material.AIR)) {
			return;
		}

		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
				|| event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (is.getItemMeta().hasDisplayName()
					&& is.getItemMeta().getDisplayName()
							.contains("SAO XP Booster")
					&& is.getType().equals(Material.ARROW)) {
				if (current != null) {
					// Refuse
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&C&lYou cannot activate a booster when there is already one active"));
				} else {
					List<String> lores = is.getItemMeta().getLore();
					String time = ChatColor.stripColor(lores.get(7))
							.replace("Time: ", "");

					String perc = ChatColor.stripColor(lores.get(8));
					perc = perc.replace("Boost: ", "");
					perc = perc.replace("%", "");

					String[] t = { time };

					long ft = 0;
					long duration = 0;

					long days = 0;
					long hours = 0;
					long minutes = 0;
					long seconds = 0;

					if (!time.contains("d") && !time.contains("h")
							&& !time.contains("m") && !time.contains("s")) {
						time = "0";
					} else {
						if (time.contains("d")) {
							t = t[0].split("d");
							days = Integer.parseInt(t[0]);
							if (t.length == 2) {
								t[0] = t[1];
							}
						}

						if (time.contains("h")) {
							t = t[0].split("h");
							hours = Integer.parseInt(t[0]);
							if (t.length == 2) {
								t[0] = t[1];
							}
						}

						if (time.contains("m")) {
							t = t[0].split("m");
							minutes = Integer.parseInt(t[0]);
							if (t.length == 2) {
								t[0] = t[1];
							}
						}

						if (time.contains("s")) {
							t = t[0].split("s");
							seconds = Integer.parseInt(t[0]);
						}

						if (!time.equals("0")) {
							ft += Calendar.getInstance().getTimeInMillis()
									+ (days * 1000 * 60 * 60 * 24)
									+ (hours * 1000 * 60 * 60)
									+ (minutes * 1000 * 60) + (seconds * 1000);
							duration += (days * 1000 * 60 * 60 * 24)
									+ (hours * 1000 * 60 * 60)
									+ (minutes * 1000 * 60) + (seconds * 1000);
						}

						current = new Booster(this, player.getName(),
								player.getUniqueId(),
								Calendar.getInstance().getTimeInMillis(), ft,
								duration, Integer.parseInt(perc));
					}

					if (is.getAmount() == 1) {
						player.getInventory().remove(is);
					} else {
						player.getInventory().remove(is);

						is.setAmount(is.getAmount() - 1);
						player.getInventory().addItem(is);
					}

					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&A&lYour booster has been activated!"));
				}
			}
		}
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		if (getConfig().contains("Dumps")) {
			if (getConfig().contains("Dumps." + uuid.toString())) {
				HashMap<ItemStack, Integer> i = new HashMap<>();
				for (String s : getConfig()
						.getConfigurationSection("Dumps." + uuid.toString())
						.getKeys(false)) {
					String[] split = s.split("=");
					String time = split[0];

					int percentage = Integer.parseInt(split[1]);
					int amount = getConfig()
							.getInt("Dumps." + uuid.toString() + s);

					ItemStack is = new ItemStack(Material.ARROW, 1);
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(ChatColor.translateAlternateColorCodes(
							'&', "&B&lSAO XP Booster"));

					ArrayList<String> lores = new ArrayList<>();
					lores.add(" ");
					lores.add(ChatColor.translateAlternateColorCodes('&',
							"&A&lSome say right clicking this item"));
					lores.add(ChatColor.translateAlternateColorCodes('&',
							"&A&lwill bring joy and XP to many people."));
					lores.add("  ");
					lores.add(ChatColor.translateAlternateColorCodes('&',
							"&c&lReport any issues to an admin"));
					lores.add(ChatColor.translateAlternateColorCodes('&',
							"&c&land to RandomPanda30"));
					lores.add("   ");
					lores.add(ChatColor.translateAlternateColorCodes('&',
							"&6&lTime: &c" + time));
					lores.add(
							ChatColor.translateAlternateColorCodes('&',
									"&6&lBoost: &c"
											+ Integer.toString(percentage)
											+ "&c%"));

					im.setLore(lores);
					is.setItemMeta(im);

					i.put(is, amount);
				}

				if (!(player.getInventory().firstEmpty() == -1)) {
					int space = 0;

					for (ItemStack content : player.getInventory()
							.getContents()) {
						if (content == null) {
							space++;
						}
					}

					if (space >= i.size()) {
						for (Entry<ItemStack, Integer> ii : i.entrySet()) {
							ItemStack newi = ii.getKey();
							newi.setAmount(ii.getValue());
							player.getInventory().addItem(newi);
						}
					} else {
						player.sendMessage(
								ChatColor.translateAlternateColorCodes('&',
										"&CCould not give your boosters as your inventory is full. Make some room and relog!"));
					}
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CCould not give your boosters as your inventory is full. Make some room and relog!"));
				}
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&CThis command can only be done by console"));
			return true;
		}
		if (args.length != 3) {
			if (!(sender instanceof Player)) {
				sendMessage(
						tag + "&CError, wrong usage. &ACorrect syntax: &F/booster <Player name> <Percentage> <Time e.g 2d6m>",
						null);
			} else {
				sendMessage(
						tag + "&CError, wrong usage. &ACorrect syntax: &F/booster <Player name> <Percentage> <Time e.g 2d6m>",
						(Player) sender);
			}
			return true;
		}

		if (Bukkit.getPlayer(args[0]) != null
				&& Bukkit.getPlayer(args[0]).isOnline()) {
			Player player = Bukkit.getPlayer(args[0]);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&A&lHere is your booster. Use it wisely"));

			ItemStack is = new ItemStack(Material.ARROW, 1);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
					"&B&lSAO XP Booster"));

			ArrayList<String> lores = new ArrayList<>();
			lores.add(" ");
			lores.add(ChatColor.translateAlternateColorCodes('&',
					"&A&lSome say right clicking this item"));
			lores.add(ChatColor.translateAlternateColorCodes('&',
					"&A&lwill bring joy and XP to many people."));
			lores.add("  ");
			lores.add(ChatColor.translateAlternateColorCodes('&',
					"&c&lReport any issues to an admin"));
			lores.add(ChatColor.translateAlternateColorCodes('&',
					"&c&land to RandomPanda30"));
			lores.add("   ");
			lores.add(ChatColor.translateAlternateColorCodes('&',
					"&6&lTime: &c" + args[2]));
			lores.add(ChatColor.translateAlternateColorCodes('&',
					"&6&lBoost: &c" + args[1]));

			im.setLore(lores);
			is.setItemMeta(im);

			if (player.getInventory().firstEmpty() == -1) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&C&lError, there is not enough space in your inventory. Please make some room and relog!"));
				dumpToConfig(player.getUniqueId(), args[2],
						Integer.parseInt(args[1].replace("%", "")));
			} else {
				player.getInventory().addItem(is);
			}
		} else {
			OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
			dumpToConfig(p.getUniqueId(), args[2],
					Integer.parseInt(args[1].replace("%", "")));
		}
		return true;
	}

	public void dumpToConfig(UUID uuid, String time, int percentage) {
		if (!getConfig().contains("Dumps." + uuid.toString())) {
			getConfig().set("Dumps." + uuid + "." + time + "=" + percentage, 1);
			saveConfig();
		} else {
			if (getConfig().contains("Dumps." + uuid.toString() + "." + time
					+ "=" + percentage)) {
				int i = getConfig().getInt("Dumps." + uuid.toString() + "."
						+ time + "=" + percentage);
				getConfig().set(
						"Dumps." + uuid.toString() + "." + time + "=" + time,
						i + 1);
				saveConfig();
			} else {
				getConfig().set("Dumps." + uuid + "." + time + "=" + percentage,
						1);
				saveConfig();
			}
		}
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

	// Make a log of all thge done commands and players
	public static class Booster {

		private Main plugin;

		private String name;
		private UUID uuid;

		private long startTime;
		private long endTime;
		private long duration;

		private int percentage;

		private int id;

		public Booster (Main plugin, String name, UUID uuid, long startTime,
				long endTime, long duration, int percentage) {

			this.plugin = plugin;

			this.name = name;
			this.uuid = uuid;

			this.startTime = startTime;
			this.endTime = endTime;
			this.duration = duration;

			this.percentage = percentage;
			start();
		}

		public void start() {
			Main.current = this;
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
					"&B&l" + name + "'s booster has been activated"));
			id = Bukkit.getServer().getScheduler()
					.scheduleSyncRepeatingTask(plugin, new Runnable() {
						@Override
						public void run() {
							if (Calendar.getInstance()
									.getTimeInMillis() > endTime) {
								end();
							}
						}
					}, 0L, 100L);
		}

		public void end() {
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
					"&C&l" + name + "'s booster has expired"));
			Bukkit.getScheduler().cancelTask(id);
			current = null;
		}

		public void dump() {
			plugin.getConfig().set("Dump.Name", name);
			plugin.getConfig().set("Dump.UUID", uuid.toString());
			plugin.getConfig().set("Dump.ST", startTime);
			plugin.getConfig().set("Dump.DUMP",
					Calendar.getInstance().getTimeInMillis());
			plugin.getConfig().set("Dump.ET", endTime);
			plugin.getConfig().set("Dump.D", duration);
			plugin.getConfig().set("Dump.P", percentage);
			plugin.saveConfig();
		}

		public int getPercentage() {
			return percentage;
		}

	}

}
