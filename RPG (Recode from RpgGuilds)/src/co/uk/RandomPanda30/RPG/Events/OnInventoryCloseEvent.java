package co.uk.RandomPanda30.RPG.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Handlers.InventoryH;

public class OnInventoryCloseEvent implements Listener {

	public RPG plugin;

	public OnInventoryCloseEvent (RPG plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInvClose(InventoryCloseEvent event) {
		if (!event.getInventory().getTitle().equals("Bank:")) {
			return;
		}

		Player player = (Player) event.getPlayer();
		String tmpBank = InventoryH.getInstance().serializeInventory(
				event.getInventory());
		String guild = (String) plugin.getRPGValues().getConfigC().get(
				player.getUniqueId().toString() + ".Guild.Name");
		plugin.getRPGValues().getConfigC().set(guild, tmpBank);
		RPG.gbanks.remove(player.getUniqueId());
		plugin.getRPGConfig().saveAllConfigs();
	}
}