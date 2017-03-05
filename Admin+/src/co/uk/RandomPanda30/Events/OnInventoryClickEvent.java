package co.uk.RandomPanda30.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.AdminPlus.AdminPlus;
import co.uk.RandomPanda30.Handlers.Messages;
import co.uk.RandomPanda30.Handlers.Methods;

public class OnInventoryClickEvent implements Listener {

	public OnInventoryClickEvent (AdminPlus plugin) {
		AdminPlus.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();
		Inventory inventory = event.getInventory();
		Player player = (Player) event.getWhoClicked();

		if (item == null) {
			return;
		}

		if (item.getType() == Material.AIR) {
			return;
		}

		if (inventory.getName().startsWith("\u00A7r")) {
			event.setCancelled(true);
		}
		if (inventory.getName().startsWith("\u00A7r")) {

			if (item.getType() == Material.REDSTONE_BLOCK
					&& item.getItemMeta().hasDisplayName()
					&& item.getItemMeta().getDisplayName()
							.equals(Messages.red + "Exit")) {
				player.closeInventory();
			}

			if (item.getType() == Material.REDSTONE_TORCH_ON
					&& item.getItemMeta().hasDisplayName()
					&& item.getItemMeta().getDisplayName()
							.equals(Messages.green + "Actions")) {
				Inventory actionInv = Methods.createInventory("\u00A7r"
						+ "Actions", 5);

				String[] banInfo = { Messages.green + "Click to ban" };
				actionInv.setItem(0, Methods.createInvItem(Messages.green
						+ "Ban", Material.PAPER, 1, banInfo));

				String[] exitInfo = { Messages.red + "Click to exit" };
				actionInv.setItem(44, Methods.createInvItem(Messages.red
						+ "Exit", Material.REDSTONE_BLOCK, 1, exitInfo));

				player.openInventory(actionInv);
			}

			if (item.getType() == Material.PAPER
					&& item.getItemMeta().hasDisplayName()
					&& item.getItemMeta().getDisplayName()
							.equals(Messages.green + "Ban")) {
				//player.kickPlayer(player.getName());
			}
		}
	}

}
