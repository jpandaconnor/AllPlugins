package co.uk.RandomPanda30.Inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Text.BackText;
import co.uk.RandomPanda30.Text.BlastProtection;
import co.uk.RandomPanda30.Text.FeatherFallingText;
import co.uk.RandomPanda30.Text.FireProtectionText;
import co.uk.RandomPanda30.Text.InventoryNames;
import co.uk.RandomPanda30.Text.ProjectileProtection;
import co.uk.RandomPanda30.Text.ProtectionText;
import co.uk.RandomPanda30.Text.ThornsText;

public class BootsInventory {
	
	public static void bootEnchant(Player player) {
		Inventory bootEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.bootEnchantName);

		ItemStack pro = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta proIM = pro.getItemMeta();
		proIM.setDisplayName(ProtectionText.pro);
		ArrayList<String> proLore = new ArrayList<String>();
		proLore.add(ProtectionText.proLore1);
		proLore.add(ProtectionText.proLore2);
		proIM.setLore(proLore);
		pro.setItemMeta(proIM);
		bootEnchant.setItem(0, pro);

		ItemStack firePro = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta fireProIM = firePro.getItemMeta();
		fireProIM.setDisplayName(FireProtectionText.firePro);
		ArrayList<String> fireLore = new ArrayList<String>();
		fireLore.add(FireProtectionText.fireProLore1);
		fireLore.add(FireProtectionText.fireProLore2);
		fireProIM.setLore(fireLore);
		firePro.setItemMeta(fireProIM);
		bootEnchant.setItem(1, firePro);

		ItemStack ff = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta ffIM = ff.getItemMeta();
		ffIM.setDisplayName(FeatherFallingText.ff);
		ArrayList<String> ffLore = new ArrayList<String>();
		ffLore.add(FeatherFallingText.ffLore1);
		ffLore.add(FeatherFallingText.ffLore2);
		ffIM.setLore(ffLore);
		ff.setItemMeta(ffIM);
		bootEnchant.setItem(2, ff);

		ItemStack blastPro = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta blastProIM = blastPro.getItemMeta();
		blastProIM.setDisplayName(BlastProtection.blastPro);
		ArrayList<String> blastLore = new ArrayList<String>();
		blastLore.add(BlastProtection.blastProLore1);
		blastLore.add(BlastProtection.blastProLore2);
		blastProIM.setLore(blastLore);
		blastPro.setItemMeta(blastProIM);
		bootEnchant.setItem(3, blastPro);

		ItemStack pp = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta ppIM = pp.getItemMeta();
		ppIM.setDisplayName(ProjectileProtection.proPro);
		ArrayList<String> ppLore = new ArrayList<String>();
		ppLore.add(ProjectileProtection.proProLore1);
		ppLore.add(ProjectileProtection.proProLore2);
		ppIM.setLore(ppLore);
		pp.setItemMeta(ppIM);
		bootEnchant.setItem(4, pp);

		ItemStack thorns = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta thornsIM = thorns.getItemMeta();
		thornsIM.setDisplayName(ThornsText.thorns);
		ArrayList<String> thornsLore = new ArrayList<String>();
		thornsLore.add(ThornsText.thornsLore1);
		thornsLore.add(ThornsText.thornsLore2);
		thornsIM.setLore(thornsLore);
		thorns.setItemMeta(thornsIM);
		bootEnchant.setItem(5, thorns);

		ItemStack infEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta infEnchantBACKIM = infEnchantBACK.getItemMeta();
		infEnchantBACKIM.setDisplayName(BackText.back);
		infEnchantBACK.setItemMeta(infEnchantBACKIM);
		bootEnchant.setItem(8, infEnchantBACK);

		player.openInventory(bootEnchant);

	}

}
