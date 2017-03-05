package co.uk.RandomPanda30.DailyRewardsPlus.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.uk.RandomPanda30.DailyRewardsPlus.B;

public class EA implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		if (!B.dataC.contains(event.getPlayer().getUniqueId().toString())) {
			B.dataC.set(event.getPlayer().getUniqueId().toString() + ".time", 0);
			try {
				B.dataC.save(B.data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}