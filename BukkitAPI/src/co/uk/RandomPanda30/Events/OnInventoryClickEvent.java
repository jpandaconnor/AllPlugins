package co.uk.RandomPanda30.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class OnInventoryClickEvent implements Listener {

	public OnInventoryClickEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		if (item == null) {
			return;
		}

		if (item.getType() == Material.AIR) {
			return;
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(Text_Handlers.toHub.toString())) {
			p.closeInventory();
			World w = Bukkit.getWorld("Spawn");
			Location l = w.getSpawnLocation();
			p.teleport(l);
			e.setCancelled(true);
		}

		if (item.getItemMeta().getDisplayName() != null
				&& item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.equals(Text_Handlers.toSG.toString())) {
			p.closeInventory();
			World w = Bukkit.getWorld("SurvivalGames_Spawn");
			Location l = w.getSpawnLocation();
			p.teleport(l);
			e.setCancelled(true);
		}

	}

}
