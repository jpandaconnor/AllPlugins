package co.uk.RandomPanda30.Murge.Handlers;

import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Player.DumpCollection;
import co.uk.RandomPanda30.Murge.Collection.World.LocationCollection;
import co.uk.RandomPanda30.Murge.Items.SpectatorItems;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class SpawnHandler {

	private static SpawnHandler rh = new SpawnHandler();

	public static SpawnHandler getHandler() {
		return rh;
	}

	public void spawnAsSpectator(final Player player) {
		MurgeData.getPlugin().getServer().getScheduler()
				.scheduleSyncDelayedTask(MurgeData.getPlugin(), new Runnable() {
					@Override
					public void run() {
						// Any lists here

						StatsHandler.addSpectator(player.getUniqueId());
						StatsHandler.addFlySpeed(player.getUniqueId(), 1);

						// Dump collection here

						if (DumpCollection.getCollection().isPunishDump(
								player.getUniqueId())) {
							StringMethods.sendMessage(
									(String) (String) Murge.mMap
											.getStat(MessagesValues.SINBIN),
									player);
							player.playSound(player.getLocation(),
									Sound.NOTE_BASS, 1, 1);
							DumpCollection.getCollection().removePunish(
									player.getUniqueId());
						}

						DumpCollection.getCollection().dump(
								player.getInventory().getContents(),
								player.getInventory().getArmorContents(),
								player.getUniqueId());

						// Messages here

						StringMethods.sendMessageToWorldPlayers(((String) Murge.mMap
								.getStat(MessagesValues.MOVEDTO_SPECTATE))
								.replace("%player", player.getName()));

						// Inventory manipulation here
						player.getInventory().clear();
						player.getInventory().setItem(0,
								SpectatorItems.obtainCompass());
						player.getInventory().setItem(2,
								SpectatorItems.obtainAdjustFly());
						player.getInventory().setItem(8,
								SpectatorItems.obtainLeave());

						// Teleportation here
						if (LocationCollection.isSpectatorSpawnSet()) {
							player.teleport(LocationCollection
									.getSpectatorSpawn());
						} else {
							Location newSpawn = new Location(MurgeData
									.getWorld(), MurgeData.getWorld()
									.getSpawnLocation().getX(), MurgeData
									.getWorld().getSpawnLocation().getY() + 2,
									MurgeData.getWorld().getSpawnLocation()
											.getZ());
							player.teleport(newSpawn);
						}

						player.setGameMode(GameMode.CREATIVE);
						ScoreboardHandler.getHandler().doScoreboard(player);
					}
				}, 10L);
	}

	public void spawnAsPlayer(final Player player) {
		MurgeData.getPlugin().getServer().getScheduler()
				.scheduleSyncDelayedTask(MurgeData.getPlugin(), new Runnable() {
					@Override
					public void run() {
						StatsHandler.addPlayer(player.getUniqueId());

						player.getInventory().clear();

						if (DumpCollection.getCollection().isPunishDump(
								player.getUniqueId())) {
							StringMethods.sendMessage((String) Murge.mMap
									.getStat(MessagesValues.SINBIN), player);
							player.playSound(player.getLocation(),
									Sound.NOTE_BASS, 1, 1);
							DumpCollection.getCollection().removePunish(
									player.getUniqueId());
						}

						if (DumpCollection.getCollection().isDumped(
								player.getUniqueId())) {
							player.getInventory().setContents(
									DumpCollection.getCollection()
											.getInventoryDump(
													player.getUniqueId()));
							player.getInventory()
									.setArmorContents(
											DumpCollection
													.getCollection()
													.getArmourDump(
															player.getUniqueId()));
						}

						for (Player players : InworldHandler.getHandler()
								.getPlayers()) {
							players.showPlayer(player);
						}

						if (LocationCollection.isSpawnSet()) {
							player.teleport(LocationCollection.getSpawn());
						} else {
							Location newSpawn = new Location(MurgeData
									.getWorld(), MurgeData.getWorld()
									.getSpawnLocation().getX(), MurgeData
									.getWorld().getSpawnLocation().getY() + 2,
									MurgeData.getWorld().getSpawnLocation()
											.getZ());
							player.teleport(newSpawn);
						}

						player.setGameMode(GameMode.SURVIVAL);
						ScoreboardHandler.getHandler().doScoreboard(player);
					}
				}, 10L);
	}

	public void respawnAsSpectator(final Player player) {
		MurgeData.getPlugin().getServer().getScheduler()
				.scheduleSyncDelayedTask(MurgeData.getPlugin(), new Runnable() {
					@Override
					public void run() {

						UUID uuid = player.getUniqueId();

						StatsHandler.removePlayerData(uuid);

						StatsHandler.addSpectator(uuid);
						StatsHandler.addFlySpeed(uuid, 1);

						StatsHandler.removeCombatLog(uuid);

						player.getInventory().clear();
						player.setGameMode(GameMode.CREATIVE);
						player.getInventory().setItem(0,
								SpectatorItems.obtainCompass());
						player.getInventory().setItem(2,
								SpectatorItems.obtainAdjustFly());
						player.getInventory().setItem(8,
								SpectatorItems.obtainLeave());

						if (LocationCollection.isSpectatorSpawnSet()) {
							player.teleport(LocationCollection
									.getSpectatorSpawn());
						} else {
							Location newSpawn = new Location(MurgeData
									.getWorld(), MurgeData.getWorld()
									.getSpawnLocation().getX(), MurgeData
									.getWorld().getSpawnLocation().getY() + 2,
									MurgeData.getWorld().getSpawnLocation()
											.getZ());
							player.teleport(newSpawn);
						}
					}
				}, 10L);
	}
}