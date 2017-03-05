package co.uk.RandomPanda30.Phones.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Phones.Phones;
import co.uk.RandomPanda30.Phones.Files.Messages;
import co.uk.RandomPanda30.Phones.Methods.PhonesMethods;

public class OnPlayerInteractEvent implements Listener {

	public OnPlayerInteractEvent (Phones plugin) {
		Phones.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		Action action = event.getAction();

		if (action == Action.RIGHT_CLICK_AIR
				|| action == Action.RIGHT_CLICK_BLOCK) {
			if (!item.getType().equals(Material.AIR)) {
				Bukkit.broadcastMessage("1");
				if (item.getItemMeta().hasDisplayName()
						&& item.getItemMeta()
								.getDisplayName()
								.equals(PhonesMethods
										.formatMessage((String) Messages.ITEM_PHONE_NAME.value))
						&& item.getType().equals(Material.SKULL_ITEM)) {
					Bukkit.broadcastMessage("2");

					PhonesMethods.openMainMenu(player);
				}
			}
		}
	}
}
