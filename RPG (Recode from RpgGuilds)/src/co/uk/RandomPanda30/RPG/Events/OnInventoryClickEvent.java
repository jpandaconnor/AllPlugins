package co.uk.RandomPanda30.RPG.Events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.RPG.RPG;

public class OnInventoryClickEvent implements Listener {

	public RPG plugin;

	public OnInventoryClickEvent (RPG plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (!event.getInventory().getTitle().equals("Bank:")) {
			return;
		}

		for (UUID user : RPG.gbanks) {
			Player bankuser = Bukkit.getPlayer(user);
			bankuser.updateInventory();
		}

		if (plugin.getRPGValues().getConfigC()
				.contains(player.getUniqueId().toString())) {
			String guildn = plugin.getRPGValues().getConfigC()
					.getString(player.getUniqueId().toString() + ".Guild.Name");
			String rank = null;
			for (String key : plugin.getRPGValues().getConfigC()
					.getConfigurationSection("Guilds." + guildn + ".Ranks")
					.getKeys(false)) {
				if (event.getInventory().getTitle().contains(key)) {
					rank = key;
				}
			}

			if (rank != null) {
				event.setCancelled(true);
				event.setResult(Event.Result.DENY);
				ItemStack item = event.getCurrentItem();
				if ((item == null) || (item.getTypeId() == 0)) {
					return;
				}

				ItemMeta im = event.getCurrentItem().getItemMeta();
				if (im.hasDisplayName()) {
					String iname = im.getDisplayName();
					Short green = Short.valueOf((short) 5);
					Short red = Short.valueOf((short) 14);
					if (!plugin
							.getRPGValues()
							.getConfigC()
							.getBoolean(
									"Guilds." + guildn + ".Ranks." + rank + "."
											+ iname)) {
						plugin.getRPGValues()
								.getConfigC()
								.set("Guilds." + guildn + ".Ranks." + rank
										+ "." + iname, Boolean.valueOf(true));
						ArrayList<String> lore = new ArrayList<String>();
						lore.add("True");
						im.setLore(lore);
						item.setItemMeta(im);
						item.setDurability(green.shortValue());
						plugin.getRPGConfig().saveAllConfigs();
					} else {
						plugin.getRPGValues()
								.getConfigC()
								.set("Guilds." + guildn + ".Ranks." + rank
										+ "." + iname, Boolean.valueOf(false));
						ArrayList<String> lore = new ArrayList<String>();
						lore.add("False");
						im.setLore(lore);
						item.setItemMeta(im);
						item.setDurability(red.shortValue());
						plugin.getRPGConfig().saveAllConfigs();
					}
				}
			}
		}
	}
}