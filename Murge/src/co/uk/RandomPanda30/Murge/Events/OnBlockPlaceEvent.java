package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.MiscCollection;
import co.uk.RandomPanda30.Murge.Collection.World.LocationCollection;
import co.uk.RandomPanda30.Murge.Items.EditLocationItems;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnBlockPlaceEvent implements Listener {

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Location location = event.getBlock().getLocation();

		World world = player.getWorld();
		UUID uuid = player.getUniqueId();

		if (world.equals(MurgeData.getWorld())) {
			if (StatsHandler.inSpectators(uuid)) {
				StringMethods.sendMessage((String) Murge.mMap
						.getStat(MessagesValues.SPECTATE_CANNOTPLACEBLOCKS),
						player);
				event.setCancelled(true);
			}
			if (MurgeData.isPurge()) {
				if (!MiscCollection.allowBlockPlace()) {
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.PLAYER_CANNOTPLACEBLOCKS),
							player);
					event.setCancelled(true);
				}
			}
		}

		if (player.getItemInHand() != null) {
			if (player.getItemInHand().equals(
					EditLocationItems.obtainSpawnLocation())) {
				if (player.equals(MurgeData.getEditingLocation())) {
					LocationCollection.setSpawn(location);
					player.getInventory().remove(
							EditLocationItems.obtainSpawnLocation());
					StringMethods.sendMessage(
							((String) Murge.mMap
									.getStat(MessagesValues.SET_SPAWN))
									.replace("%x",
											Double.toString(location.getX()))
									.replace("%y",
											Double.toString(location.getY()))
									.replace("%z",
											Double.toString(location.getZ())),
							player);
				} else {
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.PLAYER_CANNOTPLACEBLOCKS),
							player);
					event.setCancelled(true);
				}
			}

			if (player.getItemInHand().equals(
					EditLocationItems.obtainSpectatorLocation())) {
				if (player.equals(MurgeData.getEditingLocation())) {
					LocationCollection.setSpectatorSpawn(location);
					player.getInventory().remove(
							EditLocationItems.obtainSpectatorLocation());
					StringMethods.sendMessage((String) ((String) Murge.mMap
							.getStat(MessagesValues.SET_SPECTATORSPAWN))
							.replace("%x", Double.toString(location.getX()))
							.replace("%y", Double.toString(location.getY()))
							.replace("%z", Double.toString(location.getZ())),
							player);
				} else {
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.PLAYER_CANNOTPLACEBLOCKS),
							player);
					event.setCancelled(true);
				}
			}
		}
	}
}