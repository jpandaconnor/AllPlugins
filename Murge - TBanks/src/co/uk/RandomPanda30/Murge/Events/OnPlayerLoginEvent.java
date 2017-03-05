package co.uk.RandomPanda30.Murge.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnPlayerLoginEvent implements Listener {

	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if (MurgeData.isPurge()) {
			if (!player.hasPermission("murge.bypass")) {
				event.disallow(Result.KICK_OTHER, StringMethods
						.formatMessage((String) Murge.mMap
								.getStat(MessagesValues.REFUSEMESSAGE)));
			} else {
				event.allow();
			}
		}
	}
}