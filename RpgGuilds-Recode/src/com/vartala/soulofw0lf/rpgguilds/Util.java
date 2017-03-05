package com.vartala.soulofw0lf.rpgguilds;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
public class Util {

	public static String stub = ChatColor.GOLD + "BM: " + ChatColor.RESET;

	@SuppressWarnings("deprecation")
	public static Player getPlayer(UUID uuid) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getUniqueId().equals(uuid)) {
				return player;
			}
		}
		return null;
	}

	public static void sendMessage(Player player, String m) {
		player.sendMessage(stub + m);
	}

	public static Inventory upgradeInventory(Inventory inv, int size) {
		if (inv == null) {
			return inv;
		}
		if (inv.getSize() == size) {
			return inv;
		}
		Inventory temp = Bukkit.createInventory(null, size, "Bank:");
		for (int x = 0; x < inv.getSize(); x++) {
			if (inv.getItem(x) != null) {
				temp.setItem(x, inv.getItem(x));
			}
		}
		return temp;
	}
	
	/*

	public static boolean doesRegionContainLoc(Location loc) {
		for (Map.Entry<String, ProtectedRegion> entry : RpgGuilds.wgPlugin
				.getRegionManager(loc.getWorld()).getRegions().entrySet()) {
			if (((ProtectedRegion) entry.getValue()).contains((int) loc.getX(),
					(int) loc.getY(), (int) loc.getZ())) {
				return true;
			}
		}
		return false;
	}
	*/
	
}