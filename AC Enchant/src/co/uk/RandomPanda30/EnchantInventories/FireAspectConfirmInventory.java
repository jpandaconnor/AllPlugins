package co.uk.RandomPanda30.EnchantInventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.MiscText.InventoryNames;
import co.uk.RandomPanda30.PriceText.FireAspect;

public class FireAspectConfirmInventory {
	
	public static void fireAspectInventory(Player player) {
		Inventory fireAspectEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.confirmEnchant);
		
		ItemStack fire1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta fire1IM = fire1.getItemMeta();
		fire1IM.setDisplayName(FireAspect.fireAspect1);
		ArrayList<String> fire1Lore = new ArrayList<String>();
		fire1Lore.add(FireAspect.fireAspect1Price);
		
		ItemStack fire2 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta fire2IM = fire2.getItemMeta();
		fire2IM.setDisplayName(FireAspect.fireAspect1);
		ArrayList<String> fire2Lore = new ArrayList<String>();
		fire2Lore.add(FireAspect.fireAspect2Price);
		
		player.openInventory(fireAspectEnchant);
	}

}
