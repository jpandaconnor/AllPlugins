package co.uk.RandomPanda30.MineRP.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import co.uk.RandomPanda30.MineRP.MineRPData;
import co.uk.RandomPanda30.MineRP.Handlers.PlayerHandler;

public class OnPlayerQuitEvent implements Listener {

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		PlayerHandler object = new PlayerHandler(null, null, 0, null);

		for (PlayerHandler ph : MineRPData.players) {
			if (ph.getPlayer().equals(player)) {
				object = ph;
			}
		}
		MineRPData.players.remove(object);
	}
}