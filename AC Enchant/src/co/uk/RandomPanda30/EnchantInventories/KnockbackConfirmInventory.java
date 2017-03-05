package co.uk.RandomPanda30.EnchantInventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.EnchantmentText.KnockbackText;
import co.uk.RandomPanda30.MiscText.InventoryNames;
import co.uk.RandomPanda30.PriceText.Knockback;

public class KnockbackConfirmInventory {
	
	public static void knockbackInventory(Player player) {
		Inventory knockbackEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.confirmEnchant);
		
		ItemStack knockback1 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta knockback1IM = knockback1.getItemMeta();
		knockback1IM.setDisplayName(KnockbackText.knockName);
		ArrayList<String> knockback1Lore = new ArrayList<String>();
		knockback1Lore.add(Knockback.knock1);
		knockback1Lore.add(Knockback.knock1Price);
		knockback1IM.setLore(knockback1Lore);
		knockback1.setItemMeta(knockback1IM);
		knockbackEnchant.setItem(0, knockback1);
		
		ItemStack knockback2 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta knockback2IM = knockback2.getItemMeta();
		knockback2IM.setDisplayName(KnockbackText.knockName);
		ArrayList<String> knockback2Lore = new ArrayList<String>();
		knockback2Lore.add(Knockback.knock1);
		knockback2Lore.add(Knockback.knock1Price);
		knockback2IM.setLore(knockback2Lore);
		knockback2.setItemMeta(knockback2IM);
		knockbackEnchant.setItem(1, knockback2);
		
		player.openInventory(knockbackEnchant);
	}

}
