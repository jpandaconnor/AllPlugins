package co.uk.RandomPanda30.Guilds.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class Config {

	public enum ConfigValues {

		MYSQL_SET (false),
		MYSQL_SETUPDELAY (5),

		PLOTWORLD ("plots"),
		FALLBACKWORLD ("world"),

		RANK_LEADER_DEFAULT ("Leader:Invite;true!" + "InviteGuild;true!"
				+ "Kick;true!" + "KickGuild;true!" + "Gmotd;true!"
				+ "GChat;true!" + "RankSet;true!" + "RankTitle;true!"
				+ "RankCreate;true!" + "RankDelete;true!" + "RankPerms;true!"
				+ "PlotCreate;true!" + "PlotDelete;true!" + "PlotHome;true!"
				+ "PlotReset;true!" + "WarDeclare;true!" + "WarSurrender;true!"
				+ "Title;[&CLeader]!" + "PVP;true"),
		RANK_ROOKIE_DEFAULT ("Rookie:Invite;false!" + "InviteGuild;false!"
				+ "Kick;false!" + "KickGuild;false!" + "Gmotd;false!"
				+ "GChat;true!" + "RankSet;false!" + "RankTitle;false!"
				+ "RankCreate;false!" + "RankDelete;false!" + "RankPerms;false!"
				+ "PlotCreate;false!" + "PlotDelete;false!" + "PlotHome;true!"
				+ "PlotReset;false!" + "WarDeclare;false!"
				+ "WarSurrender;false!" + "Title;[&7Rookie]!" + "PVP;false"),
		MYSQL_DBIP ("null"),
		MYSQL_DBNAME ("null"),
		MYSQL_DBUSER ("null"),
		MYSQL_DBPASS ("null"),
		MYSQL_DBPORT (0);

		public Object value;

		ConfigValues (Object value) {
			this.value = value;
		}
	}

	public Guilds plugin;

	public Config (Guilds plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		try {
			Data.config = new File(
					"plugins/" + plugin.getName() + "/config.yml");
			if (!Data.config.exists()) {
				Data.config.getParentFile().mkdirs();
				Data.config.createNewFile();
			}

			Data.configf = new YamlConfiguration();
			Data.configs = Data.configf.getConfigurationSection("");
			Data.configf.load(Data.config);

			for (ConfigValues value : ConfigValues.values()) {
				if (Data.configs
						.get(value.name().replaceAll("_", ".")) == null) {
					Data.configs.set(value.name().replaceAll("_", "."),
							value.value);
					save();
				}

				value.value = Data.configs
						.get(value.name().replaceAll("_", "."));
			}

			Data.configf.load(Data.config);
		} catch (IOException | InvalidConfigurationException e) {
			new EUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to init the config");
		}
	}

	public void save() {
		try {
			Data.configf.save(Data.config);
		} catch (IOException e) {
			new EUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
					"%EFailed to save the config");
		}
	}
}