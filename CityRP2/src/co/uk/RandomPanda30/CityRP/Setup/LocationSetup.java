package co.uk.RandomPanda30.CityRP.Setup;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Data;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Config;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Messages;
import co.uk.RandomPanda30.CityRP.Items.LocationItems;
import co.uk.RandomPanda30.CityRP.Objects.Player;
import co.uk.RandomPanda30.CityRP.Objects.Temp.Data_Editing;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil;
import co.uk.RandomPanda30.CityRP.Resources.ExceptionUtil.Cause;
import co.uk.RandomPanda30.CityRP.Resources.ItemUtil;
import co.uk.RandomPanda30.CityRP.Resources.MessageUtil;

@SuppressWarnings({ "unchecked", "deprecation" })
public class LocationSetup implements Listener {

	CityRP plugin;

	public LocationSetup (CityRP plugin) {
		this.plugin = plugin;

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	/*
	 * 
	 * In this event, the plugin will check the items that have been placed
	 * 
	 */

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = CityRPData.getPlayerObject(event.getPlayer());
		Location location = event.getBlock().getLocation();

		/* Spawn item */
		if (player.getPlayer().getItemInHand() != null) {
			Bukkit.broadcastMessage("1");
			if (ItemUtil.isSame(player.getPlayer().getItemInHand(),
					LocationItems.getSpawnItem())) {
				Bukkit.broadcastMessage("2");
				if (!CityRPData.editingLocation.equals(player)) {
					Bukkit.broadcastMessage("3");
					MessageUtil.sendMessage(
							(String) Messages.LOCATIONSETUP_ERRRM.value,
							player);
					player.getPlayer().getInventory()
							.remove(LocationItems.getSpawnItem());
					return;
				}

				Bukkit.broadcastMessage("4");

				Bukkit.broadcastMessage("Test");
				player.getPlayer().getInventory()
						.remove(LocationItems.getSpawnItem());
				event.getBlock().setType(Material.AIR);
				player.getPlayer().sendBlockChange(location,
						Material.STAINED_CLAY, (byte) 14);
				Data.configf.set("LOCATION.SPAWN.SET", true);
				Data.configf.set("LOCATION.SPAWN.LOCATION", location);

				try {
					Data.configf.save(Data.config);
				} catch (IOException e) {
					new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
							"Couldn't save in the config: BlockPlaceEvent in LocationSetup");
				}
				event.setCancelled(true);
			}
		}
	}

	/*
	 * 
	 * In this event, the plugin will check if the location has been set and
	 * checks the location if true
	 * 
	 */

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = CityRPData.getPlayerObject(event.getPlayer());

		Block block = event.getBlock();
		Location blockLocation = block.getLocation();

		/* Spawn item */
		if ((Boolean) Config.LOCATION_SPAWN_SET.value) {
			if (!CityRPData.editingLocation.equals(player.getPlayer())) {
				// Message here
				event.setCancelled(true);
				return;
			}

			Location location = (Location) Config.LOCATION_SPAWN_LOCATION.value;
			if (blockLocation.equals(location)) {
				player.getPlayer().sendBlockChange(blockLocation, Material.AIR,
						(byte) 0);
				// Send message here
				Data.configf.set("LOCATION.SPAWN.SET", false);
				Data.configf.set("LOCATION.SPAWN.LOCATION", "");
				try {
					Data.configf.save(Data.config);
				} catch (IOException e) {
					new ExceptionUtil(e.getStackTrace(), Cause.FILEWENTWRONG,
							"Couldn't save in the config: BlockBreakEvent in LocationSetup");
				}
				event.setCancelled(true);
			}
		}

	}

	/*
	 * 
	 * In this event, the plugin will simply check the dropped item
	 * 
	 */

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		Player player = CityRPData.getPlayerObject(event.getPlayer());

		ItemStack is = event.getItemDrop().getItemStack();

		/* Spawn item */
		if (is.getItemMeta()
				.equals(LocationItems.getSpawnItem().getItemMeta())) {
			MessageUtil.sendMessage((String) Messages.ITEM_MAYNOTDROP.value,
					player);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = CityRPData.getPlayerObject(event.getPlayer());

		/*
		 * Remove this here and check if the player has interacted with
		 * something whilst editing
		 */
		if (!CityRPData.editingLocation.equals(player.getPlayer())) {
			// Message here
			player.getPlayer().getInventory()
					.remove(LocationItems.getSpawnItem());
			return;
		}
	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = CityRPData.getPlayerObject(event.getPlayer());

		// Dump back players shit and stuff

		CityRPData.editingLocation = null;
	}

	/*
	 * Adding player to the editing thingy methods should really be here
	 */

	public static void addToEditing(Player player) {
		org.bukkit.entity.Player np = player.getPlayer();

		if (CityRPData.editingLocation != null) {
			MessageUtil.sendMessage(
					(String) Messages.LOCATIONSETUP_ALREADYEDITING.value
							.toString().replace("%name", player.getName()),
					player);
			return;
		}

		CityRPData.editingLocation = player;

		player.setEditingData(new Data_Editing(np.getGameMode(),
				np.getInventory().getContents(),
				np.getInventory().getArmorContents(), np.getExp(),
				np.getLevel(), np.getHealth(), np.getFoodLevel(),
				np.getActivePotionEffects()));
		np.getInventory().clear();

		np.getInventory().setItem(0, LocationItems.getSpawnItem());

		MessageUtil.sendMessage((String) Messages.LOCATIONSETUP_EDITING.value,
				player);
	}
}