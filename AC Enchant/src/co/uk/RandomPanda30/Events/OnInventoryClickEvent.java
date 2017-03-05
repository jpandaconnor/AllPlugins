package co.uk.RandomPanda30.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.EnchantInventories.BaneOfAthropodsConfirmInventory;
import co.uk.RandomPanda30.EnchantInventories.FireAspectConfirmInventory;
import co.uk.RandomPanda30.EnchantInventories.KnockbackConfirmInventory;
import co.uk.RandomPanda30.EnchantInventories.LootingConfirmInventory;
import co.uk.RandomPanda30.EnchantInventories.SharpnessConfirmInventory;
import co.uk.RandomPanda30.EnchantInventories.SmiteConfirmInventory;
import co.uk.RandomPanda30.EnchantInventories.UnbreakingConfirmInventory;
import co.uk.RandomPanda30.EnchantmentText.BoaText;
import co.uk.RandomPanda30.EnchantmentText.EnchantmentText;
import co.uk.RandomPanda30.EnchantmentText.FireAspectText;
import co.uk.RandomPanda30.EnchantmentText.KnockbackText;
import co.uk.RandomPanda30.EnchantmentText.LootingText;
import co.uk.RandomPanda30.EnchantmentText.SharpnessText;
import co.uk.RandomPanda30.EnchantmentText.SmiteText;
import co.uk.RandomPanda30.EnchantmentText.UnbreakingText;
import co.uk.RandomPanda30.ItemInventory.AxeInventory;
import co.uk.RandomPanda30.ItemInventory.BootsInventory;
import co.uk.RandomPanda30.ItemInventory.BowInventory;
import co.uk.RandomPanda30.ItemInventory.ChestInventory;
import co.uk.RandomPanda30.ItemInventory.CloseInventory;
import co.uk.RandomPanda30.ItemInventory.HelmetInventory;
import co.uk.RandomPanda30.ItemInventory.LegsInventory;
import co.uk.RandomPanda30.ItemInventory.PickaxeInventory;
import co.uk.RandomPanda30.ItemInventory.SpadeInventory;
import co.uk.RandomPanda30.ItemInventory.SwordInventory;
import co.uk.RandomPanda30.Main.Main;
import co.uk.RandomPanda30.MiscText.InventoryNames;
import co.uk.RandomPanda30.MiscText.MainMenuText;
import co.uk.RandomPanda30.MiscText.NullItem;

public class OnInventoryClickEvent implements Listener {

