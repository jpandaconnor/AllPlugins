package co.uk.RandomPanda30.EnchantInventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.MiscText.InventoryNames;
import co.uk.RandomPanda30.PriceText.Smite;

public class SmiteConfirmInventory {

	public static void swordInventory(Player player) {
		Inventory smiteEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.confirmEnchant);
		
		ItemStack smite1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta smite1IM = smite1.getItemMeta();
		smite1IM.setDisplayName(Smite.smite1);
		ArrayList<String> smite1Lore = new ArrayList<String>();
		smite1Lore.add(Smite.smite1Price);
		smite1IM.setLore(smite1Lore);
		smite1.setItemMeta(smite1IM);
		smiteEnchant.setItem(0, smite1);
		
		ItemStack smite2 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta smite2IM = smite2.getItemMeta();
		smite2IM.setDisplayName(Smite.smite2);
		ArrayList<String> smite2Lore = new ArrayList<String>();
		smite2Lore.add(Smite.smite2Price);
		smite2IM.setLore(smite2Lore);
		smite2.setItemMeta(smite2IM);
		smiteEnchant.setItem(1, smite2);
		
		ItemStack smite3 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta smite3IM = smite3.getItemMeta();
		smite3IM.setDisplayName(Smite.smite3);
		ArrayList<String> smite3Lore = new ArrayList<String>();
		smite3Lore.add(Smite.smite3Price);
		smite3IM.setLore(smite3Lore);
		smite3.setItemMeta(smite3IM);
		smiteEnchant.setItem(2, smite3);
		
		ItemStack smite4 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta smite4IM = smite4.getItemMeta();
		smite4IM.setDisplayName(Smite.smite4);
		ArrayList<String> smite4Lore = new ArrayList<String>();
		smite4Lore.add(Smite.smite4Price);
		smite4IM.setLore(smite4Lore);
		smite4.setItemMeta(smite4IM);
		smiteEnchant.setItem(3, smite4);
		
		ItemStack smite5 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta smite5IM = smite5.getItemMeta();
		smite5IM.setDisplayName(Smite.smite5);
		ArrayList<String> smite5Lore = new ArrayList<String>();
		smite5Lore.add(Smite.smite5Price);
		smite5IM.setLore(smite5Lore);
		smite5.setItemMeta(smite5IM);
		smiteEnchant.setItem(4, smite5);
		
		player.openInventory(smiteEnchant);
	}

}
