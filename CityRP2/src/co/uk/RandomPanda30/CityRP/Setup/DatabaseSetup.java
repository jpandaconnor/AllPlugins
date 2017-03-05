package co.uk.RandomPanda30.CityRP.Setup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Data;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Messages;
import co.uk.RandomPanda30.CityRP.Objects.Player;
import co.uk.RandomPanda30.CityRP.Resources.EncryptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil.Cause;
import co.uk.RandomPanda30.CityRP.Resources.MessageUtil;

public class DatabaseSetup implements Listener, CommandExecutor {

	enum Steps {
		DBIP, DBNAME, DBUSER, DBPASS, DBPORT, CONFIRMED;
	}

	CityRP plugin;

	private Steps step;

	String ip;
	String dbname;
	String dbuser;
	String epass;
	int dbport;
	boolean confirmed;

	public DatabaseSetup (CityRP plugin) {
		this.plugin = plugin;

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		plugin.getCommand("jpanda").setExecutor(this);

		start();
	}

	@SuppressWarnings("unchecked")
	public void start() {
		if (!CityRPData.players.isEmpty()) {
			for (Player player : CityRPData.players) {
				if (player.getPlayer().hasPermission("cityrp.notifications")) {
					MessageUtil.sendMessage(
							(String) Messages.NOTIFICATIONS_DATABASESETUP.value,
							player);
				}
			}
		}
		step = Steps.DBIP;

		for (String s : (List<String>) Messages.SETUP_DATABASE_DETAILS.value) {
			MessageUtil.sendMessage(s, null);
		}

		Bukkit.getLogger().setLevel(Level.OFF);

		MessageUtil.sendMessage((String) Messages.SETUP_DATABASE_STEP1.value,
				null);
	}

	public void next() {
		switch (step) {
		case DBIP:
			MessageUtil.sendMessage(
					(String) Messages.SETUP_DATABASE_STEP2.value, null);
			step = Steps.DBNAME;
			break;
		case DBNAME:
			MessageUtil.sendMessage(
					(String) Messages.SETUP_DATABASE_STEP3.value, null);
			step = Steps.DBUSER;
			break;
		case DBUSER:
			MessageUtil.sendMessage(
					(String) Messages.SETUP_DATABASE_STEP4.value, null);
			step = Steps.DBPASS;
			break;
		case DBPASS:
			MessageUtil.sendMessage(
					(String) Messages.SETUP_DATABASE_STEP5.value, null);
			step = Steps.DBPORT;
			break;
		case DBPORT:
			MessageUtil.sendMessage(
					(String) Messages.SETUP_DATABASE_STEP6.value, null);
			step = Steps.CONFIRMED;
			break;
		case CONFIRMED:
			end();
			break;
		}
	}

	public void end() {
		Bukkit.getLogger().setLevel(Level.INFO);
		step = null;

		MessageUtil.sendMessage((String) Messages.SETUP_DATABASE_FINISHED.value,
				null);

		Data.configf.set("MYSQL.SET", true);
		Data.configf.set("MYSQL.DBIP", ip);
		Data.configf.set("MYSQL.DBNAME", dbname);
		Data.configf.set("MYSQL.DBUSER", dbuser);
		Data.configf.set("MYSQL.DBPASS", epass);
		Data.configf.set("MYSQL.DBPORT", dbport);

		try {
			Data.configf.save(Data.config);
		} catch (IOException e) {
			new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"Error whilst saving MySQL data");
		}

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				plugin.getServer().reload();
			}
		}, 100L);
	}

	@EventHandler
	public void onServerCommandEvent(ServerCommandEvent event) {
		String command = event.getCommand().split(" ")[0];

		if (step != null) {
			event.setCommand("jpanda");
			switch (step) {
			case DBIP:
				this.ip = command;
				next();
				break;
			case DBNAME:
				this.dbname = command;
				next();
				break;
			case DBUSER:
				this.dbuser = command;
				next();
				break;
			case DBPASS:
				EncryptionUtil ec = new EncryptionUtil(command);
				try {
					this.epass = ec.encrypt();
				} catch (InvalidKeyException | NoSuchPaddingException
						| NoSuchAlgorithmException
						| UnsupportedEncodingException | BadPaddingException
						| IllegalBlockSizeException e) {
					new ExceptionUtil(e.getStackTrace(), Cause.ENCRYPTIONERROR,
							"Error whilst using the util. Personal information has not been saved");
					ip = null;
					dbname = null;
					dbuser = null;
					epass = null;
					dbport = 0;

					step = null;

					break;
				}
				next();
				break;
			case DBPORT:
				try {
					this.dbport = Integer.parseInt(command);
					next();
				} catch (NumberFormatException e) {
					MessageUtil.sendMessage(
							(String) Messages.SETUP_DATABASE_REETNER_PORT.value,
							null);
					e.printStackTrace();
				}
				break;
			case CONFIRMED:
				if (command.toLowerCase().equals("y")
						|| command.toLowerCase().equals("yes")) {
					next();
				} else {
					MessageUtil.sendMessage(
							(String) Messages.SETUP_DATABASE_BACKTOSTART.value,
							null);
					MessageUtil.sendMessage(
							(String) Messages.SETUP_DATABASE_STEP1.value, null);
					step = Steps.DBIP;
				}

				break;
			default:
				break;
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		return true;
	}

	public boolean isLoggable(LogRecord line) {
		return !(line.getMessage().contains("INFO"));
	}
}
