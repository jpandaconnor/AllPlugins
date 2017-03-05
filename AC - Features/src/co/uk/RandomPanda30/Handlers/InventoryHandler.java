package co.uk.RandomPanda30.Handlers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Text.TextH;

public class InventoryHandler {

	public static void mainMenu(Player player) {
		Inventory mainMenu = Bukkit.createInventory(null, 9,
				TextH.mainMenuName.toString());

		// Donate Icon
		ItemStack donate = new ItemStack(Material.EMERALD, 1);
		ItemMeta donateIM = donate.getItemMeta();
		donateIM.setDisplayName(TextH.donateButtonName.toString());
		ArrayList<String> Donatelores = new ArrayList<String>();
		Donatelores.add(TextH.Donatelore1.toString());
		Donatelores.add(TextH.Donatelore2.toString());
		donateIM.setLore(Donatelores);
		donate.setItemMeta(donateIM);
		mainMenu.setItem(8, donate);
		// Donate Icon

		// Enchantment icon
		ItemStack enchant = new ItemStack(Material.EXP_BOTTLE, 1);
		ItemMeta enchantIM = enchant.getItemMeta();
		enchantIM.setDisplayName(TextH.enchantName.toString());
		ArrayList<String> EnchantLores = new ArrayList<String>();
		EnchantLores.add(TextH.enchantLore1.toString());
		EnchantLores.add(TextH.enchantLore2.toString());
		enchantIM.setLore(EnchantLores);
		enchant.setItemMeta(enchantIM);
		mainMenu.setItem(0, enchant);
		// Enchantment icon

		player.openInventory(mainMenu);

	}

	public static void swordEnchant(Player player) {
		Inventory swordEnchant = Bukkit.createInventory(null, 9,
				TextH.swordEnchantName.toString());

		ItemStack sharpness = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta sharpnessIM = sharpness.getItemMeta();
		sharpnessIM.setDisplayName(TextH.sharpnessName.toString());
		ArrayList<String> sharpnessLores = new ArrayList<String>();
		sharpnessLores.add(TextH.sharpnessLore1.toString());
		sharpnessLores.add(TextH.sharpnessLore2.toString());
		sharpnessLores.add(TextH.sharpP1.toString());
		sharpnessLores.add(TextH.sharpP2.toString());
		sharpnessLores.add(TextH.sharpP3.toString());
		sharpnessLores.add(TextH.sharpP4.toString());
		sharpnessLores.add(TextH.sharpP5.toString());
		sharpnessIM.setLore(sharpnessLores);
		sharpness.setItemMeta(sharpnessIM);
		swordEnchant.setItem(0, sharpness);

		ItemStack smite = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta smiteIM = smite.getItemMeta();
		smiteIM.setDisplayName(TextH.smiteName.toString());
		ArrayList<String> smiteLores = new ArrayList<String>();
		smiteLores.add(TextH.smiteLore1.toString());
		smiteLores.add(TextH.smiteLore2.toString());
		smiteLores.add(TextH.Smite1.toString());
		smiteLores.add(TextH.Smite2.toString());
		smiteLores.add(TextH.Smite3.toString());
		smiteLores.add(TextH.Smite4.toString());
		smiteLores.add(TextH.Smite5.toString());
		smiteIM.setLore(smiteLores);
		smite.setItemMeta(smiteIM);
		swordEnchant.setItem(1, smite);

		ItemStack boa = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta boaIM = boa.getItemMeta();
		boaIM.setDisplayName(TextH.boa.toString());
		ArrayList<String> boaLores = new ArrayList<String>();
		boaLores.add(TextH.boaLore1.toString());
		boaLores.add(TextH.boaLore2.toString());
		boaLores.add(TextH.Baneofa1.toString());
		boaLores.add(TextH.Baneofa2.toString());
		boaLores.add(TextH.Baneofa3.toString());
		boaLores.add(TextH.Baneofa4.toString());
		boaLores.add(TextH.Baneofa5.toString());
		boaIM.setLore(boaLores);
		boa.setItemMeta(boaIM);
		swordEnchant.setItem(2, boa);

		ItemStack knockback = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta knockbackIM = knockback.getItemMeta();
		knockbackIM.setDisplayName(TextH.knock.toString());
		ArrayList<String> knockbackLore = new ArrayList<String>();
		knockbackLore.add(TextH.knockLore1.toString());
		knockbackLore.add(TextH.knockLore2.toString());
		knockbackLore.add(TextH.knock1.toString());
		knockbackLore.add(TextH.knock2.toString());
		knockbackIM.setLore(knockbackLore);
		knockback.setItemMeta(knockbackIM);
		swordEnchant.setItem(3, knockback);
		// doei
		ItemStack fire = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta fireIM = fire.getItemMeta();
		fireIM.setDisplayName(TextH.fireA.toString());
		ArrayList<String> fireLore = new ArrayList<String>();
		fireLore.add(TextH.fireLore1.toString());
		fireLore.add(TextH.fireLore2.toString());
		fireLore.add(TextH.fire1.toString());
		fireLore.add(TextH.fire2.toString());
		fireIM.setLore(fireLore);
		fire.setItemMeta(fireIM);
		swordEnchant.setItem(4, fire);

		ItemStack loot = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta lootIM = loot.getItemMeta();
		lootIM.setDisplayName(TextH.loot.toString());
		ArrayList<String> lootLore = new ArrayList<String>();
		lootLore.add(TextH.lootLore1.toString());
		lootLore.add(TextH.lootLore2.toString());
		lootLore.add(TextH.loot1.toString());
		lootLore.add(TextH.loot2.toString());
		lootLore.add(TextH.loot3.toString());
		lootIM.setLore(lootLore);
		loot.setItemMeta(lootIM);
		swordEnchant.setItem(5, loot);

		ItemStack swordEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta swordEnchantBACKim = swordEnchantBACK.getItemMeta();
		swordEnchantBACKim.setDisplayName(TextH.back.toString());
		swordEnchantBACK.setItemMeta(swordEnchantBACKim);
		swordEnchant.setItem(8, swordEnchantBACK);

		player.openInventory(swordEnchant);
	}

