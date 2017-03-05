package co.uk.RandomPanda30.DShops.Events;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Items.I;
import co.uk.RandomPanda30.DShops.Items.IB;
import co.uk.RandomPanda30.DShops.Items.IC;
import co.uk.RandomPanda30.DShops.Items.ID;
import co.uk.RandomPanda30.DShops.Items.IF;
import co.uk.RandomPanda30.DShops.Methods.K;
import co.uk.RandomPanda30.DShops.Methods.L;
import co.uk.RandomPanda30.DShops.Methods.M;
import co.uk.RandomPanda30.DShops.Methods.O;
import co.uk.RandomPanda30.DShops.Methods.P;
import co.uk.RandomPanda30.DShops.Methods.PA;
import co.uk.RandomPanda30.DShops.Methods.R;
import co.uk.RandomPanda30.DShops.Methods.S;
import co.uk.RandomPanda30.DShops.Methods.T;
import co.uk.RandomPanda30.DShops.Methods.TA;
import co.uk.RandomPanda30.DShops.Methods.TB;
import co.uk.RandomPanda30.DShops.Methods.U;

public class EB implements Listener {

	static ArrayList<String> invs = new ArrayList<String>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory inventory = event.getInventory();
		ItemStack item = event.getCurrentItem();

		if (item == null || item.equals(Material.AIR)) {
			return;
		}

		for (String s : getInventories()) {
			if (inventory.getName().toString().contains(s)
					|| inventory.getName().equals(s)) {
				event.setCancelled(true);
			}
		}

		if (item.equals(I.obtainExitButton())) {
			player.closeInventory();
			B.inInv.remove(player.getUniqueId());
		}

		if (item.getType().equals(Material.EMERALD_BLOCK)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta()
						.getDisplayName()
						.equals(L.formatMessage((String) B.itemsC
								.get("FORSALEINV.FORSALE.NAME")))) {
			if (B.inInv.containsKey(player.getUniqueId())) {
				int count = 0;
				for (String s : B.doorsC.getConfigurationSection("").getKeys(
						false)) {
					if (B.doorsC.getString(s + ".owner.uuid") != null) {
						if (UUID.fromString(
								(String) B.doorsC.get(s + ".owner.uuid"))
								.equals(player.getUniqueId())) {
							count++;
						}
					}
				}

				if (!(count + 1 > P.getDoorThreshold())) {
					String lstring = B.inInv.get(player.getUniqueId());
					if (U.econ.getBalance(player) >= (P.isCustomPrice(lstring) ? P
							.getCustomPrice(lstring) : P.getDoorPrice())) {
						P.buyDoor(M.decompileLocation(lstring), player);
						U.withdrawMoney(
								(P.isCustomPrice(lstring) ? P
										.getCustomPrice(lstring) : P
										.getDoorPrice()), player);
						String m = (String) B.messagesC
								.get("DSHOPS.DOORBOUGHT");
						L.sendMessage(m.replaceAll("'price'", Integer
								.toString((P.isCustomPrice(lstring) ? P
										.getCustomPrice(lstring) : P
										.getDoorPrice()))), player);
					} else {
						L.sendMessage(
								(String) B.messagesC.get("CRITICAL.NOTENOUGH"),
								player);
					}
					player.closeInventory();
					B.inInv.remove(player.getUniqueId());
				} else {
					L.sendMessage(
							(String) B.messagesC.get("CRITICAL.TOOMANYDOORS"),
							player);
					player.closeInventory();
					B.inInv.remove(player.getUniqueId());
				}
			}
		}

		if (item.equals(IB.obtainOpenDoor())) {
			if (B.inInv.containsKey(player.getUniqueId())) {
				Location loc = M.decompileLocation(B.inInv.get(player
						.getUniqueId()));
				Block block = loc.getBlock();
				if (block.getData() >= 8) {
					block = block.getRelative(BlockFace.DOWN);
				}

				if (block.getData() < 4) {
					block.setData((byte) (block.getData() + 4));
				}
			}
		}

