package co.uk.RandomPanda30.Murge.Events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Special.BungeeCollection;
import co.uk.RandomPanda30.Murge.Collection.World.LocationCollection;
import co.uk.RandomPanda30.Murge.Displays.AdjustFlightMenu;
import co.uk.RandomPanda30.Murge.Handlers.BungeeHandler;
import co.uk.RandomPanda30.Murge.Handlers.PlayerDataHandler;
import co.uk.RandomPanda30.Murge.Handlers.SpawnHandler;
import co.uk.RandomPanda30.Murge.Items.EditLocationItems;
import co.uk.RandomPanda30.Murge.Items.SpectatorItems;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnPlayerInteractEvent implements Listener {

	private BungeeCollection bungeeC = new BungeeCollection();
	private BungeeHandler bungeeH = new BungeeHandler();

	private HashMap<Player, Player> compassTargets;

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		// Block block = event.getClickedBlock();

		if (event.getAction() == Action.RIGHT_CLICK_AIR
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (player.getItemInHand() != null) {

				/*
				 * if (stats.inSpectators(uuid)) { for (Material type :
				 * MurgeData.getInteractionMaterials()) { if
				 * (block.getType().equals(type)) { event.setCancelled(true);
				 * StringMethods.sendMessage( (String) MurgeData.messagesC
				 * .get("CRITCAIL.CANNOTINTERACT"), player); } } }
				 */

				if (player.getItemInHand().equals(
						SpectatorItems.obtainAdjustFly())) {

					AdjustFlightMenu.openAdjustFlightMenu(player);
					event.setCancelled(true);
				}

				if (player.getItemInHand().equals(SpectatorItems.obtainLeave())) {
					if (bungeeC.isEnabled()) {
						if (bungeeC.getSendDelay() != 0) {
							bungeeH.sendToLobby(player, true);
						} else {
							bungeeH.sendToLobby(player, false);
						}
					} else {
						player.kickPlayer(StringMethods.formatMessage(((String) Murge.mMap
								.getStat(MessagesValues.LOGGEDOUT_VIAITEM))));
					}
					event.setCancelled(true);
				}

				if (player.getItemInHand().equals(
						EditLocationItems.obtainExitEdit())) {
					PlayerDataHandler ph = new PlayerDataHandler(null, null,
							null, null, null, null, null, null);
					ph = StatsHandler.getPlayerData(uuid);

					if (!MurgeData.isPurge()) {

						SpawnHandler.getHandler().spawnAsPlayer(player);

						if (ph.pInventory != null) {
							player.getInventory().setContents(ph.pInventory);
						}

						if (ph.pArmor != null) {
							player.getInventory().setArmorContents(ph.pArmor);
						}

						player.updateInventory();
						player.setExp(ph.pEXP);
						player.setLevel(ph.pEXPL);
						player.setHealth(ph.pFood);
						player.setFoodLevel(ph.pFood);
						player.addPotionEffects(ph.pPotionEffects);
						player.setGameMode(ph.pGameMode);

						StatsHandler.removePlayerData(uuid);
						MurgeData.setEditingLocation(null);

						if (LocationCollection.isSpawnSet()) {
							LocationCollection.getSpawn().getBlock()
									.setType(Material.AIR);
							LocationCollection.getSpawn().getBlock().getState()
									.update();
						}

						if (LocationCollection.isSpectatorSpawnSet()) {
							LocationCollection.getSpectatorSpawn().getBlock()
									.setType(Material.AIR);
							LocationCollection.getSpectatorSpawn().getBlock()
									.getState().update();
						}

						StringMethods.sendMessage(((String) Murge.mMap
								.getStat(MessagesValues.LOCATIONS_OUTEDIT)),
								player);
						event.setCancelled(true);
					} else {
						player.getInventory().clear();
						if (ph.pInventory != null) {
							player.getInventory().setContents(ph.pInventory);
						}

						if (ph.pArmor != null) {
							player.getInventory().setArmorContents(ph.pArmor);
						}

						player.updateInventory();
						player.setExp(ph.pEXP);
						player.setLevel(ph.pEXPL);
						player.setHealth(ph.pFood);
						player.setFoodLevel(ph.pFood);
						player.addPotionEffects(ph.pPotionEffects);
						player.setGameMode(ph.pGameMode);

						StatsHandler.removePlayerData(uuid);
						MurgeData.setEditingLocation(null);
						SpawnHandler.getHandler().spawnAsSpectator(player);
						// Message here
						if (LocationCollection.isSpawnSet()) {
							LocationCollection.getSpawn().getBlock()
									.setType(Material.AIR);
							LocationCollection.getSpawn().getBlock().getState()
									.update();
						}

						if (LocationCollection.isSpectatorSpawnSet()) {
							LocationCollection.getSpectatorSpawn().getBlock()
									.setType(Material.AIR);
							LocationCollection.getSpectatorSpawn().getBlock()
									.getState().update();
						}
						event.setCancelled(true);
					}
				}

				if (player.getItemInHand().equals(
						SpectatorItems.obtainCompass())) {
					Player targ = null;
					for (Player player2 : Bukkit.getOnlinePlayers()) {
						UUID newUUID = player2.getUniqueId();
						if (!StatsHandler.inSpectators(newUUID)) {
							if (player2.getLocation().getWorld()
									.equals(MurgeData.getWorld())) {
								if ((player.getWorld() == player2.getWorld())
										&& (player != player2)
										&& ((targ == null) || (player
												.getLocation().distance(
														player2.getLocation()) < targ
												.getLocation().distance(
														player.getLocation())))
										&& (player2.getLocation().distance(
												player.getLocation()) > 0D)) {
									targ = player2;
								}
							}
						}
					}

					if (targ == null) {
						StringMethods.sendMessage(((String) Murge.mMap
								.getStat(MessagesValues.NOTRACK)), player);
						return;
					}
					player.setCompassTarget(targ.getLocation());
					StringMethods.sendMessage((String) ((String) Murge.mMap
							.getStat(MessagesValues.TARGET)).replace("%player",
							targ.getName()), player);
					this.compassTargets.put(player, targ);
					event.setCancelled(true);
				}
			}
		}
	}
}