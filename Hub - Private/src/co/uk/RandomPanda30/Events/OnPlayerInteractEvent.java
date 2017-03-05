package co.uk.RandomPanda30.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import co.uk.RandomPanda30.Main.Main;

public class OnPlayerInteractEvent implements Listener {

	public OnPlayerInteractEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action a = e.getAction();
		
		if(player.getItemInHand().getType() == Material.AIR) {
			return;
		}
		if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
			if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
				if (player.getItemInHand().getItemMeta().hasDisplayName()
						&& player.getItemInHand().getItemMeta()
								.getDisplayName().equals("§6Toggle Players")
						&& player.getItemInHand().getType() == Material.WATCH) {
					if (!Main.clicked.containsValue(player)) {
						player.sendMessage("§6Players are now §COff");
						for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
							player.hidePlayer(pl);
							if (pl.hasPermission("hub.alwayssee")) {
								player.showPlayer(pl);
							}
						}
						Main.clicked.put("hider", player);
					} else {
						for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
							player.showPlayer(pl);
						}
						player.sendMessage("§6Players are now §AOn");
						Main.clicked.clear();
					}
				}
			}

		}
	}
}