	public static void pickaxeEnchant(Player player) {
		Inventory pickaxeEnchant = Bukkit.createInventory(null, 9,
				TextH.pickaxeEnchantName.toString());

		ItemStack eff = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta effIM = eff.getItemMeta();
		effIM.setDisplayName(TextH.eff.toString());
		ArrayList<String> effLore = new ArrayList<String>();
		effLore.add(TextH.effLore1.toString());
		effLore.add(TextH.effLore2.toString());
		effLore.add(TextH.Eff1.toString());
		effLore.add(TextH.Eff2.toString());
		effLore.add(TextH.Eff3.toString());
		effLore.add(TextH.Eff4.toString());
		effLore.add(TextH.Eff5.toString());
		effIM.setLore(effLore);
		eff.setItemMeta(effIM);
		pickaxeEnchant.setItem(0, eff);

		ItemStack silk = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta silkIM = silk.getItemMeta();
		silkIM.setDisplayName(TextH.silk.toString());
		ArrayList<String> silkLores = new ArrayList<String>();
		silkLores.add(TextH.silkLore1.toString());
		silkLores.add(TextH.silkLore2.toString());
		silkLores.add(TextH.silk1.toString());
		silkIM.setLore(silkLores);
		silk.setItemMeta(silkIM);
		pickaxeEnchant.setItem(1, silk);

		ItemStack unbreak = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta unbreakIM = unbreak.getItemMeta();
		unbreakIM.setDisplayName(TextH.unbreaking.toString());
		ArrayList<String> unbreakLore = new ArrayList<String>();
		unbreakLore.add(TextH.unbreakingLore1.toString());
		unbreakLore.add(TextH.unbreakingLore2.toString());
		unbreakIM.setLore(unbreakLore);
		unbreak.setItemMeta(unbreakIM);
		pickaxeEnchant.setItem(2, unbreak);

		ItemStack four = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta fourIM = four.getItemMeta();
		fourIM.setDisplayName(TextH.fortune.toString());
		ArrayList<String> fourLore = new ArrayList<String>();
		fourLore.add(TextH.fortuneLore1.toString());
		fourLore.add(TextH.fortuneLore2.toString());
		fourIM.setLore(fourLore);
		four.setItemMeta(fourIM);
		pickaxeEnchant.setItem(3, four);

		ItemStack pickaxeEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta pickaxeEnchantBACKIM = pickaxeEnchantBACK.getItemMeta();
		pickaxeEnchantBACKIM.setDisplayName(TextH.back.toString());
		pickaxeEnchantBACK.setItemMeta(pickaxeEnchantBACKIM);
		pickaxeEnchant.setItem(8, pickaxeEnchantBACK);

		player.openInventory(pickaxeEnchant);

	}

