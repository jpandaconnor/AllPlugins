package co.uk.RandomPanda30.RPG;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Util {

	public RPG plugin;

	public Util (RPG plugin) {
		this.plugin = plugin;
	}

	public Player getPlayer(UUID uuid) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getUniqueId().equals(uuid)) {
				return player;
			}
		}
		return null;
	}

	public Inventory upgradeInventory(Inventory inv, int size) {
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

	public boolean doesRegionContainLoc(Location loc) {
		for (Map.Entry<String, ProtectedRegion> entry : plugin.getWorldGuard()
				.getRegionManager(loc.getWorld()).getRegions().entrySet()) {
			if (((ProtectedRegion) entry.getValue()).contains((int) loc.getX(),
					(int) loc.getY(), (int) loc.getZ())) {
				return true;
			}
		}
		return false;
	}
}