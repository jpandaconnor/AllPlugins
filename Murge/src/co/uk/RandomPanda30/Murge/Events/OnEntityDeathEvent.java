package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Player.KillsCollection;
import co.uk.RandomPanda30.Murge.Handlers.ScoreboardHandler;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;

public class OnEntityDeathEvent implements Listener {

	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent event) {
		Player attacker = null;

		UUID attackerUUID = null;

		if (event.getEntity() instanceof Player
				&& event.getEntity().getKiller() instanceof Player) {
			attacker = (Player) event.getEntity().getKiller();
			attackerUUID = attacker.getUniqueId();

			if (MurgeData.isPurge()) {
				StatsHandler.incrementPlayerKill(attackerUUID);
				KillsCollection.getCollection()
						.addValue(attacker.getUniqueId());
				ScoreboardHandler.getHandler().doScoreboard(attacker);
			}
		}
	}
}