	public static void axeEnchantment(Player player) {
		Inventory axeEnchant = Bukkit.createInventory(null, 9,
				TextH.axeEnchantName.toString());

		ItemStack eff = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta effIM = eff.getItemMeta();
		effIM.setDisplayName(TextH.eff.toString());
		ArrayList<String> effLore = new ArrayList<String>();
		effLore.add(TextH.effLore1.toString());
		effLore.add(TextH.effLore2.toString());
		effLore.add(TextH.Eff1.toString());
		effLore.add(TextH.Eff2.toString());
		effLore.add(TextH.Eff3.toString());
		effLore.add(TextH.Eff4.toString());
		effLore.add(TextH.Eff5.toString());
		effIM.setLore(effLore);
		eff.setItemMeta(effIM);
		axeEnchant.setItem(0, eff);

		ItemStack silk = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta silkIM = silk.getItemMeta();
		silkIM.setDisplayName(TextH.silk.toString());
		ArrayList<String> silkLores = new ArrayList<String>();
		silkLores.add(TextH.silkLore1.toString());
		silkLores.add(TextH.silkLore2.toString());
		silkLores.add(TextH.silk1.toString());
		silkIM.setLore(silkLores);
		silk.setItemMeta(silkIM);
		axeEnchant.setItem(1, silk);

		ItemStack unbreak = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta unbreakIM = unbreak.getItemMeta();
		unbreakIM.setDisplayName(TextH.unbreaking.toString());
		ArrayList<String> unbreakLore = new ArrayList<String>();
		unbreakLore.add(TextH.unbreakingLore1.toString());
		unbreakLore.add(TextH.unbreakingLore2.toString());
		unbreakIM.setLore(unbreakLore);
		unbreak.setItemMeta(unbreakIM);
		axeEnchant.setItem(2, unbreak);

		ItemStack four = new ItemStack(Material.DIAMOND_AXE, 1);
		ItemMeta fourIM = four.getItemMeta();
		fourIM.setDisplayName(TextH.fortune.toString());
		ArrayList<String> fourLore = new ArrayList<String>();
		fourLore.add(TextH.fortuneLore1.toString());
		fourLore.add(TextH.fortuneLore2.toString());
		fourIM.setLore(fourLore);
		four.setItemMeta(fourIM);
		axeEnchant.setItem(3, four);

		ItemStack axeEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta axeEnchantBACKIM = axeEnchantBACK.getItemMeta();
		axeEnchantBACKIM.setDisplayName(TextH.back.toString());
		axeEnchantBACK.setItemMeta(axeEnchantBACKIM);
		axeEnchant.setItem(8, axeEnchantBACK);

		player.openInventory(axeEnchant);

	}

