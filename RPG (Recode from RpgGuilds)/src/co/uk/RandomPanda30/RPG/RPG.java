package co.uk.RandomPanda30.RPG;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import co.uk.RandomPanda30.RPG.Commands.CMD_g;
import co.uk.RandomPanda30.RPG.Commands.CMD_grank;
import co.uk.RandomPanda30.RPG.Commands.CMD_gtp;
import co.uk.RandomPanda30.RPG.Commands.CMD_guild;
import co.uk.RandomPanda30.RPG.Commands.CMD_hq;
import co.uk.RandomPanda30.RPG.Commands.CMD_lookup;
import co.uk.RandomPanda30.RPG.Commands.CMD_o;
import co.uk.RandomPanda30.RPG.Economy.RPGEconomy;
import co.uk.RandomPanda30.RPG.Economy.Vault;
import co.uk.RandomPanda30.RPG.Events.OnAsyncPlayerChatEvent;
import co.uk.RandomPanda30.RPG.Events.OnBlockBreakEvent;
import co.uk.RandomPanda30.RPG.Events.OnEntityDamageByEntityEvent;
import co.uk.RandomPanda30.RPG.Events.OnInventoryClickEvent;
import co.uk.RandomPanda30.RPG.Events.OnInventoryCloseEvent;
import co.uk.RandomPanda30.RPG.Events.OnPlayerBucketEmptyEvent;
import co.uk.RandomPanda30.RPG.Events.OnPlayerBucketFillEvent;
import co.uk.RandomPanda30.RPG.Events.OnPlayerDeathEvent;
import co.uk.RandomPanda30.RPG.Events.OnPlayerJoinEvent;
import co.uk.RandomPanda30.RPG.Events.OnPlayerQuitEvent;
import co.uk.RandomPanda30.RPG.Files.RPGConfig;
import co.uk.RandomPanda30.RPG.Files.Bank.Bank;
import co.uk.RandomPanda30.RPG.Files.Config.Config;
import co.uk.RandomPanda30.RPG.Files.Messages.Messages;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesValues;
import co.uk.RandomPanda30.RPG.Handlers.GuildH;
import co.uk.RandomPanda30.RPG.Pending.SignListener;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class RPG extends JavaPlugin {

	private RPG plugin;
	private RPGValues values;

	private PluginDescriptionFile pdfFile;

	private RPGConfig config;
	private RPGEconomy economy;

	private WorldGuardPlugin worldGuard;

	public static BukkitTask checker;

	public static List<UUID> gbanks = new ArrayList<UUID>();

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

		values = new RPGValues(this);

		/* Any config thingies go here */
		setRPGConfig(new RPGConfig(this, values, new Config(this, values),
				new Messages(this, values), new Bank(this, values)));
		config.start();

		setRPGEconomy(new RPGEconomy(this, values, config.bank, new Vault(this,
				economy)));
		economy.init();

		if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			setWorldGuard((WorldGuardPlugin) Bukkit.getPluginManager()
					.getPlugin("WorldGuard"));
			// Message here
		}

		getCommand("o").setExecutor(new CMD_o(this));
		getCommand("g").setExecutor(new CMD_g(this));
		getCommand("gtp").setExecutor(new CMD_gtp(this));
		getCommand("hq").setExecutor(new CMD_hq(this));
		getCommand("grank").setExecutor(new CMD_grank(this));
		getCommand("lookup").setExecutor(new CMD_lookup(this));
		getCommand("guild").setExecutor(new CMD_guild(this));

		Bukkit.getPluginManager().registerEvents(
				new OnInventoryClickEvent(this), this);
		Bukkit.getPluginManager().registerEvents(
				new OnInventoryCloseEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new OnBlockBreakEvent(this),
				this);
		Bukkit.getPluginManager().registerEvents(
				new OnPlayerBucketFillEvent(this), this);
		Bukkit.getPluginManager().registerEvents(
				new OnPlayerBucketEmptyEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new OnPlayerDeathEvent(this),
				this);
		Bukkit.getPluginManager().registerEvents(
				new OnInventoryClickEvent(this), this);
		Bukkit.getPluginManager().registerEvents(
				new OnEntityDamageByEntityEvent(this), this);
		Bukkit.getPluginManager().registerEvents(
				new OnAsyncPlayerChatEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new OnPlayerJoinEvent(this),
				this);
		Bukkit.getPluginManager().registerEvents(new OnPlayerQuitEvent(this),
				this);

		Bukkit.getPluginManager().registerEvents(new SignListener(this), this);

		String value = this.getConfig().getString("RewardDelay");
		long newValue = Long.parseLong(value);

		checker = new SignListener.ChestTimer(this).runTaskTimer(this,
				newValue, newValue);

		List<ItemStack> list = (List<ItemStack>) this.values.getConfigC()
				.getList("Rewards");
		ItemStack[] items = new ItemStack[list.size()];
		if (items != null) {
			GuildH.rewards = list.toArray(items);
		}

		SignListener.ESignMethods.load();

		for (String s : (ArrayList<String>) getRPGConfig().getMessagesMap()
				.getStat(MessagesValues.SH)) {
			sendMessage(s, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onDisable() {
		SignListener.ESignMethods.save();
		checker = null;

		if (GuildH.rewards != null) {
			values.getConfigC().set("Rewards", GuildH.rewards);
		} else {
			values.getConfigC().set("Rewards", new ArrayList<ItemStack>());
		}
		this.config.saveAllConfigs();

		for (String s : (ArrayList<String>) getRPGConfig().getMessagesMap()
				.getStat(MessagesValues.SHM)) {
			sendMessage(s, null);
		}

		config.stop();
		gbanks.clear();
		plugin = null;
	}

	public String formatMessage(String message) {
		return message
				.replaceAll(
						"%TAG",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.TAG))
				.replaceAll(
						"%N",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.NORMAL))
				.replaceAll(
						"%W",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.WARNING))
				.replaceAll(
						"%E",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.ERROR))
				.replaceAll(
						"%A",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.ARG))
				.replaceAll(
						"%H",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.HEADER))
				.replaceAll(
						"%G",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.ON))
				.replaceAll(
						"%B",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.OFF))
				.replaceAll(
						"%T",
						(String) getRPGConfig().getMessagesMap().getStat(
								MessagesValues.TEXT))
				.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
				.replaceAll("&u", "\n");
	}

	public void sendMessage(String message, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(formatMessage(message));
		} else {
			player.sendMessage(formatMessage(message));
		}
	}

	public void setPlugin(RPG plugin) {
		this.plugin = plugin;
	}

	public RPG getPlugin() {
		return plugin;
	}

	public void setRPGValues(RPGValues values) {
		this.values = values;
	}

	public RPGValues getRPGValues() {
		if (values != null) {
			return values;
		} else {
			return new RPGValues(this);
		}

	}

	public void setPdfFile(PluginDescriptionFile pdfFile) {
		this.pdfFile = pdfFile;
	}

	public PluginDescriptionFile getPdfFile() {
		return pdfFile;
	}

	public void setRPGConfig(RPGConfig config) {
		this.config = config;
	}

	public RPGConfig getRPGConfig() {
		return config;
	}

	public void setRPGEconomy(RPGEconomy economy) {
		this.economy = economy;
	}

	public RPGEconomy getRPGEconomy() {
		return economy;
	}

	public void setWorldGuard(WorldGuardPlugin worldGuard) {
		this.worldGuard = worldGuard;
	}

	public WorldGuardPlugin getWorldGuard() {
		return worldGuard;
	}
}
