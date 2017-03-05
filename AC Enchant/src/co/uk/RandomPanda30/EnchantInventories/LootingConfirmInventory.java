package co.uk.RandomPanda30.EnchantInventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.EnchantmentText.LootingText;
import co.uk.RandomPanda30.MiscText.InventoryNames;
import co.uk.RandomPanda30.PriceText.LootMobs;

public class LootingConfirmInventory {
	
	public static void swordInventory(Player player) {
		Inventory lootEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.confirmEnchant);
	
	ItemStack loot1 = new ItemStack(Material.DIAMOND_SWORD);
	ItemMeta loot1IM = loot1.getItemMeta();
	loot1IM.setDisplayName(LootingText.lootName);
	ArrayList<String> loot1Lore = new ArrayList<String>();
	loot1Lore.add(LootMobs.loot1);
	loot1Lore.add(LootMobs.loot1Price);
	loot1IM.setLore(loot1Lore);
	loot1.setItemMeta(loot1IM);
	
	ItemStack loot2 = new ItemStack(Material.DIAMOND_SWORD);
	ItemMeta loot2IM = loot2.getItemMeta();
	loot2IM.setDisplayName(LootingText.lootName);
	ArrayList<String> loot2Lore = new ArrayList<String>();
	loot2Lore.add(LootMobs.loot2);
	loot2Lore.add(LootMobs.loot2Price);
	loot2IM.setLore(loot2Lore);
	loot2.setItemMeta(loot2IM);
	
	ItemStack loot3 = new ItemStack(Material.DIAMOND_SWORD);
	ItemMeta loot3IM = loot3.getItemMeta();
	loot3IM.setDisplayName(LootingText.lootName);
	ArrayList<String> loot3Lore = new ArrayList<String>();
	loot3Lore.add(LootMobs.loot3);
	loot3Lore.add(LootMobs.loot3Price);
	loot3IM.setLore(loot3Lore);
	loot3.setItemMeta(loot3IM);
	
	player.openInventory(lootEnchant);

}
}