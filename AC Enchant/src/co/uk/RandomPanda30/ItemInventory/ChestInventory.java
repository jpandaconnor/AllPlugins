package co.uk.RandomPanda30.ItemInventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.EnchantmentText.BlastProtection;
import co.uk.RandomPanda30.EnchantmentText.FireProtectionText;
import co.uk.RandomPanda30.EnchantmentText.ProjectileProtection;
import co.uk.RandomPanda30.EnchantmentText.ProtectionText;
import co.uk.RandomPanda30.EnchantmentText.ThornsText;
import co.uk.RandomPanda30.MiscText.BackText;
import co.uk.RandomPanda30.MiscText.InventoryNames;

public class ChestInventory {
	
	public static void chestEnchant(Player player) {
		Inventory chestEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.chestEnchantName);

		ItemStack pro = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta proIM = pro.getItemMeta();
		proIM.setDisplayName(ProtectionText.pro);
		ArrayList<String> proLore = new ArrayList<String>();
		proLore.add(ProtectionText.proLore1);
		proLore.add(ProtectionText.proLore2);
		proIM.setLore(proLore);
		pro.setItemMeta(proIM);
		chestEnchant.setItem(0, pro);

		ItemStack firePro = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta fireProIM = firePro.getItemMeta();
		fireProIM.setDisplayName(FireProtectionText.firePro);
		ArrayList<String> fireLore = new ArrayList<String>();
		fireLore.add(FireProtectionText.fireProLore1);
		fireLore.add(FireProtectionText.fireProLore2);
		fireProIM.setLore(fireLore);
		firePro.setItemMeta(fireProIM);
		chestEnchant.setItem(1, firePro);

		ItemStack blastPro = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta blastProIM = blastPro.getItemMeta();
		blastProIM.setDisplayName(BlastProtection.blastPro);
		ArrayList<String> blastLore = new ArrayList<String>();
		blastLore.add(BlastProtection.blastProLore1);
		blastLore.add(BlastProtection.blastProLore2);
		blastProIM.setLore(blastLore);
		blastPro.setItemMeta(blastProIM);
		chestEnchant.setItem(2, blastPro);

		ItemStack pp = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta ppIM = pp.getItemMeta();
		ppIM.setDisplayName(ProjectileProtection.proPro);
		ArrayList<String> ppLore = new ArrayList<String>();
		ppLore.add(ProjectileProtection.proProLore1);
		ppLore.add(ProjectileProtection.proProLore2);
		ppIM.setLore(ppLore);
		pp.setItemMeta(ppIM);
		chestEnchant.setItem(3, pp);

		ItemStack thorns = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta thornsIM = thorns.getItemMeta();
		thornsIM.setDisplayName(ThornsText.thorns);
		ArrayList<String> thornsLore = new ArrayList<String>();
		thornsLore.add(ThornsText.thornsLore1);
		thornsLore.add(ThornsText.thornsLore2);
		thornsIM.setLore(thornsLore);
		thorns.setItemMeta(thornsIM);
		chestEnchant.setItem(4, thorns);

		ItemStack infEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta infEnchantBACKIM = infEnchantBACK.getItemMeta();
		infEnchantBACKIM.setDisplayName(BackText.back);
		infEnchantBACK.setItemMeta(infEnchantBACKIM);
		chestEnchant.setItem(8, infEnchantBACK);

		player.openInventory(chestEnchant);
	}

}
