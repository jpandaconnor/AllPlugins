package co.uk.RandomPanda30.Events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import co.uk.RandomPanda30.AdminPlus.AdminPlus;
import co.uk.RandomPanda30.Handlers.Messages;
import co.uk.RandomPanda30.Handlers.Methods;

public class OnPlayerInteractEntityEvent implements Listener {

	public OnPlayerInteractEntityEvent (AdminPlus plugin) {
		AdminPlus.plugin = plugin;
	}

	@EventHandler
	public void onPlayerRightClick(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity hitEntity = event.getRightClicked();
		if (player.hasPermission(Messages.permString + "admin")) {
			if (hitEntity instanceof Player) {
				Player hitPlayer = (Player) hitEntity;
				Inventory playerInv = Methods.createInventory(
						("\u00A7r" + hitPlayer.getName()), 1);
				String[] playerInfo = {
						"",
						Messages.green + "Name - " + Messages.white
								+ hitPlayer.getName(),
						"",
						Messages.green + "World - " + Messages.white
								+ hitPlayer.getWorld().getName(),
						"",
						Messages.green
								+ "Location - "
								+ Messages.white
								+ Messages.red
								+ "X: "
								+ Messages.white
								+ (double) Math.round(hitPlayer.getLocation()
										.getX() * 100000)
								/ 100000
								+ Messages.red
								+ " Y: "
								+ Messages.white
								+ (double) Math.round(hitPlayer.getLocation()
										.getY() * 100000)
								/ 100000
								+ Messages.red
								+ " Z: "
								+ Messages.white
								+ (double) Math.round(hitPlayer.getLocation()
										.getZ() * 100000) / 100000,
						"",
						Messages.green + "Gamemode - " + Messages.white
								+ hitPlayer.getGameMode().toString(),
						"",
						Messages.green + "Unique ID - " + Messages.white
								+ hitPlayer.getUniqueId() };
				playerInv.setItem(0, Methods.createInvItem(Messages.green
						+ hitPlayer.getName(), Material.SKULL_ITEM, 1,
						playerInfo));

				String[] actions = { Messages.green + "Click to see all",
						Messages.green + "options" };
				playerInv.setItem(2, Methods.createInvItem(Messages.green
						+ "Actions", Material.REDSTONE_TORCH_ON, 1, actions));

				String[] exitInfo = { Messages.red + "Click to exit" };
				playerInv.setItem(8, Methods.createInvItem(Messages.red
						+ "Exit", Material.REDSTONE_BLOCK, 1, exitInfo));

				AdminPlus.lookAtPlayer.add(hitPlayer.getName());

				player.openInventory(playerInv);
			}
		}
	}
}
