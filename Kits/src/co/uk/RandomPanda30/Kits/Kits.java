package co.uk.RandomPanda30.Kits;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Kits extends JavaPlugin implements Listener {

	public static Kits instance;
	private static PluginDescriptionFile pdfFile;

	private static File config;
	private static FileConfiguration configC;
	private static ConfigurationSection configCS;

	private static File data;
	private static FileConfiguration dataC;
	private static ConfigurationSection dataCS;

	private StringMethods string = new StringMethods();
	private ConfigMethods configs = new ConfigMethods();
	private MySQL mysql;

	private String tag = "&F[&BKits&F] ";

	public ArrayList<UUID> alreadyTeleported = new ArrayList<UUID>();

	@Override
	public void onEnable() {
		setPlugin(this);
		setPdfFile(Kits.getPlugin().getDescription());
		configs.initConfig();
		configs.initData();

		this.getServer().getPluginManager().registerEvents(this, this);
		setMysql(new MySQL(this, (String) Kits.configC.get("MYSQL.HOST"),
				(String) Kits.configC.get("MYSQL.DATABASE"),
				(String) Kits.configC.get("MYSQL.USER"),
				(String) Kits.configC.get("MYSQL.PASSWORD"),
				(Integer) Kits.configC.get("MYSQL.PORT")));
		try {
			mysql.openConnection();
			mysql.updateSQL("USE `" + Kits.configC.get("MYSQL.DATABASE") + "`");
		} catch (ClassNotFoundException | SQLException e) {
			string.sendMessage(tag
					+ "&CSomething went wrong. Check your MySQL details again",
					null);
			return;
		}

		string.sendMessage(tag + "&AIs being enabled", null);

		getCommand("block").setExecutor(new BlockCommand());
	}

	@Override
	public void onDisable() {
		string.sendMessage(tag + "&CIs being disabled", null);

		for (String s : dataC.getConfigurationSection("").getKeys(false)) {
			dataC.set(s, null);
			try {
				dataC.save(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		setPlugin(null);
	}

	public class DefaultItems {
		public ItemStack getRed() {
			return createItem("&CRed team location", Material.WOOL, 1, 14,
					new ArrayList<String>());
		}

		public ItemStack getBlue() {
			return createItem("&4Blue team location", Material.WOOL, 1, 14,
					new ArrayList<String>());
		}
	}

	public class BlockCommand implements CommandExecutor {
		@Override
		public boolean onCommand(CommandSender sender, Command cmd,
				String label, String[] args) {
			DefaultItems di = new DefaultItems();
			ItemStack red = di.getRed();
			ItemStack blue = di.getBlue();
			if (!(sender instanceof Player)) {
				string.sendMessage(tag
						+ "&CYou cannot do this command from console", null);
				return true;
			}

			Player player = (Player) sender;
			if (player.hasPermission("kits.edit")) {
				if (args.length > 0) {
					string.sendMessage(
							tag
									+ "&CYou have done this command wrong. Please try just doing /block",
							player);
					return true;
				}

				player.getInventory().addItem(red);
				player.getInventory().addItem(blue);
				string.sendMessage(tag
						+ "&FPlace these blocks to set the location", player);
			} else {
				string.sendMessage(tag
						+ "&CYou do not have permission to do this", player);
			}
			return true;
		}
	}

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player
				&& event.getEntity() instanceof Player) {
			Player player = event.getEntity().getKiller();

			Bukkit.broadcastMessage(ChatColor.RED + player.getName());

			List<String> names = new ArrayList<String>();
			ResultSet rs;
			try {
				rs = mysql.querySQL("SELECT name FROM " + mysql.DB_NAME
						+ ".Purchases WHERE uuid = '"
						+ player.getUniqueId().toString() + "'");
				while (rs.next()) {
					names.add(rs.getString("name"));
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			int ingots4 = 0;
			if (names.contains("28")) {
				ingots4 = 28;
			}

			if (names.contains("29")) {
				ingots4 = 29;
			}

			if (names.contains("30")) {
				ingots4 = 30;
			}

			switch (ingots4) {
			case 28:
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						80, 0), true);
				break;
			case 29:
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						160, 0), true);
				break;
			case 30:
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						200, 0), true);
				break;
			}

			int ingots5 = 0;
			if (names.contains("31")) {
				ingots5 = 31;
			}

			if (names.contains("32")) {
				ingots5 = 32;
			}

			if (names.contains("33")) {
				ingots5 = 33;
			}

			switch (ingots5) {
			case 31:
				player.addPotionEffect(new PotionEffect(
						PotionEffectType.INCREASE_DAMAGE, 60, 1), true);
				break;
			case 32:
				player.addPotionEffect(new PotionEffect(
						PotionEffectType.INCREASE_DAMAGE, 100, 1), true);
				break;
			case 33:
				player.addPotionEffect(new PotionEffect(
						PotionEffectType.INCREASE_DAMAGE, 140, 1), true);
				break;
			}

			int ingots6 = 0;
			if (names.contains("34")) {
				ingots6 = 34;
			}

			if (names.contains("35")) {
				ingots6 = 35;

			}

			if (names.contains("36")) {
				ingots6 = 36;

			}
			
			switch (ingots6) {
			case 34:
				player.addPotionEffect(new PotionEffect(
						PotionEffectType.REGENERATION, 40, 1), true);
				break;
			case 35:
				player.addPotionEffect(new PotionEffect(
						PotionEffectType.REGENERATION, 80, 1), true);
				break;
			case 36:
				player.addPotionEffect(new PotionEffect(
						PotionEffectType.REGENERATION, 100, 1), true);
				break;
			}
		}
	}

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Location location = block.getLocation();

		DefaultItems di = new DefaultItems();

		ItemStack red = di.getRed();
		ItemStack blue = di.getBlue();

		if (player.getItemInHand() != null) {
			if (player.getItemInHand().equals(red)) {
				block.setType(Material.AIR);
				if (player.getGameMode().equals(GameMode.CREATIVE)) {
					player.getInventory().remove(red);
				}

				configC.set("Red", string.compileLocation(location));
				try {
					configC.save(config);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (player.getItemInHand().equals(blue)) {
				block.setType(Material.AIR);
				if (player.getGameMode().equals(GameMode.CREATIVE)) {
					player.getInventory().remove(blue);
				}

				configC.set("Blue", string.compileLocation(location));
				try {
					configC.save(config);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "null" })
	@EventHandler
	public void onPlayerTeleportEvent(PlayerTeleportEvent event) {

		final Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		Location location = event.getTo();

		Location newLocation = new Location(location.getWorld(),
				Math.floor(location.getX()), Math.floor(location.getY()),
				Math.floor(location.getZ() + 1));

		Location red;
		Location blue;

		if ((String) configC.get("Red") != null) {
			red = string.decompileLocation((String) configC.get("Red"));
		} else {
			red = location.getWorld().getSpawnLocation();
		}

		if ((String) configC.get("Blue") != null) {
			blue = string.decompileLocation((String) configC.get("Blue"));
		} else {
			blue = location.getWorld().getSpawnLocation();
		}

		if (newLocation.equals(red) || newLocation.equals(blue)) {
			if (!alreadyTeleported.contains(uuid)) {
				if (!dataC.contains(uuid.toString())) {
					alreadyTeleported.add(uuid);
					List<String> names = new ArrayList<String>();
					ResultSet rs;
					try {
						rs = mysql.querySQL("SELECT name FROM " + mysql.DB_NAME
								+ ".Purchases WHERE uuid = '" + uuid.toString()
								+ "'");
						while (rs.next()) {
							names.add(rs.getString("name"));
						}
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}

					final List<String> finalNames = names;

					Bukkit.getServer()
							.getScheduler()
							.scheduleSyncDelayedTask(getPlugin(),
									new Runnable() {
										@Override
										public void run() {
											int ingots = 0;
											if (finalNames.contains("1")) {
												ingots = 1;
											}

											if (finalNames.contains("2")) {
												ingots = 2;
											}

											if (finalNames.contains("3")) {
												ingots = 3;
											}

											if (finalNames.contains("4")) {
												ingots = 4;
											}

											if (finalNames.contains("5")) {
												ingots = 5;
											}

											if (finalNames.contains("6")) {
												ingots = 6;
											}

											if (finalNames.contains("7")) {
												ingots = 7;
											}

											if (finalNames.contains("8")) {
												ingots = 8;
											}

											if (finalNames.contains("9")) {
												ingots = 9;
											}

											switch (ingots) {
											case 1:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		10,
																		0,
																		new ArrayList<String>()));
												break;
											case 2:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		15,
																		0,
																		new ArrayList<String>()));
												break;
											case 3:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		20,
																		0,
																		new ArrayList<String>()));
												break;
											case 4:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		25,
																		0,
																		new ArrayList<String>()));
												break;
											case 5:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		30,
																		0,
																		new ArrayList<String>()));
												break;
											case 6:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		35,
																		0,
																		new ArrayList<String>()));
												break;
											case 7:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		40,
																		0,
																		new ArrayList<String>()));
												break;
											case 8:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		45,
																		0,
																		new ArrayList<String>()));
												break;
											case 9:
												player.getInventory()
														.addItem(
																createItem(
																		"Bronze ingots",
																		Material.CLAY_BRICK,
																		50,
																		0,
																		new ArrayList<String>()));
												break;
											}

											int ingots2 = 0;
											if (finalNames.contains("10")) {
												ingots2 = 10;
											}

											if (finalNames.contains("11")) {
												ingots2 = 11;
											}

											if (finalNames.contains("12")) {
												ingots2 = 12;
											}

											if (finalNames.contains("13")) {
												ingots2 = 13;
											}

											if (finalNames.contains("14")) {
												ingots2 = 14;
											}

											if (finalNames.contains("15")) {
												ingots2 = 15;
											}

											if (finalNames.contains("16")) {
												ingots2 = 16;
											}

											if (finalNames.contains("17")) {
												ingots2 = 17;
											}

											if (finalNames.contains("18")) {
												ingots2 = 18;
											}

											switch (ingots2) {
											case 10:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		2,
																		0,
																		new ArrayList<String>()));
												break;
											case 11:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		4,
																		0,
																		new ArrayList<String>()));
												break;
											case 12:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		6,
																		0,
																		new ArrayList<String>()));
												break;
											case 13:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		8,
																		0,
																		new ArrayList<String>()));
												break;
											case 14:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		10,
																		0,
																		new ArrayList<String>()));
												break;
											case 15:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		12,
																		0,
																		new ArrayList<String>()));
												break;
											case 16:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		14,
																		0,
																		new ArrayList<String>()));
												break;
											case 17:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		16,
																		0,
																		new ArrayList<String>()));
												break;
											case 18:
												player.getInventory()
														.addItem(
																createItem(
																		"Iron bars",
																		Material.IRON_INGOT,
																		18,
																		0,
																		new ArrayList<String>()));
												break;
											}

											int ingots3 = 0;
											if (finalNames.contains("19")) {
												ingots3 = 19;
											}

											if (finalNames.contains("20")) {
												ingots3 = 20;
											}

											if (finalNames.contains("21")) {
												ingots3 = 21;
											}

											if (finalNames.contains("22")) {
												ingots3 = 22;
											}

											if (finalNames.contains("23")) {
												ingots3 = 23;
											}

											if (finalNames.contains("24")) {
												ingots3 = 24;
											}

											if (finalNames.contains("25")) {
												ingots3 = 25;
											}

											if (finalNames.contains("26")) {
												ingots3 = 26;
											}

											if (finalNames.contains("27")) {
												ingots3 = 27;
											}

											switch (ingots3) {
											case 19:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		1,
																		0,
																		new ArrayList<String>()));
												break;
											case 20:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		2,
																		0,
																		new ArrayList<String>()));
												break;
											case 21:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		3,
																		0,
																		new ArrayList<String>()));
												break;
											case 22:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		4,
																		0,
																		new ArrayList<String>()));
												break;
											case 23:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		5,
																		0,
																		new ArrayList<String>()));
												break;
											case 24:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		6,
																		0,
																		new ArrayList<String>()));
												break;
											case 25:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		7,
																		0,
																		new ArrayList<String>()));
												break;
											case 26:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		8,
																		0,
																		new ArrayList<String>()));
												break;
											case 27:
												player.getInventory()
														.addItem(
																createItem(
																		"Gold bars",
																		Material.GOLD_INGOT,
																		9,
																		0,
																		new ArrayList<String>()));
												break;
											}

											int ingots7 = 0;
											if (finalNames.contains("37")) {
												ingots7 = 37;
											}

											if (finalNames.contains("38")) {
												ingots7 = 38;
											}

											if (finalNames.contains("39")) {
												ingots7 = 39;
											}

											if (finalNames.contains("40")) {
												ingots7 = 40;
											}

											switch (ingots7) {
											case 37:
												player.getInventory()
														.addItem(
																new ItemStack(
																		Material.WOOD_PICKAXE,
																		1));
												break;
											case 38:
												player.getInventory()
														.addItem(
																new ItemStack(
																		Material.STONE_PICKAXE,
																		1));
												break;
											case 39:
												player.getInventory()
														.addItem(
																new ItemStack(
																		Material.IRON_PICKAXE,
																		1));
												break;
											case 40:
												player.getInventory()
														.addItem(
																new ItemStack(
																		Material.DIAMOND_PICKAXE,
																		1));
												break;
											}
										}
									}, 20L);

					ArrayList<String> uuids = null;
					if (!uuids.isEmpty()) {
						uuids = (ArrayList<String>) dataC.get("Players");
						uuids.add(player.getUniqueId().toString());
						dataC.set("Players", uuids);
						try {
							dataC.save(data);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						uuids = new ArrayList<String>();
						uuids.add(player.getUniqueId().toString());
						dataC.set("Players", uuids);
						try {
							dataC.save(data);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public static Kits getPlugin() {
		return instance;
	}

	public static void setPlugin(Kits instance) {
		Kits.instance = instance;
	}

	public static PluginDescriptionFile getPdfFile() {
		return pdfFile;
	}

	public static void setPdfFile(PluginDescriptionFile pdfFile) {
		Kits.pdfFile = pdfFile;
	}

	public static File getConfigF() {
		return config;
	}

	public static void setConfig(File config) {
		Kits.config = config;
	}

	public static FileConfiguration getConfigC() {
		return configC;
	}

	public static void setConfigC(FileConfiguration configC) {
		Kits.configC = configC;
	}

	public static ConfigurationSection getConfigCS() {
		return configCS;
	}

	public static void setConfigCS(ConfigurationSection configCS) {
		Kits.configCS = configCS;
	}

	public MySQL getMysql() {
		return mysql;
	}

	public void setMysql(MySQL mysql) {
		this.mysql = mysql;
	}

	public static File getDataF() {
		return data;
	}

	public static void setData(File data) {
		Kits.data = data;
	}

	public static FileConfiguration getDataC() {
		return dataC;
	}

	public static void setDataC(FileConfiguration dataC) {
		Kits.dataC = dataC;
	}

	public static ConfigurationSection getDataCS() {
		return dataCS;
	}

	public static void setDataCS(ConfigurationSection dataCS) {
		Kits.dataCS = dataCS;
	}

	public class StringMethods {
		public void sendMessage(String string, Player player) {
			if (player != null) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						string));
			} else {
				Bukkit.getConsoleSender().sendMessage(
						ChatColor.translateAlternateColorCodes('&', string));
			}
		}

		public String compileLocation(Location location) {
			String s = location.getWorld().getName().toString() + ";"
					+ Double.toString(location.getX()) + ";"
					+ Double.toString(location.getY()) + ";"
					+ Double.toString(location.getZ());
			return s;
		}

		public Location decompileLocation(String string) {
			String[] s = string.split(";");
			String world = s[0];
			double x = Double.parseDouble(s[1]);
			double y = Double.parseDouble(s[2]);
			double z = Double.parseDouble(s[3]);
			return new Location(Bukkit.getWorld(world), x, y, z);
		}
	}

	public class MySQL extends Database {
		private String HOST;
		private String DB_NAME;
		private String USER;
		private String PASS;
		private int PORT;

		private Connection connection;

		public MySQL (Kits plugin, String HOST, String DB_NAME, String USER,
				String PASS, int PORT) {
			super(plugin);
			this.HOST = HOST;
			this.PORT = PORT;
			this.DB_NAME = DB_NAME;
			this.USER = USER;
			this.PASS = PASS;
			connection = null;
		}

		@Override
		public Connection openConnection() throws SQLException,
				ClassNotFoundException {
			if (checkConnection()) {
				return connection;
			}

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + HOST
					+ ":" + PORT + "/" + DB_NAME, USER, PASS);
			return connection;
		}

		@Override
		public boolean checkConnection() throws SQLException {
			return connection != null && !connection.isClosed();
		}

		@Override
		public Connection getConnection() {
			return connection;
		}

		@Override
		public boolean closeConnection() throws SQLException {
			if (connection == null) {
				return false;
			}

			connection.close();
			return true;
		}

		@Override
		public ResultSet querySQL(String query) throws SQLException,
				ClassNotFoundException {
			if (checkConnection()) {
				openConnection();
			}

			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		}

		@Override
		public int updateSQL(String query) throws SQLException,
				ClassNotFoundException {
			if (checkConnection()) {
				openConnection();
			}

			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			return result;
		}

		public void setHost(String host) {
			HOST = host;
		}

		public void setDatabaseName(String name) {
			DB_NAME = name;
		}

		public void setUser(String user) {
			USER = user;
		}

		public void setPass(String pass) {
			PASS = pass;
		}

		public void setPort(int port) {
			PORT = port;
		}

		public String getHost() {
			return HOST;
		}

		public String getDatabaseName() {
			return DB_NAME;
		}

		public String getUser() {
			return USER;
		}

		public String getPass() {
			return PASS;
		}

		public int getPort() {
			return PORT;
		}
	}

	public abstract class Database {
		protected Plugin plugin;

		protected Database (Kits plugin) {
			Kits.instance = plugin;
		}

		public abstract Connection openConnection() throws SQLException,
				ClassNotFoundException;

		public abstract boolean checkConnection() throws SQLException;

		public abstract Connection getConnection();

		public abstract boolean closeConnection() throws SQLException;

		public abstract ResultSet querySQL(String query) throws SQLException,
				ClassNotFoundException;

		public abstract int updateSQL(String query) throws SQLException,
				ClassNotFoundException;
	}

	public class ConfigMethods {
		public void initConfig() {
			try {
				Kits.setConfig(new File("plugins/"
						+ Kits.getPdfFile().getName(), "config.yml"));
				if (!Kits.getConfigF().exists()) {
					Kits.getConfigF().getParentFile().mkdirs();
					Kits.getConfigF().createNewFile();
				}

				Kits.setConfigC(new YamlConfiguration());
				Kits.setConfigCS(Kits.getConfigC().getConfigurationSection(""));
				Kits.getConfigC().load(Kits.getConfigF());

				for (Config value : Config.values()) {
					if (Kits.getConfigCS().get(
							value.name().replaceAll("_", ".")) == null) {
						Kits.getConfigCS().set(
								value.name().replaceAll("_", "."), value.value);
						Kits.getConfigC().save(Kits.getConfigF());
					}

					value.value = Kits.getConfigCS().get(
							value.name().replaceAll("_", "."));
				}

				Kits.getConfigC().load(Kits.getConfigF());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void initData() {
			try {
				Kits.setData(new File("plugins/" + Kits.getPdfFile().getName(),
						"data.yml"));
				if (!Kits.getDataF().exists()) {
					Kits.getDataF().getParentFile().mkdirs();
					Kits.getDataF().createNewFile();
				}

				Kits.setDataC(new YamlConfiguration());
				Kits.setDataCS(Kits.getDataC().getConfigurationSection(""));

				Kits.getDataC().load(Kits.getDataF());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ItemStack createItem(String name, Material material, int amount,
			int type, ArrayList<String> lores) {
		ItemStack is = new ItemStack(material, amount, (short) type);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(s);
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

	public enum Config {

		MYSQL_HOST ("localhost"),
		MYSQL_DATABASE ("coins"),
		MYSQL_USER ("root"),
		MYSQL_PASSWORD ("password"),
		MYSQL_PORT (3306),

		RED_X (25.5),
		RED_Y (25.5),
		RED_Z (25.5),

		BLUE_X (25.5),
		BLUE_Y (25.5),
		BLUE_Z (25.5);

		public Object value;

		Config (Object value) {
			this.value = value;
		}
	}
}