		if (item.equals(IB.obtainCloseDoor())) {
			if (B.inInv.containsKey(player.getUniqueId())) {
				Location loc = M.decompileLocation(B.inInv.get(player
						.getUniqueId()));
				Block block = loc.getBlock();
				if (block.getData() >= 8) {
					block = block.getRelative(BlockFace.DOWN);
				}

				if (!(block.getData() < 4)) {
					block.setData((byte) (block.getData() - 4));
				}
			}
		}

		if (item.equals(IF.obtainPlusButton_DOORPRICE())) {
			// Checks here
			P.addToCP(B.inInv.get(player.getUniqueId()), 10);
			K.saveDoors();
			O.openDoorSettings(player);
		}

		if (item.equals(IF.obtainMinusButton_DOORPRICE())) {
			// Checks here
			P.minusToCP(B.inInv.get(player.getUniqueId()), 10);
			K.saveDoors();
			O.openDoorSettings(player);
		}

		if (item.equals(IB.obtainEditFriends())) {
			S.openEditFriendsInventory(player);
		}

		if (item.equals(IC.obtainAddFriend())) {
			T.openSearchInventory(player);
		}

		if (item.equals(IC.obtainRemoveFriend())) {
			R.openFriendsList(player);
		}

		if (item.equals(ID.obtainSearchWithLetter())) {
			TA.openPreSearch(player);
		}

		if (item.equals(I.obtainNext())) {
			T.nextPage(player);
		}

		if (item.equals(I.obtainPrevious())) {
			T.previousPage(player);
		}

		if (item.getType().equals(Material.EMERALD_BLOCK)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta()
						.getDisplayName()
						.equals(L.formatMessage((String) B.itemsC
								.get("FORRENTINV.FORRENT.NAME")))) {
			int count = 0;
			for (String s : B.doorsC.getConfigurationSection("").getKeys(false)) {
				if (B.doorsC.getString(s + ".owner.uuid") != null) {
					if (UUID.fromString(
							(String) B.doorsC.get(s + ".owner.uuid")).equals(
							player.getUniqueId())) {
						count++;
					}
				}
			}

			if (!(count + 1 > P.getDoorThreshold())) {
				String lstring = B.inInv.get(player.getUniqueId());
				PA.rentDoor(M.decompileLocation(lstring), player);
				U.withdrawMoney(PA.getRentPrice(), player);
				String message = (String) B.messagesC
						.get("DSHOPS.DOORRENTED")
						.toString()
						.replaceAll("'dayno'",
								Integer.toString(PA.getRentDays()));
				message = message.replaceAll("'price'", Integer.toString((P
						.isCustomPrice(B.inInv.get(player.getUniqueId())) ? P
						.getCustomPrice(B.inInv.get(player.getUniqueId())) : PA
						.getRentPrice())));
				L.sendMessage(message, player);
				player.closeInventory();
				B.inInv.remove(player.getUniqueId());
			} else {
				L.sendMessage(
						(String) B.messagesC.get("CRITICAL.TOOMANYDOORS"),
						player);
				player.closeInventory();
				B.inInv.remove(player.getUniqueId());
			}
		}

