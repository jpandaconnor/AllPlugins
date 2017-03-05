package co.uk.RandomPanda30.Events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import co.uk.RandomPanda30.Handlers.InventoryHandler;
import co.uk.RandomPanda30.Main.Main;
import co.uk.RandomPanda30.Text.TextH;

public class OnInventoryClickEvent implements Listener {

	public OnInventoryClickEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
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

		if (e.getInventory().getName().equals(TextH.mainMenuName.toString())
				&& item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.donateButtonName.toString())
				&& item.getType() == Material.EMERALD) {
			p.sendMessage(TextH.donateMessage.toString());
			p.sendMessage(TextH.donateLink.toString());
			InventoryHandler.closeMenu(p);
		}

		if (e.getInventory().getName().equals(TextH.mainMenuName.toString())
				&& item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.enchantName.toString())
				&& item.getType() == Material.EXP_BOTTLE) {
			if (p.getItemInHand().getType() == Material.DIAMOND_SWORD
					|| p.getItemInHand().getType() == Material.IRON_SWORD
					|| p.getItemInHand().getType() == Material.STONE_SWORD
					|| p.getItemInHand().getType() == Material.GOLD_SWORD
					|| p.getItemInHand().getType() == Material.WOOD_SWORD) {
				InventoryHandler.swordEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_PICKAXE
					|| p.getItemInHand().getType() == Material.IRON_PICKAXE
					|| p.getItemInHand().getType() == Material.STONE_PICKAXE
					|| p.getItemInHand().getType() == Material.GOLD_PICKAXE
					|| p.getItemInHand().getType() == Material.WOOD_PICKAXE) {
				InventoryHandler.pickaxeEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_AXE
					|| p.getItemInHand().getType() == Material.IRON_AXE
					|| p.getItemInHand().getType() == Material.STONE_AXE
					|| p.getItemInHand().getType() == Material.GOLD_AXE
					|| p.getItemInHand().getType() == Material.WOOD_AXE) {
				InventoryHandler.axeEnchantment(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_SPADE
					|| p.getItemInHand().getType() == Material.IRON_SPADE
					|| p.getItemInHand().getType() == Material.STONE_SPADE
					|| p.getItemInHand().getType() == Material.GOLD_SPADE
					|| p.getItemInHand().getType() == Material.WOOD_SPADE) {
				InventoryHandler.spadeEnchant(p);
			} else if (p.getItemInHand().getType() == Material.BOW) {
				InventoryHandler.bowEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_HELMET
					|| p.getItemInHand().getType() == Material.IRON_HELMET
					|| p.getItemInHand().getType() == Material.GOLD_HELMET
					|| p.getItemInHand().getType() == Material.CHAINMAIL_HELMET
					|| p.getItemInHand().getType() == Material.LEATHER_HELMET) {
				InventoryHandler.helmEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_CHESTPLATE
					|| p.getItemInHand().getType() == Material.IRON_CHESTPLATE
					|| p.getItemInHand().getType() == Material.GOLD_CHESTPLATE
					|| p.getItemInHand().getType() == Material.CHAINMAIL_CHESTPLATE
					|| p.getItemInHand().getType() == Material.LEATHER_CHESTPLATE) {
				InventoryHandler.chestEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_LEGGINGS
					|| p.getItemInHand().getType() == Material.IRON_LEGGINGS
					|| p.getItemInHand().getType() == Material.GOLD_LEGGINGS
					|| p.getItemInHand().getType() == Material.CHAINMAIL_LEGGINGS
					|| p.getItemInHand().getType() == Material.LEATHER_LEGGINGS) {
				InventoryHandler.legEnchant(p);
			} else if (p.getItemInHand().getType() == Material.DIAMOND_BOOTS
					|| p.getItemInHand().getType() == Material.IRON_BOOTS
					|| p.getItemInHand().getType() == Material.GOLD_BOOTS
					|| p.getItemInHand().getType() == Material.CHAINMAIL_BOOTS
					|| p.getItemInHand().getType() == Material.LEATHER_BOOTS) {
				InventoryHandler.bootEnchant(p);
			} else {
				p.sendMessage(TextH.notATool.toString());
				InventoryHandler.closeMenu(p);
			}
		}
		ItemStack i = p.getItemInHand();

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.pro.toString())) {
			if (i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i
					.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 1) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i
					.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 2) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i
					.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 3) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i
					.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4) {
				p.sendMessage(TextH.highestLevel.toString());
			}

		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.firePro.toString())) {
			if (i.getEnchantmentLevel(Enchantment.PROTECTION_FIRE) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_FIRE, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FIRE) == 1) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_FIRE, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FIRE) == 2) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_FIRE, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FIRE) == 3) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FIRE) == 4) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}
		// connor
		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.ff.toString())) {
			if (i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_FALL, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 1) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_FALL, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 2) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_FALL, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 3) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) == 4) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.blastPro.toString())) {
			if (i.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) == 1) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) == 2) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) == 3) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) == 4) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.proPro.toString())) {
			if (i.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) == 1) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) == 2) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) == 3) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) == 4) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.resp.toString())) {
			if (i.getEnchantmentLevel(Enchantment.OXYGEN) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.OXYGEN, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.OXYGEN) == 1) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.OXYGEN, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.OXYGEN) == 2) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.OXYGEN, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.OXYGEN) == 3) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.aa.toString())) {
			if (i.getEnchantmentLevel(Enchantment.WATER_WORKER) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.WATER_WORKER, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.WATER_WORKER) == 1) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.thorns.toString())) {
			if (i.getEnchantmentLevel(Enchantment.THORNS) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.THORNS, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.THORNS) == 1) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.THORNS, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.THORNS) == 2) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.THORNS, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.THORNS) == 3) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.inf.toString())) {
			if (i.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 0) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 1) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.flame.toString())) {
			if (i.getEnchantmentLevel(Enchantment.ARROW_FIRE) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.ARROW_FIRE, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_FIRE) == 1) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}
		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName().equals(TextH.unbreaking)) {
			if (i.getEnchantmentLevel(Enchantment.DURABILITY) == 0) {
				try {
					Economy.subtract(p.getName(), 6.0);
					i.addEnchantment(Enchantment.DURABILITY, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DURABILITY) == 1) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.DURABILITY, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DURABILITY) == 2) {
				try {
					Economy.subtract(p.getName(), 24.0);
					i.addEnchantment(Enchantment.DURABILITY, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DURABILITY) == 3) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.silk.toString())) {
			if (i.getEnchantmentLevel(Enchantment.SILK_TOUCH) == 0) {
				try {
					Economy.subtract(p.getName(), 9.0);
					i.addEnchantment(Enchantment.SILK_TOUCH, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.SILK_TOUCH) == 1) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.punch.toString())) {
			if (i.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.KNOCKBACK, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) == 1) {
				try {
					Economy.subtract(p.getName(), 8.0);
					i.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) == 2) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.power.toString())) {
			if (i.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) == 1) {
				try {
					Economy.subtract(p.getName(), 8.0);
					i.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) == 2) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) == 3) {
				try {
					Economy.subtract(p.getName(), 16.0);
					i.addEnchantment(Enchantment.ARROW_DAMAGE, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) == 4) {
				try {
					Economy.subtract(p.getName(), 20.0);
					i.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) == 5) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.fortune.toString())) {
			if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
				try {
					Economy.subtract(p.getName(), 6.0);
					i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
				try {
					Economy.subtract(p.getName(), 20.0);
					i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
				try {
					Economy.subtract(p.getName(), 25.0);
					i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.eff.toString())) {
			if (i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.DIG_SPEED, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 1) {
				try {
					Economy.subtract(p.getName(), 8.0);
					i.addEnchantment(Enchantment.DIG_SPEED, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 2) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.DIG_SPEED, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 3) {
				try {
					Economy.subtract(p.getName(), 16.0);
					i.addEnchantment(Enchantment.DIG_SPEED, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 4) {
				try {
					Economy.subtract(p.getName(), 20.0);
					i.addEnchantment(Enchantment.DIG_SPEED, 5);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DIG_SPEED) == 5) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.loot.toString())) {
			if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) == 1) {
				try {
					Economy.subtract(p.getName(), 8.0);
					i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) == 2) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) == 3) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.fireA.toString())) {
			if (i.getEnchantmentLevel(Enchantment.FIRE_ASPECT) == 0) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.FIRE_ASPECT, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.FIRE_ASPECT) == 1) {
				try {
					Economy.subtract(p.getName(), 24.0);
					i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.FIRE_ASPECT) == 2) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.knock.toString())) {
			if (i.getEnchantmentLevel(Enchantment.KNOCKBACK) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.KNOCKBACK, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.KNOCKBACK) == 1) {
				try {
					Economy.subtract(p.getName(), 8.0);
					i.addEnchantment(Enchantment.KNOCKBACK, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.KNOCKBACK) == 2) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.boa.toString())) {
			if (i.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) == 1) {
				try {
					Economy.subtract(p.getName(), 8.0);
					i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) == 2) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) == 3) {
				try {
					Economy.subtract(p.getName(), 16.0);
					i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) == 4) {
				try {
					Economy.subtract(p.getName(), 20.0);
					i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) == 5) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.smiteName.toString())) {
			if (i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 0) {
				try {
					Economy.subtract(p.getName(), 4.0);
					i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 1) {
				try {
					Economy.subtract(p.getName(), 8.0);
					i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 2) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 3) {
				try {
					Economy.subtract(p.getName(), 16.0);
					i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 4) {
				try {
					Economy.subtract(p.getName(), 20.0);
					i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) == 5) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}
		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(TextH.sharpnessName.toString())) {
			if (i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 0) {
				try {
					Economy.subtract(p.getName(), 3.0);
					i.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 1) {
				try {
					Economy.subtract(p.getName(), 6.0);
					i.addEnchantment(Enchantment.DAMAGE_ALL, 2);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 2) {
				try {
					Economy.subtract(p.getName(), 9.0);
					i.addEnchantment(Enchantment.DAMAGE_ALL, 3);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 3) {
				try {
					Economy.subtract(p.getName(), 12.0);
					i.addEnchantment(Enchantment.DAMAGE_ALL, 4);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 4) {
				try {
					Economy.subtract(p.getName(), 16.0);
					i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
				} catch (NoLoanPermittedException e1) {
					p.sendMessage(TextH.noLoans.toString());
				} catch (UserDoesNotExistException e1) {
					p.sendMessage(TextH.nullPlayer.toString());
				}
			} else if (i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 5) {
				p.sendMessage(TextH.highestLevel.toString());
			}
		}
		return true;
	}
}
