package co.uk.RandomPanda30.Wild.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import co.uk.RandomPanda30.Wild.Main;
import co.uk.RandomPanda30.Wild.Handler.MessageH;

public class OnPlayerInteractEvent implements Listener {

	public OnPlayerInteractEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		World world = player.getWorld();
		double max = 1000;
		Action action = event.getAction();
		if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (block.getType() == Material.SIGN_POST
					|| block.getType() == Material.SIGN
					|| block.getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) block.getState();
				if (sign.getLine(0).equalsIgnoreCase(MessageH.randomSign1)) {
					Location location = new Location(world, 0, 0, 0);
					location.setX(world.getSpawnLocation().getX()
							+ Math.random() * max * 2 - max);
					location.setZ(world.getSpawnLocation().getZ()
							+ Math.random() * max * 2 - max);
					location.setY(world.getHighestBlockYAt(
							location.getBlockX(), location.getBlockZ()));
					player.teleport(location);
				}
			}
		}
	}

}
