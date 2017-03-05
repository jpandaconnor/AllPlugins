package co.uk.RandomPanda30.ItemHandlerPlus.Events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

import co.uk.RandomPanda30.ItemHandlerPlus.Displays.D;
import co.uk.RandomPanda30.ItemHandlerPlus.Displays.DA;
import co.uk.RandomPanda30.ItemHandlerPlus.Displays.DList;
import co.uk.RandomPanda30.ItemHandlerPlus.Items.I;
import co.uk.RandomPanda30.ItemHandlerPlus.Methods.V;

public class EA implements Listener {

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		if (event.getCurrentItem() == null) {
			return;
		}

		if (event.getCurrentItem().getType().equals(Material.AIR)) {
			return;
		}

		for (String s : DList.getInvNames()) {
			if (event.getInventory().getName().contains(s)) {
				event.setCancelled(true);
			}
		}

		if (event.getCurrentItem().equals(I.obtainExitButton())) {
			player.closeInventory();
		}

		if (event.getCurrentItem().equals(I.obtainHotbarRestriction())) {
			DA.openHotbarMenu(player);
		}

		/*
		 * if (event.getCurrentItem().equals(I.obtainItemDropRestrction())) {
		 * DB.openItemInventory(player); }
		 */

		InventoryView bottom = event.getView();
		if (event.getRawSlot() >= bottom.getTopInventory().getSize()) {
			if (player.getGameMode() == GameMode.ADVENTURE
					|| player.getGameMode() == GameMode.SURVIVAL) {
				for (int i = 0; i < 9; i++) {
					if (event.getSlot() == i) {
						if (V.isEnabled(i + 1) == false) {
							event.setCancelled(true);
						}
					}
				}
			}
		}

		if (event.getInventory().getName().equals("Click to toggle on/off")) {
			String name = event.getCurrentItem().getItemMeta().getDisplayName();
			for (int i = 1; i < 10; i++) {
				if (name.contains(Integer.toString(i))) {
					V.toggle(i);
					DA.openHotbarMenu(player);
				}
			}
		}

		if (event.getCurrentItem().equals(I.obtainBackButton())) {
			if (event.getInventory().getName().equals("Click to toggle on/off")) {
				D.openIHPMenu(player);
			}
		}
	}
}