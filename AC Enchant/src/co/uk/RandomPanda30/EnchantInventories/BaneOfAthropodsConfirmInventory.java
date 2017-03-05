package co.uk.RandomPanda30.EnchantInventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.MiscText.InventoryNames;
import co.uk.RandomPanda30.PriceText.Boa;

public class BaneOfAthropodsConfirmInventory {

	public static void boaInventory(Player player) {
		Inventory boaEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.confirmEnchant);
		
		ItemStack boa1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta boa1IM = boa1.getItemMeta();
		boa1IM.setDisplayName(Boa.boa1);
		ArrayList<String> boa1Lore = new ArrayList<String>();
		boa1Lore.add(Boa.boa1Price);
		boa1IM.setLore(boa1Lore);
		boa1.setItemMeta(boa1IM);
		boaEnchant.setItem(0, boa1);
		
		ItemStack boa2 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta boa2IM = boa2.getItemMeta();
		boa2IM.setDisplayName(Boa.boa2);
		ArrayList<String> boa2Lore = new ArrayList<String>();
		boa2Lore.add(Boa.boa2Price);
		boa2IM.setLore(boa2Lore);
		boa2.setItemMeta(boa2IM);
		boaEnchant.setItem(1, boa2);
		
		ItemStack boa3 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta boa3IM = boa3.getItemMeta();
		boa3IM.setDisplayName(Boa.boa3);
		ArrayList<String> boa3Lore = new ArrayList<String>();
		boa3Lore.add(Boa.boa3Price);
		boa3IM.setLore(boa3Lore);
		boa3.setItemMeta(boa3IM);
		boaEnchant.setItem(2, boa3);
		
		ItemStack boa4 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta boa4IM = boa4.getItemMeta();
		boa4IM.setDisplayName(Boa.boa4);
		ArrayList<String> boa4Lore = new ArrayList<String>();
		boa4Lore.add(Boa.boa4Price);
		boa4IM.setLore(boa4Lore);
		boa4.setItemMeta(boa4IM);
		boaEnchant.setItem(3, boa4);
		
		ItemStack boa5 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta boa5IM = boa5.getItemMeta();
		boa5IM.setDisplayName(Boa.boa5);
		ArrayList<String> boa5Lore = new ArrayList<String>();
		boa5Lore.add(Boa.boa5Price);
		boa5IM.setLore(boa5Lore);
		boa5.setItemMeta(boa5IM);
		boaEnchant.setItem(4, boa5);
		
		player.openInventory(boaEnchant);

	}
}
