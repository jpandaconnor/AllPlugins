package co.uk.RandomPanda30.Inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Text.BackText;
import co.uk.RandomPanda30.Text.BoaText;
import co.uk.RandomPanda30.Text.FireAspectText;
import co.uk.RandomPanda30.Text.InventoryNames;
import co.uk.RandomPanda30.Text.KnockbackText;
import co.uk.RandomPanda30.Text.LootingText;
import co.uk.RandomPanda30.Text.SharpnessText;
import co.uk.RandomPanda30.Text.SmiteText;

public class SwordInventory {

	public static void swordInventory(Player player) {
		Inventory swordEnchant = Bukkit.createInventory(null, 9,
				InventoryNames.swordEnchantName.toString());

		ItemStack sharpness = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta sharpnessIM = sharpness.getItemMeta();
		sharpnessIM.setDisplayName(SharpnessText.sharpnessName);
		ArrayList<String> sharpnessLores = new ArrayList<String>();
		sharpnessLores.add(SharpnessText.sharpnessLore1);
		sharpnessLores.add(SharpnessText.sharpnessLore2);
		sharpnessIM.setLore(sharpnessLores);
		sharpness.setItemMeta(sharpnessIM);
		swordEnchant.setItem(0, sharpness);

		ItemStack smite = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta smiteIM = smite.getItemMeta();
		smiteIM.setDisplayName(SmiteText.smiteName);
		ArrayList<String> smiteLores = new ArrayList<String>();
		smiteLores.add(SmiteText.smiteLore1);
		smiteLores.add(SmiteText.smiteLore2);
		smiteIM.setLore(smiteLores);
		smite.setItemMeta(smiteIM);
		swordEnchant.setItem(1, smite);

		ItemStack boa = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta boaIM = boa.getItemMeta();
		boaIM.setDisplayName(BoaText.boaName);
		ArrayList<String> boaLores = new ArrayList<String>();
		boaLores.add(BoaText.boaLore1);
		boaLores.add(BoaText.boaLore2);
		boaLores.add(BoaText.boaLore3);
		boaIM.setLore(boaLores);
		boa.setItemMeta(boaIM);
		swordEnchant.setItem(2, boa);

		ItemStack knockback = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta knockbackIM = knockback.getItemMeta();
		knockbackIM.setDisplayName(KnockbackText.knockName);
		ArrayList<String> knockbackLore = new ArrayList<String>();
		knockbackLore.add(KnockbackText.knockLore1);
		knockbackLore.add(KnockbackText.knockLore2);
		knockbackIM.setLore(knockbackLore);
		knockback.setItemMeta(knockbackIM);
		swordEnchant.setItem(3, knockback);
		// doei
		ItemStack fire = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta fireIM = fire.getItemMeta();
		fireIM.setDisplayName(FireAspectText.fireName);
		ArrayList<String> fireLore = new ArrayList<String>();
		fireLore.add(FireAspectText.fireLore1);
		fireLore.add(FireAspectText.fireLore2);
		fireIM.setLore(fireLore);
		fire.setItemMeta(fireIM);
		swordEnchant.setItem(4, fire);

		ItemStack loot = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta lootIM = loot.getItemMeta();
		lootIM.setDisplayName(LootingText.lootName);
		ArrayList<String> lootLore = new ArrayList<String>();
		lootLore.add(LootingText.lootLore1);
		lootLore.add(LootingText.lootLore2);
		lootIM.setLore(lootLore);
		loot.setItemMeta(lootIM);
		swordEnchant.setItem(5, loot);

		ItemStack swordEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta swordEnchantBACKim = swordEnchantBACK.getItemMeta();
		swordEnchantBACKim.setDisplayName(BackText.back);
		swordEnchantBACK.setItemMeta(swordEnchantBACKim);
		swordEnchant.setItem(8, swordEnchantBACK);

		player.openInventory(swordEnchant);
	}
}
