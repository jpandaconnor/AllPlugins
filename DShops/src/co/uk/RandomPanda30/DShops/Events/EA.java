package co.uk.RandomPanda30.DShops.Events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Door;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.S;
import co.uk.RandomPanda30.DShops.Methods.L;
import co.uk.RandomPanda30.DShops.Methods.M;
import co.uk.RandomPanda30.DShops.Methods.O;
import co.uk.RandomPanda30.DShops.Methods.OI;
import co.uk.RandomPanda30.DShops.Methods.P;
import co.uk.RandomPanda30.DShops.Methods.PA;

@SuppressWarnings("deprecation")
public class EA implements Listener {

	@SuppressWarnings({ "unchecked" })
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = null;
		ArrayList<String> worlds = (ArrayList<String>) B.configC.get("WORLDS");

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			block = event.getClickedBlock();
			if (player.isSneaking()) {
				block = event.getClickedBlock();
				if (block.getType().equals(Material.IRON_DOOR_BLOCK)) {
					Door door = (Door) block.getState().getData();
					if (worlds.contains(player.getLocation().getWorld()
							.getName().toString())) {
						B.getKeys();
						if (door.isTopHalf()) {
							if (B.keys.contains(M.compileLocation(block
									.getLocation()))) {
								if (P.getDoorState(
										B.doorsC.getString(M
												.compileLocation(block
														.getLocation())
												+ ".state")).equals(S.FORSALE)) {
									B.inInv.put(player.getUniqueId(), M
											.compileLocation(block
													.getLocation()));
									if (!player.hasPermission("dshops.admin")) {
										O.openForSaleInventory(player);
									} else {
										O.openForSaleInventoryOP(player);
									}
								}

								if (P.getDoorState(
										B.doorsC.getString(M
												.compileLocation(block
														.getLocation())
												+ ".state")).equals(S.OWNED)) {
									if (P.getOwner(
											M.compileLocation(block
													.getLocation())).equals(
											player.getUniqueId())) {
										O.openOwnedInventory(player);
										B.inInv.put(player.getUniqueId(), M
												.compileLocation(block
														.getLocation()));
									} else if (P.getFriendsUUID(M
											.compileLocation(block
													.getLocation())) != null
											&& P.getFriendsUUID(
													M.compileLocation(block
															.getLocation()))
													.contains(
															player.getUniqueId())) {
										B.inInv.put(player.getUniqueId(), M
												.compileLocation(block
														.getLocation()));
										O.openFriendInventory(player);
									} else {
										L.sendMessage((String) B.messagesC
												.get("CRITICAL.NOTOWNER"),
												player);
									}
								}
							} else {
								P.addDoorDefaults(M.compileLocation(block
										.getLocation()));
								B.inInv.put(player.getUniqueId(),
										M.compileLocation(block.getLocation()));
								if (!player.hasPermission("dshops.admin")) {
									O.openForSaleInventory(player);
								} else {
									O.openForSaleInventoryOP(player);
								}
							}
						}
					}
				}

				if (block.getType().equals(Material.WOODEN_DOOR)) {
					Door door = (Door) block.getState().getData();
					if (worlds.contains(player.getLocation().getWorld()
							.getName().toString())) {
						event.setCancelled(true);
						B.getKeys();
						if (door.isTopHalf()) {
							if (B.keys.contains(M.compileLocation(block
									.getLocation()))) {
								if (P.getDoorState(
										B.doorsC.getString(M
												.compileLocation(block
														.getLocation())
												+ ".state")).equals(S.FORRENT)) {
									B.inInv.put(player.getUniqueId(), M
											.compileLocation(block
													.getLocation()));
									if (!player.hasPermission("dshops.admin")) {
										OI.openForRentInventory(player);
									} else {
										OI.openForRentInventoryOP(player);
									}
								}

								if (P.getDoorState(
										B.doorsC.getString(M
												.compileLocation(block
														.getLocation())
												+ ".state")).equals(S.RENTED)) {
									if (P.getOwner(
											M.compileLocation(block
													.getLocation())).equals(
											player.getUniqueId())) {
										OI.openRentedInventory(player, M
												.compileLocation(block
														.getLocation()));
										B.inInv.put(player.getUniqueId(), M
												.compileLocation(block
														.getLocation()));
									} else if (P.getFriendsUUID(M
											.compileLocation(block
													.getLocation())) != null
											&& P.getFriendsUUID(
													M.compileLocation(block
															.getLocation()))
													.contains(
															player.getUniqueId())) {
										B.inInv.put(player.getUniqueId(), M
												.compileLocation(block
														.getLocation()));
										O.openFriendInventory(player);

									} else {
										L.sendMessage((String) B.messagesC
												.get("CRITICAL.NOTOWNER"),
												player);
									}
								}
							} else {
								PA.addDoorDefaults(
										M.compileLocation(block.getLocation()),
										player);
								B.inInv.put(player.getUniqueId(),
										M.compileLocation(block.getLocation()));
								if (!player.hasPermission("dshops.admin")) {
									OI.openForRentInventory(player);
								} else {
									OI.openForRentInventoryOP(player);
								}
							}
						}
					}
				}
			} else {
				if (block.getType().equals(Material.WOODEN_DOOR)) {
					if (worlds.contains(player.getLocation().getWorld()
							.getName().toString())) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}