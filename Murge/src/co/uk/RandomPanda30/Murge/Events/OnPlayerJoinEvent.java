package co.uk.RandomPanda30.Murge.Events;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.DefaultCollection;
import co.uk.RandomPanda30.Murge.Collection.Special.MySQLCollection;
import co.uk.RandomPanda30.Murge.Handlers.SpawnHandler;
import co.uk.RandomPanda30.Murge.Handlers.TitleHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.MySQL.MySQL;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnPlayerJoinEvent implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		MySQL mysql = Murge.getMysql();

		event.setJoinMessage(StringMethods
				.formatMessage((String) ((String) Murge.mMap
						.getStat(MessagesValues.LOGGEDIN)).replace("%player",
						player.getName())));

		if (!MySQLCollection.getCollection().isMySQL()) {
			if (!Murge.cso.getDataC().contains(uuid.toString())) {
				DefaultCollection.getCollection().setNewPlayer_FILE(uuid);
			}
		} else {
			List<String> uuids = new ArrayList<String>();
			ResultSet result;
			try {
				result = mysql.querySQL("SELECT uuid FROM "
						+ Murge.cMap.getStat(ConfigValues.MYSQL_DATABASE)
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
		}

		if (!MurgeData.isPurge()) {
			SpawnHandler.getHandler().spawnAsPlayer(player);
			TitleHandler.getHandler().sendTitle(
					player,
					StringMethods.formatMessage(((String) Murge.mMap
							.getStat(MessagesValues.TITLE_LOGINPLAYER))),
					StringMethods.formatMessage((String) Murge.mMap
							.getStat(MessagesValues.SUBTITLE_LOGINPLAYER)), 30,
					100, 30);
		} else {
			SpawnHandler.getHandler().spawnAsSpectator(player);
			TitleHandler.getHandler().sendTitle(
					player,
					StringMethods.formatMessage((String) Murge.mMap
							.getStat(MessagesValues.TITLE_LOGINSPECTATOR)),
					StringMethods.formatMessage((String) Murge.mMap
							.getStat(MessagesValues.SUBTITLE_LOGINSPECTATOR)),
					30, 100, 30);
			for (Player players : Bukkit.getOnlinePlayers()) {
				if (players.getWorld().equals(MurgeData.getWorld())) {
					if (StatsHandler.inSpectators(uuid)) {
						players.showPlayer(player);
					} else {
						players.hidePlayer(player);
					}
				}
			}
		}
	}
}