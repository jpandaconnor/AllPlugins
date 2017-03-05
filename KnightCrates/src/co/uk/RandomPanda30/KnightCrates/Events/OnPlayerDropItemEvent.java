package co.uk.RandomPanda30.KnightCrates.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;
import co.uk.RandomPanda30.KnightCrates.Items.Crates;

public class OnPlayerDropItemEvent implements Listener {

	public KnightCrates plugin;

	public OnPlayerDropItemEvent (KnightCrates plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		ItemStack is = event.getItemDrop().getItemStack();

		if (is.getItemMeta().equals(Crates.getCrate().getItemMeta())) {
			KnightCrates.sendMessage(
					KnightCrates.tag + "&CYou cannot drop these items", player);
			event.setCancelled(true);
		}
	}
}
