package co.uk.RandomPanda30.SpawnStrict;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public Main plugin;

	public String tag = "&F[&BSpawnStrict&f] ";

	@Override
	public void onEnable() {
		plugin = this;

		Bukkit.getConsoleSender().sendMessage(ChatColor
				.translateAlternateColorCodes('&', tag + "&Ais being enabled"));

		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.translateAlternateColorCodes('&',
						tag + "&Cis being disabled"));
		plugin = null;
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();

		if (block != null) {
			if (block.getType().equals(Material.MOB_SPAWNER)) {
				if (!player.hasPermission("spawnstrict.break")) {
					player.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "&CYou cannot mine this"));
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		Item is = event.getItem();

		if (is.getItemStack().getType().equals(Material.MOB_SPAWNER)) {
			if (!player.hasPermission("spawnstrict.pickup")) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&CYou cannot pick this up"));
				event.setCancelled(true);
			}
		}
	}
}