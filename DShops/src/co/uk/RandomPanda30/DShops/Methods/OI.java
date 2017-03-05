package co.uk.RandomPanda30.DShops.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Items.I;
import co.uk.RandomPanda30.DShops.Items.IB;
import co.uk.RandomPanda30.DShops.Items.IE;

public class OI {

	public static void openForRentInventory(Player player) {
		Inventory inventory = Bukkit
				.createInventory(null, 9, "Rent this door!");

		inventory.setItem(0, IE.obtainPaperDetails());
		inventory.setItem(2,
				IE.obtainForRentButton(B.inInv.get(player.getUniqueId())));
		inventory.setItem(8, I.obtainExitButton());

		player.openInventory(inventory);
	}

	public static void openForRentInventoryOP(Player player) {
		Inventory inventory = Bukkit
				.createInventory(null, 9, "Rent this door!");

		inventory.setItem(0, IE.obtainPaperDetails());
		inventory.setItem(2,
				IE.obtainForRentButton(B.inInv.get(player.getUniqueId())));
		inventory.setItem(6, I.obtainDoorSettings());
		inventory.setItem(8, I.obtainExitButton());

		player.openInventory(inventory);
	}

	public static void openRentedInventory(Player player, String plotName) {
		Inventory inventory = Bukkit.createInventory(null, 18,
				"Edit this rented door!");
		inventory.setItem(0, IB.obtainOpenDoor());
		inventory.setItem(9, IB.obtainCloseDoor());
		inventory.setItem(2, IB.obtainEditFriends());
		inventory.setItem(4, IE.obtainPayRentButton(plotName));
		inventory.setItem(6, IB.obtainSellDoor());
		inventory.setItem(8, I.obtainExitButton());
		player.openInventory(inventory);
	}
}