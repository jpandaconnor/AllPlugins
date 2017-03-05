package com.vartala.soulofw0lf.rpgguilds;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ActionManager {

	public static void openBank(UUID uuid) {
		RpgGuilds.plugin.reloadConfig();
		String guild = RpgGuilds.plugin.getConfig().getString(
				uuid.toString() + ".Guild.Name");
		String bank = RpgGuilds.plugin.getConfig().getString(guild);
		Inventory inv = null;
		Player player = Util.getPlayer(uuid);

		/*
		if ((RpgGuilds.wgPlugin != null)
				&& (!Util.doesRegionContainLoc(player.getLocation()))) {
			Util.sendMessage(player, "You Cannot Open Your Bank Here");
			return;
		}
		*/

		if (bank == null) {
			if (player.hasPermission("gbank.5row")) {
				inv = Bukkit.createInventory(null, 45, "Bank:");
			} else if (player.hasPermission("gbank.4row")) {
				inv = Bukkit.createInventory(null, 36, "Bank:");
			} else if (player.hasPermission("gbank.3row")) {
				inv = Bukkit.createInventory(null, 27, "Bank:");
			} else if (player.hasPermission("gbank.2row")) {
				inv = Bukkit.createInventory(null, 18, "Bank:");
			} else if (player.hasPermission("gbank.1row")) {
				inv = Bukkit.createInventory(null, 9, "Bank:");
			}
		} else {
			inv = SerializedInventory.deSerializeInventory(bank);
		}

		if (player.hasPermission("gbank.5row")) {
			inv = Util.upgradeInventory(inv, 45);
		} else if (player.hasPermission("gbank.4row")) {
			inv = Util.upgradeInventory(inv, 36);
		} else if (player.hasPermission("gbank.3row")) {
			inv = Util.upgradeInventory(inv, 27);
		} else if (player.hasPermission("gbank.2row")) {
			inv = Util.upgradeInventory(inv, 18);
		} else if (player.hasPermission("gbank.1row")) {
			inv = Util.upgradeInventory(inv, 9);
		}

		if (inv != null) {
			Util.getPlayer(uuid).openInventory(inv);
		}
	}
}