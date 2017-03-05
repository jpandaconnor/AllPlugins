package co.uk.RandomPanda30.DShops.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.DShops.Items.I;
import co.uk.RandomPanda30.DShops.Items.IC;

public class S {

	public static void openEditFriendsInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, "Edit friends");

		inventory.setItem(0, IC.obtainAddFriend());
		inventory.setItem(2, IC.obtainRemoveFriend());

		inventory.setItem(8, I.obtainExitButton());

		player.openInventory(inventory);
	}
}