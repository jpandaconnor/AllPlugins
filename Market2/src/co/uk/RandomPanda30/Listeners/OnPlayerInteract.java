package co.uk.RandomPanda30.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Handlers.MessageH;
import co.uk.RandomPanda30.Markets.Main;

public class OnPlayerInteract implements Listener {

	public OnPlayerInteract (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		ItemStack item = player.getItemInHand();
		if (item.getType() != Material.AIR) {
			if (item.getItemMeta().hasDisplayName()) {
				ItemMeta itemMeta = item.getItemMeta();

				if (itemMeta.getDisplayName().equals(MessageH.wandName)) {
					Action action = event.getAction();

					if (action == Action.LEFT_CLICK_BLOCK) {
						event.setCancelled(true);
						Location blockLocation = event.getClickedBlock()
								.getLocation();

						double x = blockLocation.getX();
						double y = blockLocation.getY();
						double z = blockLocation.getZ();
						MessageH.sendCoordinates(player, x, y, z, 1);
						Location newLoc = new Location(null, 0, 0, 0);
						newLoc.setWorld(player.getWorld());
						newLoc.setX(x);
						newLoc.setY(y);
						newLoc.setZ(z);
						Main.pos1.put("pos1", newLoc);
					}

					if (action == Action.RIGHT_CLICK_BLOCK) {
						event.setCancelled(true);
						Location blockLocation = event.getClickedBlock()
								.getLocation();

						double x = blockLocation.getX();
						double y = blockLocation.getY();
						double z = blockLocation.getZ();
						MessageH.sendCoordinates(player, x, y, z, 2);
						Location newLoc = new Location(null, 0, 0, 0);
						newLoc.setWorld(player.getWorld());
						newLoc.setX(x);
						newLoc.setY(y);
						newLoc.setZ(z);
						Main.pos2.put("pos2", newLoc);
					}
				}
			}
		}
	}
}