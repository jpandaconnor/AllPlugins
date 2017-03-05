package co.uk.RandomPanda30.Guilds.Events;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class OnAsyncPlayerChatEvent implements Listener {

	private Guilds plugin;

	public OnAsyncPlayerChatEvent (Guilds plugin) {
		this.setPlugin(plugin);
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		String guild = "";

		ResultSet pguild;
		try {
			pguild = Data.mysql.querySQL("SELECT guild FROM data WHERE uuid = '"
					+ player.getUniqueId().toString() + "'");
			if (pguild.next()) {
				guild = pguild.getString("guild");
			}
		} catch (ClassNotFoundException | SQLException e) {
			new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
					"Error whilst getting player's guild in the invite command method thingy");
		}

		if (guild.equals("-")) {
			return;
		}

		String tag = null;

		try {
			pguild = Data.mysql.querySQL(
					"SELECT tag FROM guilds WHERE guild = '" + guild + "'");
			if (pguild.next()) {
				tag = pguild.getString("tag");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (tag != null) {
			event.setMessage(ChatColor.translateAlternateColorCodes('&',
					"&F[&A" + tag + "&F] ") + event.getMessage());
		}
	}

	public Guilds getPlugin() {
		return plugin;
	}

	public void setPlugin(Guilds plugin) {
		this.plugin = plugin;
	}
}