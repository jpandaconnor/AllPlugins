package co.uk.RandomPanda30.ItemInventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.EnchantmentText.BowText;
import co.uk.RandomPanda30.EnchantmentText.UnbreakingText;
import co.uk.RandomPanda30.MiscText.BackText;
import co.uk.RandomPanda30.MiscText.InventoryNames;

public class BowInventory {
	
	public static void bowEnchant(Player player) {
		Inventory bowEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.bowEnchantName);

		ItemStack unbreak = new ItemStack(Material.BOW, 1);
		ItemMeta unbreakIM = unbreak.getItemMeta();
		unbreakIM.setDisplayName(UnbreakingText.unbreaking);
		ArrayList<String> unbreakLore = new ArrayList<String>();
		unbreakLore.add(UnbreakingText.unbreakingLore1);
		unbreakLore.add(UnbreakingText.unbreakingLore2);
		unbreakIM.setLore(unbreakLore);
		unbreak.setItemMeta(unbreakIM);
		bowEnchant.setItem(0, unbreak);

		ItemStack power = new ItemStack(Material.BOW, 1);
		ItemMeta powerIM = power.getItemMeta();
		powerIM.setDisplayName(BowText.power);
		ArrayList<String> powerLore = new ArrayList<String>();
		powerLore.add(BowText.powerLore1);
		powerLore.add(BowText.powerLore2);
		powerIM.setLore(powerLore);
		power.setItemMeta(powerIM);
		bowEnchant.setItem(1, power);

		ItemStack punch = new ItemStack(Material.BOW, 1);
		ItemMeta punchIM = punch.getItemMeta();
		punchIM.setDisplayName(BowText.punch);
		ArrayList<String> punchLore = new ArrayList<String>();
		punchLore.add(BowText.punchLore1);
		punchLore.add(BowText.powerLore2);
		punchIM.setLore(punchLore);
		punch.setItemMeta(punchIM);
		bowEnchant.setItem(2, punch);

		ItemStack flame = new ItemStack(Material.BOW, 1);
		ItemMeta flameIM = flame.getItemMeta();
		flameIM.setDisplayName(BowText.flame);
		ArrayList<String> flameLore = new ArrayList<String>();
		flameLore.add(BowText.flameLore1);
		flameLore.add(BowText.flameLore2);
		flameIM.setLore(flameLore);
		flame.setItemMeta(flameIM);
		bowEnchant.setItem(3, flame);

		ItemStack inf = new ItemStack(Material.BOW, 1);
		ItemMeta infIM = inf.getItemMeta();
		infIM.setDisplayName(BowText.inf);
		ArrayList<String> infLore = new ArrayList<String>();
		infLore.add(BowText.infLore1);
		infLore.add(BowText.infLore2);
		infIM.setLore(infLore);
		inf.setItemMeta(infIM);
		bowEnchant.setItem(4, inf);

		ItemStack infEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta infEnchantBACKIM = infEnchantBACK.getItemMeta();
		infEnchantBACKIM.setDisplayName(BackText.back);
		infEnchantBACK.setItemMeta(infEnchantBACKIM);
		bowEnchant.setItem(8, infEnchantBACK);

		player.openInventory(bowEnchant);
	}

}
