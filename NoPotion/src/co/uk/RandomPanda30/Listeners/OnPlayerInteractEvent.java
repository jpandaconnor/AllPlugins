package co.uk.RandomPanda30.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.NoPotion.Main;

public class OnPlayerInteractEvent implements Listener {

	public OnPlayerInteractEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		if (item == null) {
			return;
		}

		if (item.getType().equals(Material.POTION)) {
			event.setCancelled(true);
			player.sendMessage(ChatColor.GOLD
					+ "All Potions are disabled. This is a creative server.");
		}
	}

}
