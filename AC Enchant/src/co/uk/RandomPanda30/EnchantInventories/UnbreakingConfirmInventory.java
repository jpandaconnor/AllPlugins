package co.uk.RandomPanda30.EnchantInventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.MiscText.InventoryNames;
import co.uk.RandomPanda30.PriceText.Unbreaking;

public class UnbreakingConfirmInventory {

	public static void swordInventory(Player player) {
		Inventory unbreakingEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.confirmEnchant);

		ItemStack unbreak1 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta unbreak1IM = unbreak1.getItemMeta();
		unbreak1IM.setDisplayName(Unbreaking.unbreaking1);
		ArrayList<String> unbreak1Lore = new ArrayList<String>();
		unbreak1Lore.add(Unbreaking.unbreaking1Price);
		unbreak1IM.setLore(unbreak1Lore);
		unbreak1.setItemMeta(unbreak1IM);

		ItemStack unbreak2 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta unbreak2IM = unbreak2.getItemMeta();
		unbreak2IM.setDisplayName(Unbreaking.unbreaking1);
		ArrayList<String> unbreak2Lore = new ArrayList<String>();
		unbreak2Lore.add(Unbreaking.unbreaking2Price);
		unbreak2IM.setLore(unbreak2Lore);
		unbreak2.setItemMeta(unbreak2IM);

		ItemStack unbreak3 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta unbreak3IM = unbreak3.getItemMeta();
		unbreak3IM.setDisplayName(Unbreaking.unbreaking1);
		ArrayList<String> unbreak3Lore = new ArrayList<String>();
		unbreak3Lore.add(Unbreaking.unbreaking3Price);
		unbreak3IM.setLore(unbreak3Lore);
		unbreak3.setItemMeta(unbreak3IM);

		player.openInventory(unbreakingEnchant);
	}

}