	public static void spadeEnchant(Player player) {
		Inventory spadeEnchant = Bukkit.createInventory(null, 9,
				TextH.spadeEnchantName.toString());

		ItemStack eff = new ItemStack(Material.DIAMOND_SPADE, 1);
		ItemMeta effIM = eff.getItemMeta();
		effIM.setDisplayName(TextH.eff.toString());
		ArrayList<String> effLore = new ArrayList<String>();
		effLore.add(TextH.effLore1.toString());
		effLore.add(TextH.effLore2.toString());
		effLore.add(TextH.Eff1.toString());
		effLore.add(TextH.Eff2.toString());
		effLore.add(TextH.Eff3.toString());
		effLore.add(TextH.Eff4.toString());
		effLore.add(TextH.Eff5.toString());
		effIM.setLore(effLore);
		eff.setItemMeta(effIM);
		spadeEnchant.setItem(0, eff);

		ItemStack silk = new ItemStack(Material.DIAMOND_SPADE, 1);
		ItemMeta silkIM = silk.getItemMeta();
		silkIM.setDisplayName(TextH.silk.toString());
		ArrayList<String> silkLores = new ArrayList<String>();
		silkLores.add(TextH.silkLore1.toString());
		silkLores.add(TextH.silkLore2.toString());
		silkLores.add(TextH.silk1.toString());
		silkIM.setLore(silkLores);
		silk.setItemMeta(silkIM);
		spadeEnchant.setItem(1, silk);

		ItemStack unbreak = new ItemStack(Material.DIAMOND_SPADE, 1);
		ItemMeta unbreakIM = unbreak.getItemMeta();
		unbreakIM.setDisplayName(TextH.unbreaking.toString());
		ArrayList<String> unbreakLore = new ArrayList<String>();
		unbreakLore.add(TextH.unbreakingLore1.toString());
		unbreakLore.add(TextH.unbreakingLore2.toString());
		unbreakIM.setLore(unbreakLore);
		unbreak.setItemMeta(unbreakIM);
		spadeEnchant.setItem(2, unbreak);

		ItemStack four = new ItemStack(Material.DIAMOND_SPADE, 1);
		ItemMeta fourIM = four.getItemMeta();
		fourIM.setDisplayName(TextH.fortune.toString());
		ArrayList<String> fourLore = new ArrayList<String>();
		fourLore.add(TextH.fortuneLore1.toString());
		fourLore.add(TextH.fortuneLore2.toString());
		fourIM.setLore(fourLore);
		four.setItemMeta(fourIM);
		spadeEnchant.setItem(3, four);

		ItemStack spadeEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta spadeEnchantBACKIM = spadeEnchantBACK.getItemMeta();
		spadeEnchantBACKIM.setDisplayName(TextH.back.toString());
		spadeEnchantBACK.setItemMeta(spadeEnchantBACKIM);
		spadeEnchant.setItem(8, spadeEnchantBACK);

		player.openInventory(spadeEnchant);
	}

	public static void bowEnchant(Player player) {
		Inventory bowEnchant = Bukkit.createInventory(null, 9,
				TextH.bowEnchantName.toString());

		ItemStack unbreak = new ItemStack(Material.BOW, 1);
		ItemMeta unbreakIM = unbreak.getItemMeta();
		unbreakIM.setDisplayName(TextH.unbreaking.toString());
		ArrayList<String> unbreakLore = new ArrayList<String>();
		unbreakLore.add(TextH.unbreakingLore1.toString());
		unbreakLore.add(TextH.unbreakingLore2.toString());
		unbreakIM.setLore(unbreakLore);
		unbreak.setItemMeta(unbreakIM);
		bowEnchant.setItem(0, unbreak);

		ItemStack power = new ItemStack(Material.BOW, 1);
		ItemMeta powerIM = power.getItemMeta();
		powerIM.setDisplayName(TextH.power.toString());
		ArrayList<String> powerLore = new ArrayList<String>();
		powerLore.add(TextH.powerLore1.toString());
		powerLore.add(TextH.powerLore2.toString());
		powerIM.setLore(powerLore);
		power.setItemMeta(powerIM);
		bowEnchant.setItem(1, power);

		ItemStack punch = new ItemStack(Material.BOW, 1);
		ItemMeta punchIM = punch.getItemMeta();
		punchIM.setDisplayName(TextH.punch.toString());
		ArrayList<String> punchLore = new ArrayList<String>();
		punchLore.add(TextH.punchLore1.toString());
		punchLore.add(TextH.punchLore2.toString());
		punchIM.setLore(punchLore);
		punch.setItemMeta(punchIM);
		bowEnchant.setItem(2, punch);

		ItemStack flame = new ItemStack(Material.BOW, 1);
		ItemMeta flameIM = flame.getItemMeta();
		flameIM.setDisplayName(TextH.flame.toString());
		ArrayList<String> flameLore = new ArrayList<String>();
		flameLore.add(TextH.flameLore1.toString());
		flameLore.add(TextH.flameLore2.toString());
		flameIM.setLore(flameLore);
		flame.setItemMeta(flameIM);
		bowEnchant.setItem(3, flame);

		ItemStack inf = new ItemStack(Material.BOW, 1);
		ItemMeta infIM = inf.getItemMeta();
		infIM.setDisplayName(TextH.inf.toString());
		ArrayList<String> infLore = new ArrayList<String>();
		infLore.add(TextH.infLore1.toString());
		infLore.add(TextH.infLore2.toString());
		infIM.setLore(infLore);
		inf.setItemMeta(infIM);
		bowEnchant.setItem(4, inf);

		ItemStack infEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta infEnchantBACKIM = infEnchantBACK.getItemMeta();
		infEnchantBACKIM.setDisplayName(TextH.back.toString());
		infEnchantBACK.setItemMeta(infEnchantBACKIM);
		bowEnchant.setItem(8, infEnchantBACK);

		player.openInventory(bowEnchant);
	}

