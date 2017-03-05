package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnPlayerDropItemEvent implements Listener {

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		if (StatsHandler.inSpectators(uuid)) {
			StringMethods.sendMessage(((String) Murge.mMap
					.getStat(MessagesValues.SPECTATE_CANNOTDROPITEMS)), player);
			event.setCancelled(true);
		}
	}
}