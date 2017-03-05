package co.uk.RandomPanda30.KnightCrates.Events;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;

public class OnInventoryClickEvent implements Listener {

	public KnightCrates plugin;

	public OnInventoryClickEvent (KnightCrates plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack is = event.getCurrentItem();

		if (is == null) {
			return;
		}

		if (is.getType().equals(Material.AIR)) {
			return;
		}

		if (event.getInventory().getName().equals(ChatColor
				.translateAlternateColorCodes('&', "&6&lCrate Menu"))) {
			event.setCancelled(true);
		}

		if (is.getItemMeta().hasDisplayName()
				&& is.getItemMeta().getDisplayName()
						.equals(ChatColor.translateAlternateColorCodes('&',
								"&7&lIron Key"))
				&& is.getType().equals(Material.NETHER_STAR)) {
			if (is.getAmount() == 0) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						KnightCrates.tag
								+ "&CYou do not have any crate keys. Go to &6http://oblivion.yt/shop &Cto buy some!"));
				player.closeInventory();
				return;
			} else {
				int keys = plugin.getConfig()
						.getInt(player.getUniqueId().toString() + ".iron");
				Inventory items = Bukkit.createInventory(null, 54, "Items");

				if (KnightCrates.iron.getItems() == null) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CThe crate inventories have not been setup. Please report this to an admin"));
					player.closeInventory();
					return;
				}

				for (ItemStack ls : KnightCrates.iron.getItems()) {
					items.addItem(ls);
				}

				boolean l1 = true;
				boolean l2 = true;
				boolean l3 = true;
				boolean l4 = true;
				boolean l5 = true;
				boolean l6 = true;

				for (int i = 0; i < 9; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l1 = false;
						break;
					}
				}

				if (l1 == false) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CThe crate inventories have not been setup. Please report this to an admin"));
					player.closeInventory();
					return;
				}

				for (int i = 9; i < 18; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l2 = false;
						break;
					}
				}

				for (int i = 18; i < 27; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l3 = false;
						break;
					}
				}

				for (int i = 27; i < 36; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l4 = false;
						break;
					}
				}

				for (int i = 36; i < 45; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l5 = false;
						break;
					}
				}

				for (int i = 45; i < 54; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l6 = false;
						break;
					}
				}

				int max = 0;

				if (l1)
					max++;

				if (l2)
					max++;

				if (l3)
					max++;

				if (l4)
					max++;

				if (l5)
					max++;

				if (l6)
					max++;

				Random random = new Random();
				int r = random.nextInt(max) + 1;

				ArrayList<ItemStack> stack = new ArrayList<>();
				switch (r) {
				case 1:
					for (int i = 0; i < 9; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 2:
					for (int i = 9; i < 18; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 3:
					for (int i = 18; i < 27; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 4:
					for (int i = 27; i < 36; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 5:
					for (int i = 36; i < 45; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 6:
					for (int i = 45; i < 54; i++) {
						stack.add(items.getItem(i));
					}
					break;
				}

				int space = 0;

				for (ItemStack content : player.getInventory().getContents()) {
					if (content == null) {
						space++;
					}
				}

				if (space >= stack.size()) {
					for (ItemStack newi : stack) {
						player.getInventory().addItem(newi);
					}
				} else {
					player.closeInventory();
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou do not have enough space in your inventory"));
					return;
				}

				Firework fw = (Firework) player.getWorld()
						.spawnEntity(player.getLocation(), EntityType.FIREWORK);

				Firework fw1 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().add(1, 0, 0), EntityType.FIREWORK);
				Firework fw2 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().subtract(1, 0, 0),
						EntityType.FIREWORK);

				Firework fw3 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().add(0, 0, 1), EntityType.FIREWORK);
				Firework fw4 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().subtract(0, 0, 1),
						EntityType.FIREWORK);

				FireworkMeta fwm = fw.getFireworkMeta();

				FireworkEffect effect = FireworkEffect.builder().flicker(false)
						.withColor(Color.ORANGE).withFade(Color.ORANGE)
						.with(Type.BALL_LARGE).trail(true).build();

				fwm.addEffect(effect);

				fwm.setPower(3);

				fw.setFireworkMeta(fwm);
				fw1.setFireworkMeta(fwm);
				fw2.setFireworkMeta(fwm);
				fw3.setFireworkMeta(fwm);
				fw4.setFireworkMeta(fwm);

				player.closeInventory();

				plugin.getConfig().set(
						player.getUniqueId().toString() + ".iron", keys - 1);
				plugin.saveConfig();

				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes(
						'&', "&B&l" + player.getName()
								+ "&6&l has opened a crate using a crate key. Buy yours at: &A&lhttp://oblivion.yt/shop"));
			}
		}

		if (is.getItemMeta().hasDisplayName()
				&& is.getItemMeta().getDisplayName()
						.equals(ChatColor.translateAlternateColorCodes('&',
								"&6&lGold Key"))
				&& is.getType().equals(Material.NETHER_STAR)) {
			if (is.getAmount() == 0) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						KnightCrates.tag
								+ "&CYou do not have any crate keys. Go to &6http://oblivion.yt/shop &Cto buy some!"));
				player.closeInventory();
				return;
			} else {
				int keys = plugin.getConfig()
						.getInt(player.getUniqueId().toString() + ".gold");
				Inventory items = Bukkit.createInventory(null, 54, "Items");

				if (KnightCrates.gold.getItems() == null) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CThe crate inventories have not been setup. Please report this to an admin"));
					player.closeInventory();
					return;
				}

				for (ItemStack ls : KnightCrates.gold.getItems()) {
					items.addItem(ls);
				}

				boolean l1 = true;
				boolean l2 = true;
				boolean l3 = true;
				boolean l4 = true;
				boolean l5 = true;
				boolean l6 = true;

				for (int i = 0; i < 9; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l1 = false;
						break;
					}
				}

				if (l1 == false) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CThe crate inventories have not been setup. Please report this to an admin"));
					player.closeInventory();
					return;
				}

				for (int i = 9; i < 18; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l2 = false;
						break;
					}
				}

				for (int i = 18; i < 27; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l3 = false;
						break;
					}
				}

				for (int i = 27; i < 36; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l4 = false;
						break;
					}
				}

				for (int i = 36; i < 45; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l5 = false;
						break;
					}
				}

				for (int i = 45; i < 54; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l6 = false;
						break;
					}
				}

				int max = 0;

				if (l1)
					max++;

				if (l2)
					max++;

				if (l3)
					max++;

				if (l4)
					max++;

				if (l5)
					max++;

				if (l6)
					max++;

				Random random = new Random();
				int r = random.nextInt(max) + 1;

				ArrayList<ItemStack> stack = new ArrayList<>();
				switch (r) {
				case 1:
					for (int i = 0; i < 9; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 2:
					for (int i = 9; i < 18; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 3:
					for (int i = 18; i < 27; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 4:
					for (int i = 27; i < 36; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 5:
					for (int i = 36; i < 45; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 6:
					for (int i = 45; i < 54; i++) {
						stack.add(items.getItem(i));
					}
					break;
				}

				int space = 0;

				for (ItemStack content : player.getInventory().getContents()) {
					if (content == null) {
						space++;
					}
				}

				if (space >= stack.size()) {
					for (ItemStack newi : stack) {
						player.getInventory().addItem(newi);
					}
				} else {
					player.closeInventory();
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou do not have enough space in your inventory"));
					return;
				}

				Firework fw = (Firework) player.getWorld()
						.spawnEntity(player.getLocation(), EntityType.FIREWORK);

				Firework fw1 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().add(1, 0, 0), EntityType.FIREWORK);
				Firework fw2 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().subtract(1, 0, 0),
						EntityType.FIREWORK);

				Firework fw3 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().add(0, 0, 1), EntityType.FIREWORK);
				Firework fw4 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().subtract(0, 0, 1),
						EntityType.FIREWORK);

				FireworkMeta fwm = fw.getFireworkMeta();

				FireworkEffect effect = FireworkEffect.builder().flicker(false)
						.withColor(Color.ORANGE).withFade(Color.ORANGE)
						.with(Type.BALL_LARGE).trail(true).build();

				fwm.addEffect(effect);

				fwm.setPower(2);

				fw.setFireworkMeta(fwm);
				fw1.setFireworkMeta(fwm);
				fw2.setFireworkMeta(fwm);
				fw3.setFireworkMeta(fwm);
				fw4.setFireworkMeta(fwm);

				player.closeInventory();

				plugin.getConfig().set(
						player.getUniqueId().toString() + ".gold", keys - 1);
				plugin.saveConfig();

				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes(
						'&', "&B&l" + player.getName()
								+ "&6&l has opened a crate using a crate key. Buy yours at: &A&lhttp://oblivion.yt/shop"));
			}
		}

		if (is.getItemMeta().hasDisplayName()
				&& is.getItemMeta().getDisplayName()
						.equals(ChatColor.translateAlternateColorCodes('&',
								"&B&lDiamond Key"))
				&& is.getType().equals(Material.NETHER_STAR)) {
			if (is.getAmount() == 0) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						KnightCrates.tag
								+ "&CYou do not have any crate keys. Go to &6http://oblivion.yt/shop &Cto buy some!"));
				player.closeInventory();
				return;
			} else {
				int keys = plugin.getConfig()
						.getInt(player.getUniqueId().toString() + ".diamond");
				Inventory items = Bukkit.createInventory(null, 54, "Items");

				if (KnightCrates.diamond.getItems() == null) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CThe crate inventories have not been setup. Please report this to an admin"));
					player.closeInventory();
					return;
				}

				for (ItemStack ls : KnightCrates.diamond.getItems()) {
					items.addItem(ls);
				}

				boolean l1 = true;
				boolean l2 = true;
				boolean l3 = true;
				boolean l4 = true;
				boolean l5 = true;
				boolean l6 = true;

				for (int i = 0; i < 9; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l1 = false;
						break;
					}
				}

				if (l1 == false) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CThe crate inventories have not been setup. Please report this to an admin"));
					player.closeInventory();
					return;
				}

				for (int i = 9; i < 18; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l2 = false;
						break;
					}
				}

				for (int i = 18; i < 27; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l3 = false;
						break;
					}
				}

				for (int i = 27; i < 36; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l4 = false;
						break;
					}
				}

				for (int i = 36; i < 45; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l5 = false;
						break;
					}
				}

				for (int i = 45; i < 54; i++) {
					if (items.getItem(i) == null || items.getItem(i).getType()
							.equals(Material.AIR)) {
						l6 = false;
						break;
					}
				}

				int max = 0;

				if (l1)
					max++;

				if (l2)
					max++;

				if (l3)
					max++;

				if (l4)
					max++;

				if (l5)
					max++;

				if (l6)
					max++;

				Random random = new Random();
				int r = random.nextInt(max) + 1;

				ArrayList<ItemStack> stack = new ArrayList<>();
				switch (r) {
				case 1:
					for (int i = 0; i < 9; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 2:
					for (int i = 9; i < 18; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 3:
					for (int i = 18; i < 27; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 4:
					for (int i = 27; i < 36; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 5:
					for (int i = 36; i < 45; i++) {
						stack.add(items.getItem(i));
					}
					break;
				case 6:
					for (int i = 45; i < 54; i++) {
						stack.add(items.getItem(i));
					}
					break;
				}

				int space = 0;

				for (ItemStack content : player.getInventory().getContents()) {
					if (content == null) {
						space++;
					}
				}

				if (space >= stack.size()) {
					for (ItemStack newi : stack) {
						player.getInventory().addItem(newi);
					}
				} else {
					player.closeInventory();
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&',
							"&CYou do not have enough space in your inventory"));
					return;
				}

				Firework fw = (Firework) player.getWorld()
						.spawnEntity(player.getLocation(), EntityType.FIREWORK);

				Firework fw1 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().add(1, 0, 0), EntityType.FIREWORK);
				Firework fw2 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().subtract(1, 0, 0),
						EntityType.FIREWORK);

				Firework fw3 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().add(0, 0, 1), EntityType.FIREWORK);
				Firework fw4 = (Firework) player.getWorld().spawnEntity(
						player.getLocation().subtract(0, 0, 1),
						EntityType.FIREWORK);

				FireworkMeta fwm = fw.getFireworkMeta();

				FireworkEffect effect = FireworkEffect.builder().flicker(false)
						.withColor(Color.ORANGE).withFade(Color.ORANGE)
						.with(Type.BALL_LARGE).trail(true).build();

				fwm.addEffect(effect);

				fwm.setPower(2);

				fw.setFireworkMeta(fwm);
				fw1.setFireworkMeta(fwm);
				fw2.setFireworkMeta(fwm);
				fw3.setFireworkMeta(fwm);
				fw4.setFireworkMeta(fwm);

				player.closeInventory();

				plugin.getConfig().set(
						player.getUniqueId().toString() + ".diamond", keys - 1);
				plugin.saveConfig();

				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes(
						'&', "&B&l" + player.getName()
								+ "&6&l has opened a crate using a crate key. Buy yours at: &A&lhttp://oblivion.yt/shop"));
			}
		}
	}
}