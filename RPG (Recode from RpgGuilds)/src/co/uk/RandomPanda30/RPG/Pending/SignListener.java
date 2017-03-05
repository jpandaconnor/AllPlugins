package co.uk.RandomPanda30.RPG.Pending;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Handlers.GuildH;

public class SignListener implements Listener {

	public static RPG plugin;

	public SignListener (RPG plugin) {
		SignListener.plugin = plugin;
	}

	public static ArrayList<ESign> signs = new ArrayList<ESign>();

	public enum Type {
		CAPTURED, UNOWNED;
	}

	public static class ChestTimer extends BukkitRunnable {

		public RPG plugin;

		public ChestTimer (RPG plugin) {
			this.plugin = plugin;
		}

		@Override
		public void run() {
			for (ESign sign : signs) {
				Location loc = sign.getLocation();
				loc.setY(loc.getY() - 1);
				if (loc.getBlock().getType().equals(Material.CHEST)) {
					Chest chest = (Chest) loc.getWorld().getBlockAt(loc)
							.getState();
					if (GuildH.rewards != null) {
						chest.getBlockInventory().addItem(GuildH.rewards);
						chest.update();
					}
				}
			}
		}
	}

	public static class ESignMethods {

		public static Location getLocation(String s) {
			return (Location) plugin.getRPGValues().getConfigC()
					.get("Signs." + s + ".location");
		}

		public static String getType(String s) {
			return (String) plugin.getRPGValues().getConfigC()
					.get("Signs." + s + ".state");
		}

		public static String getGuild(String s) {
			return (String) plugin.getRPGValues().getConfigC()
					.get("Signs." + s + ".guild");
		}

		public static ArrayList<String> getSigns() {
			if (plugin.getRPGValues().getConfigC().contains("Signs")) {
				if (plugin.getRPGValues().getConfigC()
						.getConfigurationSection("Guilds") != null) {
					if (plugin.getRPGValues().getConfigC()
							.getConfigurationSection("Signs") != null) {
						Set<String> keys = plugin.getRPGValues().getConfigC()
								.getConfigurationSection("Signs")
								.getKeys(false);
						ArrayList<String> n = new ArrayList<String>();
						if (keys != null) {
							for (String s : keys) {
								n.add(s);
							}
							return n;
						}
					}
				}
			}
			return null;
		}

		public static void load() {
			if (getSigns() != null) {
				for (String s : getSigns()) {
					ESign sign = new ESign(getLocation(s),
							getTypeFromString(getType(s)), getGuild(s), s);
					signs.add(sign);
				}
			}
		}

		public static void save() {
			for (ESign sign : signs) {
				plugin.getRPGValues()
						.getConfigC()
						.set("Signs." + compileLocation(sign.getLocation())
								+ ".location", sign.getLocation());
				plugin.getRPGValues()
						.getConfigC()
						.set("Signs." + compileLocation(sign.getLocation())
								+ ".state", sign.getType().toString());
				plugin.getRPGValues()
						.getConfigC()
						.set("Signs." + compileLocation(sign.getLocation())
								+ ".guild", sign.getGuild());
			}
			plugin.getRPGConfig().saveAllConfigs();
		}

		public static boolean isSign(Block block) {
			boolean t = false;
			switch (block.getType()) {
			case SIGN:
				t = true;
				break;
			case WALL_SIGN:
				t = true;
				break;
			case SIGN_POST:
				t = true;
				break;
			default:
				t = false;
				break;
			}
			return t;
		}
	}

	public static class ESign {

		@SuppressWarnings("unused")
		private Location location;
		private Type type;
		private String guild;
		private String oLocation;

		public ESign (Location location, Type type, String guild,
				String oLocation) {
			this.location = location;
			this.type = type;
			this.guild = guild;
			this.oLocation = oLocation;
		}

		public Location getLocation() {
			return decompileLocation(oLocation);
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public String getGuild() {
			return guild;
		}

		public void setGuild(String guild) {
			this.guild = guild;
		}
	}

	String unowned = "&F[&BUnowned&F]";
	String captured = "&F[&CCaptured&F]";

	@EventHandler
	public void onSignChangeEvent(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] lines = event.getLines();
		Location location = event.getBlock().getLocation();

		if (lines[0].equalsIgnoreCase("[sts]")) {
			if (player.hasPermission("cts.place")) {
				ESign sign = new ESign(location, Type.UNOWNED, "",
						compileLocation(location));
				signs.add(sign);
				event.setLine(0,
						ChatColor.translateAlternateColorCodes('&', unowned));
				event.setLine(1, " ");
				event.setLine(2, ChatColor.translateAlternateColorCodes('&',
						"&C&lClick to claim!"));
			} else {
				event.getBlock().breakNaturally();
			}
		}
	}