	public OnInventoryClickEvent(Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public boolean onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		if (item == null) {
			return true;
		}

		if (item.getType() == Material.AIR) {
			return true;
		}

		if (e.getInventory().getName().startsWith("\u00A7r ")) {
			e.setCancelled(true);
		}

		if (e.getInventory().getName().equals(MainMenuText.mainMenu)
				&& item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(EnchantmentText.enchantmentName)
				&& item.getType() == Material.EXP_BOTTLE) {
			e.setCancelled(true);
			if (p.getItemInHand().getType() == Material.DIAMOND_SWORD
					|| p.getItemInHand().getType() == Material.IRON_SWORD
					|| p.getItemInHand().getType() == Material.STONE_SWORD
					|| p.getItemInHand().getType() == Material.GOLD_SWORD
					|| p.getItemInHand().getType() == Material.WOOD_SWORD) {
				SwordInventory.swordInventory(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_PICKAXE
					|| p.getItemInHand().getType() == Material.IRON_PICKAXE
					|| p.getItemInHand().getType() == Material.STONE_PICKAXE
					|| p.getItemInHand().getType() == Material.GOLD_PICKAXE
					|| p.getItemInHand().getType() == Material.WOOD_PICKAXE) {
				PickaxeInventory.pickaxeEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_AXE
					|| p.getItemInHand().getType() == Material.IRON_AXE
					|| p.getItemInHand().getType() == Material.STONE_AXE
					|| p.getItemInHand().getType() == Material.GOLD_AXE
					|| p.getItemInHand().getType() == Material.WOOD_AXE) {
				AxeInventory.axeEnchantment(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_SPADE
					|| p.getItemInHand().getType() == Material.IRON_SPADE
					|| p.getItemInHand().getType() == Material.STONE_SPADE
					|| p.getItemInHand().getType() == Material.GOLD_SPADE
					|| p.getItemInHand().getType() == Material.WOOD_SPADE) {
				SpadeInventory.spadeEnchant(p);
			} else if (p.getItemInHand().getType() == Material.BOW) {
				BowInventory.bowEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_HELMET
					|| p.getItemInHand().getType() == Material.IRON_HELMET
					|| p.getItemInHand().getType() == Material.GOLD_HELMET
					|| p.getItemInHand().getType() == Material.CHAINMAIL_HELMET
					|| p.getItemInHand().getType() == Material.LEATHER_HELMET) {
				HelmetInventory.helmEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_CHESTPLATE
					|| p.getItemInHand().getType() == Material.IRON_CHESTPLATE
					|| p.getItemInHand().getType() == Material.GOLD_CHESTPLATE
					|| p.getItemInHand().getType() == Material.CHAINMAIL_CHESTPLATE
					|| p.getItemInHand().getType() == Material.LEATHER_CHESTPLATE) {
				ChestInventory.chestEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_LEGGINGS
					|| p.getItemInHand().getType() == Material.IRON_LEGGINGS
					|| p.getItemInHand().getType() == Material.GOLD_LEGGINGS
					|| p.getItemInHand().getType() == Material.CHAINMAIL_LEGGINGS
					|| p.getItemInHand().getType() == Material.LEATHER_LEGGINGS) {
				LegsInventory.legEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_BOOTS
					|| p.getItemInHand().getType() == Material.IRON_BOOTS
					|| p.getItemInHand().getType() == Material.GOLD_BOOTS
					|| p.getItemInHand().getType() == Material.CHAINMAIL_BOOTS
					|| p.getItemInHand().getType() == Material.LEATHER_BOOTS) {
				BootsInventory.bootEnchant(p);
			} else {
				p.sendMessage(NullItem.notATool1);
				p.sendMessage(NullItem.notATool2);
				CloseInventory.closeInventory(p);
			}
		}

		if (e.getInventory().getName().equals(InventoryNames.swordEnchantName)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(SharpnessText.sharpnessName)
				&& item.getType() == Material.DIAMOND_SWORD) {
			SharpnessConfirmInventory.swordInventory(p);
		}

		if (e.getInventory().getName().equals(InventoryNames.swordEnchantName)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(SmiteText.smiteName)
				&& item.getType() == Material.DIAMOND_SWORD) {
			SmiteConfirmInventory.swordInventory(p);
		}

		if (e.getInventory().getName().equals(InventoryNames.swordEnchantName)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName().equals(BoaText.boaName)
				&& item.getType() == Material.DIAMOND_SWORD) {
			BaneOfAthropodsConfirmInventory.boaInventory(p);
		}

		if (e.getInventory().getName().equals(InventoryNames.swordEnchantName)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(KnockbackText.knockName)
				&& item.getType() == Material.DIAMOND_SWORD) {
			KnockbackConfirmInventory.knockbackInventory(p);
		}

		if (e.getInventory().getName().equals(InventoryNames.swordEnchantName)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(FireAspectText.fireName)
				&& item.getType() == Material.DIAMOND_SWORD) {
			FireAspectConfirmInventory.fireAspectInventory(p);
		}

		if (e.getInventory().getName().equals(InventoryNames.swordEnchantName)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(LootingText.lootName)
				&& item.getType() == Material.DIAMOND_SWORD) {
			LootingConfirmInventory.swordInventory(p);
		}

		if (e.getInventory().getName().equals(InventoryNames.swordEnchantName)
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(UnbreakingText.unbreaking)
				&& item.getType() == Material.DIAMOND_SWORD) {
			UnbreakingConfirmInventory.swordInventory(p);
		}
		return true;
	}
}
