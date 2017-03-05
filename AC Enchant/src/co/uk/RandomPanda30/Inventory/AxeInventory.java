package co.uk.RandomPanda30.Inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Text.BackText;
import co.uk.RandomPanda30.Text.EffText;
import co.uk.RandomPanda30.Text.FortuneText;
import co.uk.RandomPanda30.Text.InventoryNames;
import co.uk.RandomPanda30.Text.SilkText;
import co.uk.RandomPanda30.Text.UnbreakingText;

public class AxeInventory {
	
	public static void axeEnchantment(Player player) {
		Inventory axeEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.axeEnchantName);

		ItemStack eff = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta effIM = eff.getItemMeta();
		effIM.setDisplayName(EffText.eff);
		ArrayList<String> effLore = new ArrayList<String>();
		effLore.add(EffText.effLore1);
		effLore.add(EffText.effLore2);
		effIM.setLore(effLore);
		eff.setItemMeta(effIM);
		axeEnchant.setItem(0, eff);

		ItemStack silk = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta silkIM = silk.getItemMeta();
		silkIM.setDisplayName(SilkText.silk);
		ArrayList<String> silkLores = new ArrayList<String>();
		silkLores.add(SilkText.silkLore1);
		silkLores.add(SilkText.silkLore2);
		silkIM.setLore(silkLores);
		silk.setItemMeta(silkIM);
		axeEnchant.setItem(1, silk);

		ItemStack unbreak = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta unbreakIM = unbreak.getItemMeta();
		unbreakIM.setDisplayName(UnbreakingText.unbreaking);
		ArrayList<String> unbreakLore = new ArrayList<String>();
		unbreakLore.add(UnbreakingText.unbreakingLore1);
		unbreakLore.add(UnbreakingText.unbreakingLore2);
		unbreakIM.setLore(unbreakLore);
		unbreak.setItemMeta(unbreakIM);
		axeEnchant.setItem(2, unbreak);

		ItemStack four = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta fourIM = four.getItemMeta();
		fourIM.setDisplayName(FortuneText.fortune);
		ArrayList<String> fourLore = new ArrayList<String>();
		fourLore.add(FortuneText.fortuneLore1);
		fourLore.add(FortuneText.fortuneLore2);
		fourIM.setLore(fourLore);
		four.setItemMeta(fourIM);
		axeEnchant.setItem(3, four);

		ItemStack axeEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta axeEnchantBACKIM = axeEnchantBACK.getItemMeta();
		axeEnchantBACKIM.setDisplayName(BackText.back);
		axeEnchantBACK.setItemMeta(axeEnchantBACKIM);
		axeEnchant.setItem(8, axeEnchantBACK);

		player.openInventory(axeEnchant);

	}

}