	@EventHandler
	public void onPlayerCloseInventory(InventoryCloseEvent event) {
		String name = event.getInventory().getName();

		if (name.equals("Drag and drop items into here")) {
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			for (ItemStack is : event.getInventory().getContents()) {
				if (is != null) {
					items.add(is);
				}
			}

			ItemStack[] newItems = new ItemStack[items.size()];
			GuildH.rewards = items.toArray(newItems);
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		Block block = event.getClickedBlock();
		Sign s = null;

		if (action == Action.RIGHT_CLICK_BLOCK) {
			if (ESignMethods.isSign(block)) {
				ESign newSign = null;
				for (ESign sign : signs) {
					if (block.getLocation().equals(sign.getLocation())) {
						newSign = sign;
						if (newSign.getGuild().equals("")) {
							String g = "";
							for (String guild : plugin.getRPGValues()
									.getConfigC()
									.getConfigurationSection("Guilds")
									.getKeys(false)) {
								if (plugin
										.getRPGValues()
										.getConfigC()
										.get("Guilds." + guild + ".Leader")
										.equals(player.getUniqueId().toString())
										|| plugin
												.getRPGValues()
												.getConfigC()
												.get(player.getUniqueId()
														.toString()
														+ ".Guild.Name")
												.equals(guild)) {
									g = guild;
								}
							}

							if (g.isEmpty()) {
								player.sendMessage(ChatColor
										.translateAlternateColorCodes('&',
												"&CYou are not in a guild!"));
								return;
							}

							newSign.setGuild(g);
							newSign.setType(Type.CAPTURED);

							String guildName = "&C "
									+ (g.length() >= 13 ? g.substring(0, 12)
											: g);

							s = (Sign) block.getState();
							s.setLine(0,
									ChatColor.translateAlternateColorCodes('&',
											captured));
							s.setLine(2, ChatColor
									.translateAlternateColorCodes('&',
											"&BGuild:"));
							s.setLine(3, ChatColor
									.translateAlternateColorCodes('&',
											guildName));
							s.update();

						} else {
							String g = plugin
									.getRPGValues()
									.getConfigC()
									.getString(
											player.getUniqueId().toString()
													+ ".Guild.Name");

							if (newSign.getGuild().equals(g)) {
								player.sendMessage(ChatColor
										.translateAlternateColorCodes('&',
												"&CYou already own this point!"));
								return;
							}

							if (g.isEmpty()) {
								player.sendMessage(ChatColor
										.translateAlternateColorCodes('&',
												"&CYou are not in a guild!"));
								return;
							}

							newSign.setGuild(g);
							newSign.setType(Type.CAPTURED);

							String guildName = "&C "
									+ (g.length() >= 13 ? g.substring(0, 12)
											: g);

							s = (Sign) block.getState();
							s.setLine(0,
									ChatColor.translateAlternateColorCodes('&',
											captured));
							s.setLine(2, ChatColor
									.translateAlternateColorCodes('&',
											"&BGuild:"));
							s.setLine(3, ChatColor
									.translateAlternateColorCodes('&',
											guildName));
							s.update();
						}
					}
				}
			} else if (block.getType().equals(Material.CHEST)) {
				ESign ss = null;
				for (ESign sign : signs) {
					Location loc = sign.getLocation();
					loc.setY(loc.getY() - 1);
					if (block.getLocation().equals(loc)) {
						ss = sign;
					}
				}

				if (!plugin.getRPGValues().getConfigC()
						.get(player.getUniqueId().toString() + ".Guild.Name")
						.equals(ss.getGuild())) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou cannot open this chest as it's not yours!"));
					event.setCancelled(true);
				}
			}
		}
	}

	public static Type getTypeFromString(String s) {
		Type type = null;
		switch (s) {
		case "CAPTURED":
			type = Type.CAPTURED;
			break;
		case "UNOWNED":
			type = Type.UNOWNED;
			break;
		}
		return type;
	}

	public static String compileLocation(Location location) {
		String s = location.getWorld().getName().toString() + ";"
				+ Double.toString(location.getX()) + ";"
				+ Double.toString(location.getY()) + ";"
				+ Double.toString(location.getZ());
		s = s.replace(".", "=");
		return s;
	}

	public static Location decompileLocation(String string) {
		String rawS = string.replace("=", ".");
		String[] s = rawS.split(";");
		String world = s[0];
		double x = Double.parseDouble(s[1]);
		double y = Double.parseDouble(s[2]);
		double z = Double.parseDouble(s[3]);
		return new Location(Bukkit.getWorld(world), x, y, z);
	}
}