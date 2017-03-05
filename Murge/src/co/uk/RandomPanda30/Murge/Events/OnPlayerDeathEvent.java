package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.MiscCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.DeathsCollection;
import co.uk.RandomPanda30.Murge.Collection.Special.BungeeCollection;
import co.uk.RandomPanda30.Murge.Collection.World.LocationCollection;
import co.uk.RandomPanda30.Murge.Handlers.BungeeHandler;
import co.uk.RandomPanda30.Murge.Handlers.ScoreboardHandler;
import co.uk.RandomPanda30.Murge.Handlers.SpawnHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnPlayerDeathEvent implements Listener {

	private BungeeCollection bungeeC = new BungeeCollection();
	private BungeeHandler bungeeH = new BungeeHandler();

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		Player player = null;
		UUID uuid = null;

		if (event.getEntity() instanceof Player) {
			player = (Player) event.getEntity();
			uuid = player.getUniqueId();

			if (MurgeData.isPurge()) {
				if (StatsHandler.inCombatLog(uuid)) {
					StatsHandler.removeCombatLog(uuid);
				}

				if (MiscCollection.isStrikeOnDeath()) {
					MurgeData.getWorld().strikeLightningEffect(
							player.getLocation());
				}

				event.setDeathMessage(StringMethods
						.formatMessage(((String) Murge.mMap
								.getStat(MessagesValues.DIED)).replace(
								"%player", player.getName())));
				StringMethods.sendMessageToWorldPlayers(((String) Murge.mMap
						.getStat(MessagesValues.MOVEDTO_SPECTATE)).replace(
						"%player", player.getName()));

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

				if (bungeeC.isEnabled()) {
					if (bungeeC.getSendDelay() != 0) {
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (players.getWorld().equals(MurgeData.getWorld())) {
								if (StatsHandler.inSpectators(uuid)) {
									players.showPlayer(player);
								} else {
									players.hidePlayer(player);
								}
							}
						}
						bungeeH.sendToLobby(player, true);
					} else {
						bungeeH.sendToLobby(player, false);
					}
				} else {
					player.spigot().respawn();

					SpawnHandler.getHandler().respawnAsSpectator(player);

					for (Player players : Bukkit.getOnlinePlayers()) {
						if (players.getWorld().equals(MurgeData.getWorld())) {
							if (StatsHandler.inSpectators(uuid)) {
								players.showPlayer(player);
							} else {
								players.hidePlayer(player);
							}
						}
					}
				}

				DeathsCollection.getCollection().addValue(uuid);

			} else {
				event.setDeathMessage(StringMethods
						.formatMessage((String) ((String) Murge.mMap
								.getStat(MessagesValues.DIED)).replace(
								"%player", player.getName())));
				if (MiscCollection.shouldRespawnAsSpectator()) {
					player.spigot().respawn();
					SpawnHandler.getHandler().respawnAsSpectator(player);
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (players.getWorld().equals(MurgeData.getWorld())) {
							if (StatsHandler.inSpectators(uuid)) {
								players.showPlayer(player);
							} else {
								players.hidePlayer(player);
							}
						}
					}
				} else {
					if (LocationCollection.isSpawnSet()) {
						player.teleport(LocationCollection.getSpawn());
					} else {
						player.teleport(MurgeData.getWorld().getSpawnLocation());
					}
				}
			}
			ScoreboardHandler.getHandler().doScoreboard(player);
		}
	}
}