	public static void helmEnchant(Player player) {
		Inventory helmEnchant = Bukkit.createInventory(null, 9,
				TextH.helmEnchantName.toString());

		ItemStack pro = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta proIM = pro.getItemMeta();
		proIM.setDisplayName(TextH.pro.toString());
		ArrayList<String> proLore = new ArrayList<String>();
		proLore.add(TextH.proLore1.toString());
		proLore.add(TextH.proLore2.toString());
		proIM.setLore(proLore);
		pro.setItemMeta(proIM);
		helmEnchant.setItem(0, pro);

		ItemStack firePro = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta fireProIM = firePro.getItemMeta();
		fireProIM.setDisplayName(TextH.firePro.toString());
		ArrayList<String> fireLore = new ArrayList<String>();
		fireLore.add(TextH.fireProLore1.toString());
		fireLore.add(TextH.fireProLore2.toString());
		fireProIM.setLore(fireLore);
		firePro.setItemMeta(fireProIM);
		helmEnchant.setItem(1, firePro);

		ItemStack blastPro = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta blastProIM = blastPro.getItemMeta();
		blastProIM.setDisplayName(TextH.blastPro.toString());
		ArrayList<String> blastLore = new ArrayList<String>();
		blastLore.add(TextH.blastProLore1.toString());
		blastLore.add(TextH.blastProLore2.toString());
		blastProIM.setLore(blastLore);
		blastPro.setItemMeta(blastProIM);
		helmEnchant.setItem(2, blastPro);

		ItemStack pp = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta ppIM = pp.getItemMeta();
		ppIM.setDisplayName(TextH.proPro.toString());
		ArrayList<String> ppLore = new ArrayList<String>();
		ppLore.add(TextH.proLore1.toString());
		ppLore.add(TextH.proProLore2.toString());
		ppIM.setLore(ppLore);
		pp.setItemMeta(ppIM);
		helmEnchant.setItem(3, pp);

		ItemStack res = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta resIM = res.getItemMeta();
		resIM.setDisplayName(TextH.resp.toString());
		ArrayList<String> resLore = new ArrayList<String>();
		resLore.add(TextH.respLore1.toString());
		resLore.add(TextH.respLore2.toString());
		resIM.setLore(resLore);
		res.setItemMeta(resIM);
		helmEnchant.setItem(4, res);

		ItemStack aa = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta aaIM = aa.getItemMeta();
		aaIM.setDisplayName(TextH.aa.toString());
		ArrayList<String> aaLore = new ArrayList<String>();
		aaLore.add(TextH.aaLore1.toString());
		aaLore.add(TextH.aaLore2.toString());
		aaIM.setLore(aaLore);
		aa.setItemMeta(aaIM);
		helmEnchant.setItem(5, aa);

		ItemStack thorns = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta thornsIM = thorns.getItemMeta();
		thornsIM.setDisplayName(TextH.thorns.toString());
		ArrayList<String> thornsLore = new ArrayList<String>();
		thornsLore.add(TextH.thornsLore1.toString());
		thornsLore.add(TextH.thornsLore2.toString());
		thornsIM.setLore(thornsLore);
		thorns.setItemMeta(thornsIM);
		helmEnchant.setItem(6, thorns);

		ItemStack infEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta infEnchantBACKIM = infEnchantBACK.getItemMeta();
		infEnchantBACKIM.setDisplayName(TextH.back.toString());
		infEnchantBACK.setItemMeta(infEnchantBACKIM);
		helmEnchant.setItem(8, infEnchantBACK);

		player.openInventory(helmEnchant);

	}

