package com.vartala.soulofw0lf.rpgguilds;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

	@EventHandler
	public void OnInteract(InventoryClickEvent event) {
		if (!event.getInventory().getTitle().equals("Bank:")) {
			return;
		}

		for (UUID user : RpgGuilds.gbanks) {
			Player bankuser = Bukkit.getPlayer(user);
			bankuser.updateInventory();
		}
	}

	@EventHandler
	public void onInvClose(InventoryCloseEvent event) {
		if (!event.getInventory().getTitle().equals("Bank:")) {
			return;
		}

		Player player = (Player) event.getPlayer();
		String tmpBank = SerializedInventory.serializeInventory(event
				.getInventory());
		String guild = RpgGuilds.plugin.getConfig().getString(
				player.getUniqueId().toString() + ".Guild.Name");

		RpgGuilds.plugin.getConfig().set(guild, tmpBank);
		RpgGuilds.gbanks.remove(player.getUniqueId());
		RpgGuilds.plugin.saveConfig();
	}
}