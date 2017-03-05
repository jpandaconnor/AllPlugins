package co.uk.RandomPanda30.CityRP;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import co.uk.RandomPanda30.CityRP.Commands.CommandRegister;
import co.uk.RandomPanda30.CityRP.Configs.ConfigH;
import co.uk.RandomPanda30.CityRP.Configs.ItemsH;
import co.uk.RandomPanda30.CityRP.Configs.MessagesH;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Config;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Messages;
import co.uk.RandomPanda30.CityRP.Configs.JobsH.CitizenH;
import co.uk.RandomPanda30.CityRP.Misc.Job;
import co.uk.RandomPanda30.CityRP.MySQL.MySQL;
import co.uk.RandomPanda30.CityRP.MySQL.Tables;
import co.uk.RandomPanda30.CityRP.Objects.Player;
import co.uk.RandomPanda30.CityRP.Resources.EncryptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil.Cause;
import co.uk.RandomPanda30.CityRP.Resources.MessageUtil;
import co.uk.RandomPanda30.CityRP.Resources.RegisterUtil;
import co.uk.RandomPanda30.CityRP.Setup.DatabaseSetup;
import co.uk.RandomPanda30.CityRP.Tasks.Server;

@SuppressWarnings("unchecked")
public class CityRP extends JavaPlugin implements Listener {

	private CityRP plugin;
	private PluginDescriptionFile pdfFile;

	private BukkitTask serverT;

	public void onEnable() {
		plugin = this;

		this.pdfFile = this.getDescription();

		new ConfigH(this);
		new MessagesH(this);
		new ItemsH(this);

		new CitizenH(this);

		new CommandRegister(this);

		new RegisterUtil(this);

		// Check config if enabled here

		// Starting server timer here every 1 second

		setServerT(new Server(this).runTaskTimer(this, 0L, 20L));

		getServer().getPluginManager().registerEvents(this, this);

		for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
			new Player(player, player.getName(), 100, Job.CITIZEN, true);
		}

		for (String s : (ArrayList<String>) Messages.START.value) {
			MessageUtil.sendMessage(s.replace("%no", this.pdfFile.getVersion()),
					null);
		}

		if (!(Boolean) Config.MYSQL_SET.value) {
			MessageUtil.sendMessage(
					(String) Messages.NOTIFICATIONS_NOMYSQL.value.toString()
							.replace("%time",
									Integer.toString(
											(Integer) Config.MYSQL_SETUPDELAY.value)),
					null);
			getServer().getScheduler().scheduleSyncDelayedTask(this,
					new Runnable() {
						@Override
						public void run() {
							// Run setup here
							new DatabaseSetup(plugin);
						}
					}, new Long((Integer) Config.MYSQL_SETUPDELAY.value));
		} else {
			EncryptionUtil eu = new EncryptionUtil(
					(String) Config.MYSQL_DBPASS.value);
			try {
				CityRPData.mysql = new MySQL(this,
						(String) Config.MYSQL_DBIP.value,
						(String) Config.MYSQL_DBNAME.value,
						(String) Config.MYSQL_DBUSER.value, eu.decrypt(),
						(Integer) Config.MYSQL_DBPORT.value);
				Tables.checkTables();
			} catch (InvalidKeyException | NoSuchPaddingException
					| NoSuchAlgorithmException | BadPaddingException
					| IllegalBlockSizeException e) {
				new ExceptionUtil(e.getStackTrace(), Cause.ENCRYPTIONERROR,
						"Failed to decrypt loading in MySQL variable");
			}
		}
	}

	public void onDisable() {
		for (String s : (ArrayList<String>) Messages.STOP.value) {
			MessageUtil.sendMessage(s.replace("%no", this.pdfFile.getVersion()),
					null);
		}

		// Make everything in the data class null - Stops memory leaks

		plugin = null;
	}

	public CityRP getInstance() {
		return plugin;
	}

	public PluginDescriptionFile getData() {
		return pdfFile;
	}

	public BukkitTask getServerT() {
		return serverT;
	}

	public void setServerT(BukkitTask serverT) {
		this.serverT = serverT;
	}

	@EventHandler
	public void onServerCommandEvent(ServerCommandEvent event) {
		Bukkit.broadcastMessage(event.getCommand());
		event.setCancelled(true);
	}
}