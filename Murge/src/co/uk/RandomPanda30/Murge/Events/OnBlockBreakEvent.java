package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.MiscCollection;
import co.uk.RandomPanda30.Murge.Collection.World.LocationCollection;
import co.uk.RandomPanda30.Murge.Handlers.Regeneration.OreRHandler;
import co.uk.RandomPanda30.Murge.Handlers.Regeneration.OreRHandler.Oretype;
import co.uk.RandomPanda30.Murge.Items.EditLocationItems;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnBlockBreakEvent implements Listener {

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Location location = event.getBlock().getLocation();

		World world = player.getWorld();
		UUID uuid = player.getUniqueId();

		Block block = event.getBlock();

		if (player.getWorld().equals(MurgeData.getWorld())) {
			if (StatsHandler.inSpectators(uuid)) {
				StringMethods.sendMessage((String) Murge.mMap
						.getStat(MessagesValues.SPECTATE_CANNOTBREAKBLOCKS),
						player);
				event.setCancelled(true);
			}

			Location tree = block.getLocation();
			tree.setY(tree.getY() - 1);
			switch (tree.getBlock().getType()) {
			case DIRT:
				
				break;

			case GRASS:

				break;

			case LOG:

				break;
			default:
				break;
			}

			if (MurgeData.isPurge()) {
				if (!MiscCollection.allowBlockBreak()) {
					StringMethods.sendMessage((String) Murge.mMap
							.getStat(MessagesValues.PLAYER_CANNOTBREAKBLOCKS),
							player);
					event.setCancelled(true);
				}
			} else {
				OreRHandler ore = null;
				switch (block.getType()) {
				case GOLD_ORE:
					ore = new OreRHandler(location, Oretype.GOLD);
					StatsHandler.addOre(ore);
					break;
				case IRON_ORE:
					ore = new OreRHandler(location, Oretype.IRON);
					StatsHandler.addOre(ore);
					break;
				case COAL_ORE:
					ore = new OreRHandler(location, Oretype.COAL);
					StatsHandler.addOre(ore);
					break;
				case LAPIS_ORE:
					ore = new OreRHandler(location, Oretype.LL);
					StatsHandler.addOre(ore);
					break;
				case DIAMOND_ORE:
					ore = new OreRHandler(location, Oretype.DIAMOND);
					StatsHandler.addOre(ore);
					break;
				case REDSTONE_ORE:
					ore = new OreRHandler(location, Oretype.REDSTONE);
					StatsHandler.addOre(ore);
					break;
				case EMERALD_ORE:
					ore = new OreRHandler(location, Oretype.EMERALD);
					StatsHandler.addOre(ore);
					break;
				default:
					break;
				}
			}
		}

		if (world.equals(MurgeData.getWorld())) {
			if (player.equals(MurgeData.getEditingLocation())) {
				if (LocationCollection.isSpawnSet()) {
					if (location.equals(LocationCollection.getSpawn())) {
						StringMethods
								.sendMessage(
										(String) ((String) Murge.mMap
												.getStat(MessagesValues.REMOVE_SPAWN))
												.replace(
														"%x",
														Double.toString(location
																.getX()))
												.replace(
														"%y",
														Double.toString(location
																.getY()))
												.replace(
														"%z",
														Double.toString(location
																.getZ())),
										player);
						player.getInventory().setItem(0,
								EditLocationItems.obtainSpawnLocation());
						LocationCollection.removeSpawn();
					}
				}
			}
		}

		if (world.equals(MurgeData.getWorld())) {
			if (player.equals(MurgeData.getEditingLocation())) {
				if (LocationCollection.isSpectatorSpawnSet()) {
					if (location.equals(LocationCollection.getSpectatorSpawn())) {
						StringMethods
								.sendMessage(
										(String) ((String) Murge.mMap
												.getStat(MessagesValues.REMOVE_SPECTATORSPAWN))
												.replace(
														"%x",
														Double.toString(location
																.getX()))
												.replace(
														"%y",
														Double.toString(location
																.getY()))
												.replace(
														"%z",
														Double.toString(location
																.getZ())),
										player);
						player.getInventory().setItem(1,
								EditLocationItems.obtainSpectatorLocation());
						LocationCollection.removeSpectatorSpawn();
					}
				}
			}
		}
	}
}