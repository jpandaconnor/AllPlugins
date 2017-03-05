package co.uk.RandomPanda30.Murge;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import co.uk.RandomPanda30.Murge.Checkers.DayTimer;
import co.uk.RandomPanda30.Murge.Collection.DefaultCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.DumpCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.EconCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.KitsCollection;
import co.uk.RandomPanda30.Murge.Collection.Regeneration.OreCollection;
import co.uk.RandomPanda30.Murge.Collection.Regeneration.TreeCollection;
import co.uk.RandomPanda30.Murge.Collection.Special.MySQLCollection;
import co.uk.RandomPanda30.Murge.Collection.World.LocationCollection;
import co.uk.RandomPanda30.Murge.Collection.World.WorldBorderCollection;
import co.uk.RandomPanda30.Murge.Commands.CommandMurge;
import co.uk.RandomPanda30.Murge.Commands.CommandsFixwb;
import co.uk.RandomPanda30.Murge.Commands.CommandsStart;
import co.uk.RandomPanda30.Murge.Commands.CommandsStop;
import co.uk.RandomPanda30.Murge.Events.Events;
import co.uk.RandomPanda30.Murge.Executor.OreRegenExecutor;
import co.uk.RandomPanda30.Murge.Executor.TreeRegenExecutor;
import co.uk.RandomPanda30.Murge.Handlers.CommandHandler;
import co.uk.RandomPanda30.Murge.Handlers.EconHandler;
import co.uk.RandomPanda30.Murge.Handlers.EncryptionHandler;
import co.uk.RandomPanda30.Murge.Handlers.InworldHandler;
import co.uk.RandomPanda30.Murge.Handlers.ScoreboardHandler;
import co.uk.RandomPanda30.Murge.Handlers.WorldBorderHandler;
import co.uk.RandomPanda30.Murge.Methods.ConfigMethods;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Methods.WorldMethods;
import co.uk.RandomPanda30.Murge.Metrics.Metrics;
import co.uk.RandomPanda30.Murge.MySQL.MySQL;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.ConfigMapping;
import co.uk.RandomPanda30.Murge.Values.ConfigurationSectionObjects;
import co.uk.RandomPanda30.Murge.Values.ItemsMapping;
import co.uk.RandomPanda30.Murge.Values.MessagesMapping;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class Murge extends JavaPlugin {

	public static BukkitTask normalChecker;
	public static BukkitTask purgeChecker;
	public static BukkitTask oreChecker;
	public static BukkitTask treeChecker;

	public static ConfigMapping cMap;
	public static MessagesMapping mMap;
	public static ItemsMapping iMap;

	private static MySQL mysql;

	public static ConfigurationSectionObjects cso = new ConfigurationSectionObjects();

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		MurgeData.plugin = this;
		MurgeData.initDataFile();
		ConfigMethods.initAllConfigs();

		cMap = new ConfigMapping(cso.getConfigC());
		mMap = new MessagesMapping(cso.getMessagesC());
		iMap = new ItemsMapping(cso.getItemsC());

		cMap.load();
		mMap.load();
		iMap.load();

		if (!WorldMethods.checkWorld((String) cMap.getStat(ConfigValues.WORLD))) {
			StringMethods.sendMessage(
					(String) mMap.getStat(MessagesValues.WORLDNULL), null);
			return;
		}

		if ((Boolean) cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
			try {
				Murge.setMysql(new MySQL(this, (String) cMap
						.getStat(ConfigValues.MYSQL_HOST), (String) cMap
						.getStat(ConfigValues.MYSQL_DATABASE), (String) cMap
						.getStat(ConfigValues.MYSQL_USER), EncryptionHandler
						.getHandle().decrypt(
								(String) cMap
										.getStat(ConfigValues.MYSQL_PASSWORD)),
						(Integer) cMap.getStat(ConfigValues.MYSQL_PORT)));
				Murge.getMysql().openConnection();
				MySQLCollection.getCollection().setup();
			} catch (Exception e) {
				StringMethods.sendMessage((String) Murge.mMap
						.getStat(MessagesValues.MYSQL_BADDETAILS), null);
				e.printStackTrace();
				return;
			}
		}

		MurgeData.setWorld(Bukkit.getWorld((String) cMap
				.getStat(ConfigValues.WORLD)));

		this.getServer().getMessenger()
				.registerOutgoingPluginChannel(this, "BungeeCord");

		Events events = new Events(this);
		events.registerEvents();

		registerCommands();

		if (WorldBorderCollection.getCollection().isEnabled()) {
			WorldBorderHandler.createWorldBorder(WorldBorderCollection
					.getCollection().getCentre(), WorldBorderCollection
					.getCollection().getSize(), WorldBorderCollection
					.getCollection().getDamage());
		}

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
				"gamerule doDaylightCycle false");

		MurgeData.getWorld().setTime(1000L);

		normalChecker = new DayTimer(this).runTaskTimer(this, 0, 20);

		for (Player player : InworldHandler.getHandler().getPlayers()) {
			if ((Boolean) cMap.getStat(ConfigValues.MYSQL_ENABLE)) {
				List<String> uuids = new ArrayList<String>();
				ResultSet result;
				try {
					result = getMysql().querySQL(
							"SELECT uuid FROM "
									+ cMap.getStat(ConfigValues.MYSQL_DATABASE)
									+ ".murge");
					while (result.next()) {
						uuids.add(result.getString("uuid"));
					}

					if (!uuids.contains(player.getUniqueId().toString())) {
						DefaultCollection.getCollection().setNewPlayer_MYSQL(
								player.getUniqueId());
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				DefaultCollection.getCollection().setNewPlayer_FILE(
						player.getUniqueId());
			}

			if (DumpCollection.getCollection().isDumped(player.getUniqueId())) {
				player.getInventory().clear();
				player.getInventory().setContents(
						DumpCollection.getCollection().getInventoryDump(
								player.getUniqueId()));
				player.getInventory().setArmorContents(
						DumpCollection.getCollection().getArmourDump(
								player.getUniqueId()));
			}
		}

		if (EconCollection.getCollection().usingVault()) {
			EconHandler.getHandler().setupEcon();
		}

		StringMethods.sendMessageToWorldPlayers((String) mMap
				.getStat(MessagesValues.COUNTDOWN_STARTED));

		if (OreCollection.getCollection().isEnabled()) {
			OreCollection.getCollection().loadData();
		}

		if (OreCollection.getCollection().isEnabled()) {
			OreRegenExecutor oreR = new OreRegenExecutor();
			oreR.execute();
		}

		if (TreeCollection.getCollection().isEnabled()) {
			TreeRegenExecutor treeR = new TreeRegenExecutor();
			treeR.execute();
		}

		//KitsCollection.getCollection().load();

		ScoreboardHandler.getHandler().onlineInit();

		for (String s : (ArrayList<String>) mMap.getStat(MessagesValues.SM)) {
			StringMethods.sendMessage(s, null);
		}

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onDisable() {
		for (String s : (ArrayList<String>) mMap.getStat(MessagesValues.SHM)) {
			StringMethods.sendMessage(s, null);
		}
		
		if (normalChecker != null) {
			normalChecker.cancel();
		}

		if (purgeChecker != null) {
			purgeChecker.cancel();
		}

		if (oreChecker != null) {
			oreChecker.cancel();
		}

		if (treeChecker != null) {
			treeChecker.cancel();
		}

		if (MurgeData.isPurge()) {
			InworldHandler.getHandler().sendMessage(
					(String) mMap.getStat(MessagesValues.RELOADED));
		}

		InworldHandler.getHandler().resetScoreboard();

		for (Player player : InworldHandler.getHandler().getPlayers()) {
			if (!StatsHandler.inSpectators(player.getUniqueId())) {
				DumpCollection.getCollection().dump(
						player.getInventory().getContents(),
						player.getInventory().getArmorContents(),
						player.getUniqueId());
			} else {
				player.getInventory().clear();
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.showPlayer(player);
				}
				if (LocationCollection.isSpawnSet()) {
					player.teleport(LocationCollection.getSpawn());
				} else {
					player.teleport(MurgeData.getWorld().getSpawnLocation());
				}
			}
		}

		if (OreCollection.getCollection().isEnabled()) {
			OreCollection.getCollection().dumpData();
		}

		KitsCollection.getCollection().save();

		cMap.save();
		mMap.save();
		iMap.save();

		MurgeData.plugin = null;
	}

	public void registerCommands() {
		CommandHandler handler = new CommandHandler();
		handler.register("murge", new CommandMurge());
		handler.register("start", new CommandsStart());
		handler.register("stop", new CommandsStop());
		handler.register("fixwb", new CommandsFixwb());
		getCommand("murge").setExecutor(handler);
	}

	public static MySQL getMysql() {
		if (mysql != null) {
			try {
				if (!mysql.checkConnection()) {
					mysql.openConnection();
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return mysql;
		}
		return null;
	}

	public static void setMysql(MySQL mysql) {
		Murge.mysql = mysql;
	}
}