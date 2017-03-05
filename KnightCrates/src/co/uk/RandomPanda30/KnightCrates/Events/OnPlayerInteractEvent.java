package co.uk.RandomPanda30.KnightCrates.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;
import co.uk.RandomPanda30.KnightCrates.Menu.CrateMenu;

public class OnPlayerInteractEvent implements Listener {
	
	public KnightCrates plugin;
	
	public OnPlayerInteractEvent(KnightCrates plugin) {
		this.plugin= plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();		
		Action action = event.getAction();
		
		Block block = event.getClickedBlock();
		
		if (block == null) {
			return;
		}
		
		Location location = block.getLocation();
		
		
		
		if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if(block.getType().equals(Material.CHEST)) {
				if(KnightCrates.locations.contains(location)) {
					CrateMenu.openCrateMenu(player);
					event.setCancelled(true);
				}
			}
		}
	}
}