	public static void chestEnchant(Player player) {
		Inventory chestEnchant = Bukkit.createInventory(null, 9,
				TextH.chestEnchantName.toString());

		ItemStack pro = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta proIM = pro.getItemMeta();
		proIM.setDisplayName(TextH.pro.toString());
		ArrayList<String> proLore = new ArrayList<String>();
		proLore.add(TextH.proLore1.toString());
		proLore.add(TextH.proLore2.toString());
		proIM.setLore(proLore);
		pro.setItemMeta(proIM);
		chestEnchant.setItem(0, pro);

		ItemStack firePro = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta fireProIM = firePro.getItemMeta();
		fireProIM.setDisplayName(TextH.firePro.toString());
		ArrayList<String> fireLore = new ArrayList<String>();
		fireLore.add(TextH.fireProLore1.toString());
		fireLore.add(TextH.fireProLore2.toString());
		fireProIM.setLore(fireLore);
		firePro.setItemMeta(fireProIM);
		chestEnchant.setItem(1, firePro);

		ItemStack blastPro = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta blastProIM = blastPro.getItemMeta();
		blastProIM.setDisplayName(TextH.blastPro.toString());
		ArrayList<String> blastLore = new ArrayList<String>();
		blastLore.add(TextH.blastProLore1.toString());
		blastLore.add(TextH.blastProLore2.toString());
		blastProIM.setLore(blastLore);
		blastPro.setItemMeta(blastProIM);
		chestEnchant.setItem(2, blastPro);

		ItemStack pp = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta ppIM = pp.getItemMeta();
		ppIM.setDisplayName(TextH.proPro.toString());
		ArrayList<String> ppLore = new ArrayList<String>();
		ppLore.add(TextH.proLore1.toString());
		ppLore.add(TextH.proProLore2.toString());
		ppIM.setLore(ppLore);
		pp.setItemMeta(ppIM);
		chestEnchant.setItem(3, pp);

		ItemStack thorns = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta thornsIM = thorns.getItemMeta();
		thornsIM.setDisplayName(TextH.thorns.toString());
		ArrayList<String> thornsLore = new ArrayList<String>();
		thornsLore.add(TextH.thornsLore1.toString());
		thornsLore.add(TextH.thornsLore2.toString());
		thornsIM.setLore(thornsLore);
		thorns.setItemMeta(thornsIM);
		chestEnchant.setItem(4, thorns);

		ItemStack infEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta infEnchantBACKIM = infEnchantBACK.getItemMeta();
		infEnchantBACKIM.setDisplayName(TextH.back.toString());
		infEnchantBACK.setItemMeta(infEnchantBACKIM);
		chestEnchant.setItem(8, infEnchantBACK);

		player.openInventory(chestEnchant);
	}

	public static void legEnchant(Player player) {
		Inventory legsEnchant = Bukkit.createInventory(null, 9,
				TextH.legsEnchantName.toString());

		ItemStack pro = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta proIM = pro.getItemMeta();
		proIM.setDisplayName(TextH.pro.toString());
		ArrayList<String> proLore = new ArrayList<String>();
		proLore.add(TextH.proLore1.toString());
		proLore.add(TextH.proLore2.toString());
		proIM.setLore(proLore);
		pro.setItemMeta(proIM);
		legsEnchant.setItem(0, pro);

		ItemStack firePro = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta fireProIM = firePro.getItemMeta();
		fireProIM.setDisplayName(TextH.firePro.toString());
		ArrayList<String> fireLore = new ArrayList<String>();
		fireLore.add(TextH.fireProLore1.toString());
		fireLore.add(TextH.fireProLore2.toString());
		fireProIM.setLore(fireLore);
		legsEnchant.setItem(1, firePro);

		ItemStack blastPro = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta blastProIM = blastPro.getItemMeta();
		blastProIM.setDisplayName(TextH.blastPro.toString());
		ArrayList<String> blastLore = new ArrayList<String>();
		blastLore.add(TextH.blastProLore1.toString());
		blastLore.add(TextH.blastProLore2.toString());
		blastProIM.setLore(blastLore);
		blastPro.setItemMeta(blastProIM);
		legsEnchant.setItem(2, blastPro);

		ItemStack pp = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta ppIM = pp.getItemMeta();
		ppIM.setDisplayName(TextH.proPro.toString());
		ArrayList<String> ppLore = new ArrayList<String>();
		ppLore.add(TextH.proLore1.toString());
		ppLore.add(TextH.proProLore2.toString());
		ppIM.setLore(ppLore);
		pp.setItemMeta(ppIM);
		legsEnchant.setItem(3, pp);

		ItemStack thorns = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta thornsIM = thorns.getItemMeta();
		thornsIM.setDisplayName(TextH.thorns.toString());
		ArrayList<String> thornsLore = new ArrayList<String>();
		thornsLore.add(TextH.thornsLore1.toString());
		thornsLore.add(TextH.thornsLore2.toString());
		thornsIM.setLore(thornsLore);
		thorns.setItemMeta(thornsIM);
		legsEnchant.setItem(4, thorns);

		ItemStack infEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta infEnchantBACKIM = infEnchantBACK.getItemMeta();
		infEnchantBACKIM.setDisplayName(TextH.back.toString());
		infEnchantBACK.setItemMeta(infEnchantBACKIM);
		legsEnchant.setItem(8, infEnchantBACK);

		player.openInventory(legsEnchant);
	}