		if (item.equals(IB.obtainSellDoor())) {
			if (inventory.getName().equals("Edit this rented door!")) {
				String lstring = B.inInv.get(player.getUniqueId());
				String time = (String) B.doorsC.get(lstring + ".time");

				long days = 0;
				long cTime = Calendar.getInstance().getTimeInMillis();
				long finTime = Long.parseLong(time) - cTime;

				days = finTime / 1000 / 60 / 60 / 24;

				int deduction = (P.isCustomPrice(B.inInv.get(player
						.getUniqueId())) ? P.getCustomPrice(B.inInv.get(player
						.getUniqueId())) : PA.getRentPrice())
						- ((P.isCustomPrice(B.inInv.get(player.getUniqueId())) ? P
								.getCustomPrice(B.inInv.get(player
										.getUniqueId())) : PA.getRentPrice()) / (int) days);
				PA.sellRentDoor(B.inInv.get(player.getUniqueId()), deduction,
						player);
				String message = (String) B.messagesC.get("DSHOPS.DOORSOLD");
				String newMessage = message.replaceAll("'price'",
						Integer.toString(deduction));
				L.sendMessage(newMessage, player);

				player.closeInventory();
				B.inInv.remove(player.getUniqueId());
			} else {
				P.sellDoor(player, B.inInv.get(player.getUniqueId()));
				int price = (P.isCustomPrice(B.inInv.get(player.getUniqueId())) ? P
						.getCustomPrice(B.inInv.get(player.getUniqueId())) : P
						.getDoorPrice()) / 10;
				int newPrice = (P.isCustomPrice(B.inInv.get(player
						.getUniqueId())) ? P.getCustomPrice(B.inInv.get(player
						.getUniqueId())) : P.getDoorPrice())
						- price;
				String message = (String) B.messagesC.get("DSHOPS.DOORSOLD");
				String newMessage = message.replaceAll("'price'",
						Integer.toString(newPrice));
				L.sendMessage(newMessage, player);
				player.closeInventory();
				B.inInv.remove(player.getUniqueId());
			}
		}

		if (item.equals(I.obtainDoorSettings())) {
			O.openDoorSettings(player);
		}

		if (item.getType().equals(Material.REDSTONE_TORCH_ON)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta()
						.getDisplayName()
						.equals(L.formatMessage((String) B.itemsC
								.get("RENTEDINV.PAYRENT.NAME")))) {
			U.withdrawMoney(PA.getRentPrice(), player);
			PA.updateRentTime(B.inInv.get(player.getUniqueId()), player);
			L.sendMessage((String) B.messagesC.get("DSHOPS.RENTUPDATED"),
					player);
			player.closeInventory();
			B.inInv.remove(player.getUniqueId());
		}

		if (item.getType().equals(Material.SKULL_ITEM)) {
			if (inventory.getName().equals("Choose a letter")) {
				TB.openLetterSearch(player, item.getItemMeta().getDisplayName()
						.toString());
				event.setCancelled(true);
			}

			if (inventory.getName().contains("Search by letter (")
					|| inventory.getName().contains("Search for player (")) {
				Player target = Bukkit.getPlayer(item.getItemMeta()
						.getDisplayName());
				String loc = B.inInv.get(player.getUniqueId());
				if (target != null) {
					P.addFriend(target, loc);
					L.sendMessage(
							(String) B.messagesC.get("DSHOPS.FRIENDADDED"),
							player);
					player.closeInventory();
					B.inInv.remove(player.getUniqueId());
				}
				event.setCancelled(true);
			}

			if (inventory.getName().contains("Friends list")) {
				String loc = B.inInv.get(player.getUniqueId());
				String uuid = ChatColor.stripColor(
						item.getItemMeta().getLore().get(0)).replaceAll(
						"UUID: ", "");
				String name = item.getItemMeta().getDisplayName();
				String finalS = M.compileDetails(UUID.fromString(uuid), name);
				P.removeFriend(finalS, loc);
				L.sendMessage((String) B.messagesC.get("DSHOPS.FRIENDREMOVED"),
						player);
				player.closeInventory();
				B.inInv.remove(player.getUniqueId());
			}
		}

	}

	public static ArrayList<String> getInventories() {
		invs.add("Search for player (");
		invs.add("Search by letter (");
		invs.add("Choose a letter");
		invs.add("Friends list");
		invs.add("Edit friends");
		invs.add("Buy this door!");
		invs.add("Edit this door!");
		invs.add("Rent this door!");
		invs.add("Edit this rented door!");
		invs.add("Edit door settings");
		return invs;
	}
}
