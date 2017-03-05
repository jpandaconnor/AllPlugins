package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.BroadcastCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.DumpCollection;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnPlayerQuitEvent implements Listener {

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		event.setQuitMessage(StringMethods.formatMessage(((String) Murge.mMap
				.getStat(MessagesValues.LOGGEDOUT_VIANORMAL)).replace(
				"%player", player.getName())));

		if (StatsHandler.inCombatLog(uuid)) {
			DumpCollection.getCollection().dumpPunish(uuid);

			for (ItemStack i : player.getInventory().getContents()) {
				if (i != null) {
					if (i.getType() != Material.AIR) {
						player.getWorld().dropItemNaturally(
								player.getLocation(), i);
						player.getInventory().remove(i);
					}
				}
			}

			for (ItemStack i : player.getInventory().getArmorContents()) {
				if (i != null) {
					if (i.getType() != Material.AIR) {
						player.getWorld().dropItemNaturally(
								player.getLocation(), i);
						player.getInventory().remove(i);
					}
				}
			}

			if (BroadcastCollection.broadcastLoggedOut()) {
				StringMethods.sendMessageToWorldPlayers((String) Murge.mMap
						.getStat(MessagesValues.QUITCOMBAT));
			}

			StatsHandler.removeCombatLog(uuid);
		}

		if (StatsHandler.inSpectators(uuid)) {
			StatsHandler.removeSpectator(uuid);
		}
	}
}