	public static void bootEnchant(Player player) {
		Inventory bootEnchant = Bukkit.createInventory(null, 9,
				TextH.bootEnchantName.toString());

		ItemStack pro = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta proIM = pro.getItemMeta();
		proIM.setDisplayName(TextH.pro.toString());
		ArrayList<String> proLore = new ArrayList<String>();
		proLore.add(TextH.proLore1.toString());
		proLore.add(TextH.proLore2.toString());
		proIM.setLore(proLore);
		pro.setItemMeta(proIM);
		bootEnchant.setItem(0, pro);

		ItemStack firePro = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta fireProIM = firePro.getItemMeta();
		fireProIM.setDisplayName(TextH.firePro.toString());
		ArrayList<String> fireLore = new ArrayList<String>();
		fireLore.add(TextH.fireProLore1.toString());
		fireLore.add(TextH.fireProLore2.toString());
		fireProIM.setLore(fireLore);
		bootEnchant.setItem(1, firePro);

		ItemStack ff = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta ffIM = ff.getItemMeta();
		ffIM.setDisplayName(TextH.ff.toString());
		ArrayList<String> ffLore = new ArrayList<String>();
		ffLore.add(TextH.ffLore1.toString());
		ffLore.add(TextH.ffLore2.toString());
		ffIM.setLore(ffLore);
		ff.setItemMeta(ffIM);
		bootEnchant.setItem(2, ff);

		ItemStack blastPro = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta blastProIM = blastPro.getItemMeta();
		blastProIM.setDisplayName(TextH.blastPro.toString());
		ArrayList<String> blastLore = new ArrayList<String>();
		blastLore.add(TextH.blastProLore1.toString());
		blastLore.add(TextH.blastProLore2.toString());
		blastProIM.setLore(blastLore);
		blastPro.setItemMeta(blastProIM);
		bootEnchant.setItem(3, blastPro);

		ItemStack pp = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta ppIM = pp.getItemMeta();
		ppIM.setDisplayName(TextH.proPro.toString());
		ArrayList<String> ppLore = new ArrayList<String>();
		ppLore.add(TextH.proLore1.toString());
		ppLore.add(TextH.proProLore2.toString());
		ppIM.setLore(ppLore);
		pp.setItemMeta(ppIM);
		bootEnchant.setItem(4, pp);

		ItemStack thorns = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta thornsIM = thorns.getItemMeta();
		thornsIM.setDisplayName(TextH.thorns.toString());
		ArrayList<String> thornsLore = new ArrayList<String>();
		thornsLore.add(TextH.thornsLore1.toString());
		thornsLore.add(TextH.thornsLore2.toString());
		thornsIM.setLore(thornsLore);
		thorns.setItemMeta(thornsIM);
		bootEnchant.setItem(5, thorns);

		ItemStack infEnchantBACK = new ItemStack(Material.REDSTONE, 1);
		ItemMeta infEnchantBACKIM = infEnchantBACK.getItemMeta();
		infEnchantBACKIM.setDisplayName(TextH.back.toString());
		infEnchantBACK.setItemMeta(infEnchantBACKIM);
		bootEnchant.setItem(8, infEnchantBACK);

		player.openInventory(bootEnchant);

	}

	public static void closeMenu(Player player) {
		player.closeInventory();
	}

	public static void sharpnessLevel(Player player) {
		Inventory sharpnessLevel = Bukkit.createInventory(null, 9,
				TextH.sharpnessBuy.toString());
		
		ItemStack sLevel1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta sLevel1IM = sLevel1.getItemMeta();
		sLevel1IM.set
		
	}
}
