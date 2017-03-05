package co.uk.RandomPanda30.ERRanks.Events;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.uk.RandomPanda30.ERRanks.ERRanksData;
import co.uk.RandomPanda30.ERRanks.Methods.StringMethods;

@SuppressWarnings("unchecked")
public class OnAsyncPlayerChatEvent implements Listener {

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		ArrayList<String> ranks = (ArrayList<String>) ERRanksData.configC
				.get("RANKSLIST");

		String path = "";

		for (String perm : ranks) {
			if (player.hasPermission((String) ERRanksData.configC.get("RANKS."
					+ perm.toUpperCase() + ".PERMISSION"))) {
				path = perm.toUpperCase();
			}
		}

		if (path == "") {
			event.setFormat(StringMethods
					.formatMessage((String) ERRanksData.configC
							.get("RANKS.DEFAULT.TAG").toString()
							.replace("%name", player.getName()))
					+ event.getMessage());
			return;
		}

		event.setFormat(StringMethods
				.formatMessage((String) ERRanksData.configC
						.get("RANKS." + path + ".TAG").toString()
						.replace("%name", player.getName())
						+ event.getMessage()));
	}
}