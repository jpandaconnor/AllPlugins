package co.uk.RandomPanda30.ItemInventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.EnchantmentText.EnchantmentText;
import co.uk.RandomPanda30.MiscText.DonateText;
import co.uk.RandomPanda30.MiscText.MainMenuText;

public class MainInventory {

	public static void mainMenu(Player player) {
		Inventory mainMenu = Bukkit.createInventory(null, 9,
				MainMenuText.mainMenu);

		ItemStack donate = new ItemStack(Material.EMERALD, 1);
		ItemMeta donateIM = donate.getItemMeta();
		donateIM.setDisplayName(DonateText.donateName);
		ArrayList<String> Donatelores = new ArrayList<String>();
		Donatelores.add(DonateText.donateLore1);
		Donatelores.add(DonateText.donateLore2);
		Donatelores.add(DonateText.donateLore3);
		donateIM.setLore(Donatelores);
		donate.setItemMeta(donateIM);
		mainMenu.setItem(8, donate);

		ItemStack enchant = new ItemStack(Material.EXP_BOTTLE, 1);
		ItemMeta enchantIM = enchant.getItemMeta();
		enchantIM.setDisplayName(EnchantmentText.enchantmentName);
		ArrayList<String> EnchantLores = new ArrayList<String>();
		EnchantLores.add(EnchantmentText.enchantLore1);
		EnchantLores.add(EnchantmentText.enchantLore2);
		EnchantLores.add(EnchantmentText.enchantLore3);
		enchantIM.setLore(EnchantLores);
		enchant.setItemMeta(enchantIM);
		mainMenu.setItem(0, enchant);

		player.openInventory(mainMenu);
	}
}