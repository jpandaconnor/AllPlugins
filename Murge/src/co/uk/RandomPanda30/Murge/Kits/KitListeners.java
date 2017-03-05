package co.uk.RandomPanda30.Murge.Kits;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.KitsOptionsMenu;
import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.KitsOptionsItems;

public class KitListeners implements Listener {

	public Murge plugin;

	public KitListeners (Murge plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		String message = event.getMessage();

		KitSetup setup = null;

		if (KitTemp.containsAdding(uuid)) {
			for (KitSetup ks : KitTemp.getMakingKits()) {
				if (ks.getPlayer().equals(player)) {
					setup = ks;
				}
			}

			switch (setup.getStep()) {
			case NAME:
				setup.setName(message);
				setup.next();
				break;
			case COOLDOWN:
				setup.setCooldown(message);
				setup.next();
				break;
			}
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		UUID uuid = player.getUniqueId();
		KitEditor ki = null;
		for (KitEditor newKi : KitTemp.getEditingKits()) {
			if (newKi.getPlayer().equals(player)) {
				ki = newKi;
			}
		}

		removeLists(uuid);

		if (event.getInventory().getName().equals("Drag and drop items")) {
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			for (ItemStack is : event.getInventory().getContents()) {
				if (is != null) {
					items.add(is);
				}
			}

			if (items.contains(DefaultItems.obtainBack())) {
				items.remove(DefaultItems.obtainBack());
			}

			if (items.contains(DefaultItems.obtainExit())) {
				items.remove(DefaultItems.obtainExit());
			}

			ItemStack[] newItems = new ItemStack[items.size()];
			newItems = items.toArray(newItems);
			ki.setItems(newItems);
		}

		if (event.getInventory().getName().equals("Drag and drop armour")) {
			ArrayList<ItemStack> armour = new ArrayList<ItemStack>();
			for (ItemStack is : event.getInventory().getContents()) {
				if (is != null) {
					armour.add(is);
				}
			}

			if (armour.contains(DefaultItems.obtainBack())) {
				armour.remove(DefaultItems.obtainBack());
			}

			if (armour.contains(DefaultItems.obtainExit())) {
				armour.remove(DefaultItems.obtainExit());
			}

			ItemStack[] newItems = new ItemStack[armour.size()];
			newItems = armour.toArray(newItems);
			ki.setArmour(newItems);
		}
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		UUID uuid = player.getUniqueId();
		ItemStack item = event.getCurrentItem();
		int slot = event.getSlot();

		if (item == null) {
			if (isInLists(uuid)) {
				player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
				removeLists(uuid);
			}
			return;
		}

		if (item.equals(Material.AIR)) {
			return;
		}

		if (event.getInventory().getName().equals("Edit kit")) {
			KitEditor ki = null;
			for (KitEditor newKi : KitTemp.getEditingKits()) {
				if (newKi.getPlayer().equals(player)) {
					ki = newKi;
				}
			}

			if (item.equals(DefaultItems.obtainBack())) {
				KitsOptionsMenu.openKitsMenu(player);
				KitTemp.removeEditing(uuid);
			}

			if (item.equals(DefaultItems.obtainExit())) {
				player.closeInventory();
				KitTemp.removeEditing(uuid);
			}

			if (item.equals(ki.obtainEditItems())) {
				ki.items();
			}

			if (item.equals(ki.obtainEditArmour())) {
				ki.armour();
			}
		}

		if (event.getInventory().getName().equals("Drag and drop items")) {
			KitEditor ki = null;
			for (KitEditor newKi : KitTemp.getEditingKits()) {
				if (newKi.getPlayer().equals(player)) {
					ki = newKi;
				}
			}

			if (item.equals(DefaultItems.obtainBack())) {
				ArrayList<ItemStack> items = new ArrayList<ItemStack>();
				for (ItemStack is : event.getInventory().getContents()) {
					if (is != null) {
						items.add(is);
					}
				}

				if (items.contains(DefaultItems.obtainBack())) {
					items.remove(DefaultItems.obtainBack());
				}

				if (items.contains(DefaultItems.obtainExit())) {
					items.remove(DefaultItems.obtainExit());
				}

				ItemStack[] newItems = new ItemStack[items.size()];
				newItems = items.toArray(newItems);
				ki.setItems(newItems);
				ki.open();
				event.setCancelled(true);
			}

			if (item.equals(DefaultItems.obtainExit())) {
				ArrayList<ItemStack> items = new ArrayList<ItemStack>();
				for (ItemStack is : event.getInventory().getContents()) {
					if (is != null) {
						items.add(is);
					}
				}

				if (items.contains(DefaultItems.obtainBack())) {
					items.remove(DefaultItems.obtainBack());
				}

				if (items.contains(DefaultItems.obtainExit())) {
					items.remove(DefaultItems.obtainExit());
				}

				ItemStack[] newItems = new ItemStack[items.size()];
				newItems = items.toArray(newItems);
				ki.setItems(newItems);
				// ki.open();
				event.setCancelled(true);
				removeLists(uuid);
			}
		}

		if (event.getInventory().getName().equals("Drag and drop armour")) {
			KitEditor ki = null;
			for (KitEditor newKi : KitTemp.getEditingKits()) {
				if (newKi.getPlayer().equals(player)) {
					ki = newKi;
				}
			}

			if (item.equals(DefaultItems.obtainBack())) {
				ArrayList<ItemStack> armour = new ArrayList<ItemStack>();
				for (ItemStack is : event.getInventory().getContents()) {
					if (is != null) {
						armour.add(is);
					}
				}

				if (armour.contains(DefaultItems.obtainBack())) {
					armour.remove(DefaultItems.obtainBack());
				}

				if (armour.contains(DefaultItems.obtainExit())) {
					armour.remove(DefaultItems.obtainExit());
				}

				ItemStack[] newItems = new ItemStack[armour.size()];
				newItems = armour.toArray(newItems);
				ki.setArmour(newItems);
				ki.open();
				event.setCancelled(true);
			}

			if (item.equals(DefaultItems.obtainExit())) {
				ArrayList<ItemStack> items = new ArrayList<ItemStack>();
				for (ItemStack is : event.getInventory().getContents()) {
					if (is != null) {
						items.add(is);
					}
				}

				if (items.contains(DefaultItems.obtainBack())) {
					items.remove(DefaultItems.obtainBack());
				}

				if (items.contains(DefaultItems.obtainExit())) {
					items.remove(DefaultItems.obtainExit());
				}

				ItemStack[] newItems = new ItemStack[items.size()];
				newItems = items.toArray(newItems);
				ki.setArmour(newItems);
				// ki.open();
				event.setCancelled(true);
				removeLists(uuid);
			}
		}

		if (event.getInventory().getName().equals("Kits menu")) {
			if (item.equals(KitsOptionsItems.obtainBorder())) {
				if (isInLists(uuid)) {
					removeLists(uuid);
					player.playSound(player.getLocation(), Sound.NOTE_BASS, 1,
							1);
				}
				event.setCancelled(true);
			}

			if (event.getInventory().getItem(slot) == null) {
				if (isInLists(uuid)) {
					removeLists(uuid);
					player.playSound(player.getLocation(), Sound.NOTE_BASS, 1,
							1);
				}
			}

			if (item.equals(KitsOptionsItems.obtainRemove())) {
				if (!KitTemp.containsRemoving(uuid)) {
					KitTemp.addRemoving(uuid);
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1,
							2);
					// Message
				} else {
					KitTemp.removeRemoving(uuid);
					player.playSound(player.getLocation(), Sound.NOTE_BASS, 1,
							1);
					// Message
				}
			}

			if (item.equals(KitsOptionsItems.obtainEdit())) {
				if (!KitTemp.containsEditing(uuid)) {
					KitTemp.addEditing(uuid);
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1,
							2);
					// Message
				} else {
					KitTemp.removeEditing(uuid);
					player.playSound(player.getLocation(), Sound.NOTE_BASS, 1,
							1);
					// Message
				}
			}

			if (item.equals(KitsOptionsItems.obtainAdd())) {
				player.closeInventory();
				KitSetup setup = new KitSetup(player);
				KitTemp.addMakingKits(setup);
			}

			if (item.getType().equals(Material.PAPER)) {
				String name = ChatColor.stripColor(
						item.getItemMeta().getDisplayName()).replaceAll(
						"Name: ", "");
				if (KitTemp.containsRemoving(uuid)) {
					Kit kit = null;
					for (Kit k : KitTemp.getKits()) {
						if (k.getName().equals(name)) {
							kit = k;
						}
					}
					kit.remove();
					KitsOptionsMenu.openKitsMenu(player);
					// Message here
				}

				if (KitTemp.containsEditing(uuid)) {
					Kit kit = null;
					for (Kit k : KitTemp.getKits()) {
						if (k.getName().equals(name)) {
							kit = k;
						}
					}
					KitEditor ki = new KitEditor(kit, player);
					ki.add();
					ki.open();
				}
			}
		}
	}

	private void removeLists(UUID uuid) {
		if (KitTemp.containsAdding(uuid)) {
			KitTemp.removeAdding(uuid);
		}

		if (KitTemp.containsRemoving(uuid)) {
			KitTemp.removeRemoving(uuid);
		}

		if (KitTemp.containsEditing(uuid)) {
			KitTemp.removeEditing(uuid);
		}
	}

	private boolean isInLists(UUID uuid) {
		return KitTemp.containsAdding(uuid) || KitTemp.containsRemoving(uuid)
				|| KitTemp.containsEditing(uuid);
	}
}