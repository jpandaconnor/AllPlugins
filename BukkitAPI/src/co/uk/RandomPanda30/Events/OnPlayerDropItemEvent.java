package co.uk.RandomPanda30.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import co.uk.RandomPanda30.Handlers.Misc_Handlers;
import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class OnPlayerDropItemEvent implements Listener {

	public OnPlayerDropItemEvent(Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (p.getName().equals(Misc_Handlers.RandomPanda30.toString())
				|| p.getName().equals(Misc_Handlers.riley2465.toString())
				|| p.getName().equals(Misc_Handlers.Danmeal_.toString())) {
			e.setCancelled(false);
			return;
		} else {
			if (p.getWorld().getName().equals("Spawn")) {
				e.setCancelled(true);
				p.sendMessage(Text_Handlers.Btag.toString()
						+ "§CYou cannot drop items in the spawn");
			}else{
				return;
			}
		}
	}

}
