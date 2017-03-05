package co.uk.RandomPanda30.ClearJoin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InventoryHandler implements Listener {

	public InventoryHandler(Main plugin) {
		Main.plugin = plugin;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("clearjoin.clear")) {
			e.getPlayer().getInventory().clear();
		} else {
			return;
		}
	}

}
