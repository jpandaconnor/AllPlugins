package co.uk.RandomPanda30.EnchantInventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.MiscText.InventoryNames;
import co.uk.RandomPanda30.PriceText.Sharpness;

public class SharpnessConfirmInventory {
	
	public static void swordInventory(Player player) {
		Inventory sharpnessEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.confirmEnchant);
		
		ItemStack sharp1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta sharp1IM = sharp1.getItemMeta();
		sharp1IM.setDisplayName(Sharpness.sharp1);
		ArrayList<String> sharp1Lore = new ArrayList<String>();
		sharp1Lore.add(Sharpness.sharp1Price);
		sharp1IM.setLore(sharp1Lore);
		sharp1.setItemMeta(sharp1IM);
		sharpnessEnchant.setItem(0, sharp1);
		
		ItemStack sharp2 = new ItemStack(Material.DIAMOND_SWORD, 2);
		ItemMeta sharp2IM = sharp2.getItemMeta();
		sharp2IM.setDisplayName(Sharpness.sharp2);
		ArrayList<String> sharp2Lore = new ArrayList<String>();
		sharp2Lore.add(Sharpness.sharp2Price);
		sharp2IM.setLore(sharp2Lore);
		sharp2.setItemMeta(sharp2IM);
		sharpnessEnchant.setItem(1, sharp2);
		
		ItemStack sharp3 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta sharp3IM = sharp3.getItemMeta();
		sharp3IM.setDisplayName(Sharpness.sharp3);
		ArrayList<String> sharp3Lore = new ArrayList<String>();
		sharp3Lore.add(Sharpness.sharp3Price);
		sharp3IM.setLore(sharp3Lore);
		sharp3.setItemMeta(sharp3IM);
		sharpnessEnchant.setItem(2, sharp3);
		
		ItemStack sharp4 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta sharp4IM = sharp4.getItemMeta();
		sharp4IM.setDisplayName(Sharpness.sharp4);
		ArrayList<String> sharp4Lore = new ArrayList<String>();
		sharp4Lore.add(Sharpness.sharp4Price);
		sharp4IM.setLore(sharp4Lore);
		sharp4.setItemMeta(sharp4IM);
		sharpnessEnchant.setItem(3, sharp4);
		
		ItemStack sharp5 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta sharp5IM = sharp5.getItemMeta();
		sharp5IM.setDisplayName(Sharpness.sharp5);
		ArrayList<String> sharp5Lore = new ArrayList<String>();
		sharp5Lore.add(Sharpness.sharp5Price);
		sharp5IM.setLore(sharp5Lore);
		sharp5.setItemMeta(sharp5IM);
		sharpnessEnchant.setItem(4, sharp5);
		
		player.openInventory(sharpnessEnchant);
	}
}
