package co.uk.RandomPanda30.Guilds.Events;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Plots.PlotHandler;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.EUtil.Cause;

public class OnEntityDamageEvent implements Listener {

	public Guilds plugin;

	public OnEntityDamageEvent (Guilds plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (event.getCause().equals(DamageCause.VOID)) {
				String guild = "";

				ResultSet pguild;
				try {
					pguild = Data.mysql
							.querySQL("SELECT guild FROM data WHERE uuid = '"
									+ player.getUniqueId().toString() + "'");
					if (pguild.next()) {
						guild = pguild.getString("guild");
					}
				} catch (ClassNotFoundException | SQLException e) {
					new EUtil(e.getStackTrace(), Cause.MYSQLERROR,
							"Error whilst getting player's guild in the invite command method thingy");
				}

				if (guild.equals("NULL")) {
					return;
				}

				PlotHandler ph = new PlotHandler(guild);
				ph.sendHome(player);
			}
		}
	}

}
