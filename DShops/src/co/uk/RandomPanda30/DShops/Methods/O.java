package co.uk.RandomPanda30.DShops.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Items.I;
import co.uk.RandomPanda30.DShops.Items.IA;
import co.uk.RandomPanda30.DShops.Items.IB;
import co.uk.RandomPanda30.DShops.Items.IF;

public class O {

	public static void openForSaleInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, "Buy this door!");
		inventory.setItem(0, IA.obtainPaperDetails());
		inventory.setItem(2,
				IA.obtainForSaleButton(B.inInv.get(player.getUniqueId())));
		inventory.setItem(8, I.obtainExitButton());

		player.openInventory(inventory);
	}

	public static void openForSaleInventoryOP(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, "Buy this door!");

		inventory.setItem(0, IA.obtainPaperDetails());
		inventory.setItem(2,
				IA.obtainForSaleButton(B.inInv.get(player.getUniqueId())));
		inventory.setItem(6, I.obtainDoorSettings());
		inventory.setItem(8, I.obtainExitButton());

		player.openInventory(inventory);
	}

	public static void openDoorSettings(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 27,
				"Edit door settings");
		inventory.setItem(0, IF.obtainPlusButton_DOORPRICE());
		inventory.setItem(9,
				IF.obtainDoorPriceIndicator(B.inInv.get(player.getUniqueId())));
		inventory.setItem(18, IF.obtainMinusButton_DOORPRICE());

		player.openInventory(inventory);
	}

	public static void openOwnedInventory(Player player) {
		Inventory inventory = Bukkit
				.createInventory(null, 9, "Edit this door!");
		inventory.setItem(0, IB.obtainOpenDoor());
		inventory.setItem(2, IB.obtainCloseDoor());
		inventory.setItem(4, IB.obtainEditFriends());
		inventory.setItem(6, IB.obtainSellDoor());
		inventory.setItem(8, I.obtainExitButton());
		player.openInventory(inventory);
	}

	public static void openFriendInventory(Player player) {
		Inventory inventory = Bukkit
				.createInventory(null, 9, "Edit this door!");
		inventory.setItem(0, IB.obtainOpenDoor());
		inventory.setItem(2, IB.obtainCloseDoor());
		inventory.setItem(8, I.obtainExitButton());
		player.openInventory(inventory);
	}
}