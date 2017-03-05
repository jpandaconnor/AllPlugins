package co.uk.RandomPanda30.RPG.Handlers;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Util;

public class ActionH {

	public RPG plugin;

	public ActionH (RPG plugin) {
		this.plugin = plugin;
	}

	public Util util = new Util(plugin);

	public void openBank(UUID uuid) {
		plugin.getRPGConfig().initAllConfigs();
		String guild = (String) plugin.getRPGValues().getConfigC()
				.get(uuid.toString() + ".Guild.Name");
		String bank = (String) plugin.getRPGValues().getConfigC().get(guild);
		Inventory inv = null;
		Player player = util.getPlayer(uuid);

		if ((plugin.getWorldGuard() != null)
				&& (!util.doesRegionContainLoc(player.getLocation()))) {
			// Util.sendMessage(player, "You Cannot Open Your Bank Here");
			return;
		}

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
			inv = InventoryH.getInstance().deSerializeInventory(bank);
		}

		if (player.hasPermission("gbank.5row")) {
			inv = util.upgradeInventory(inv, 45);
		} else if (player.hasPermission("gbank.4row")) {
			inv = util.upgradeInventory(inv, 36);
		} else if (player.hasPermission("gbank.3row")) {
			inv = util.upgradeInventory(inv, 27);
		} else if (player.hasPermission("gbank.2row")) {
			inv = util.upgradeInventory(inv, 18);
		} else if (player.hasPermission("gbank.1row")) {
			inv = util.upgradeInventory(inv, 9);
		}

		if (inv != null) {
			util.getPlayer(uuid).openInventory(inv);
		}
	}
}