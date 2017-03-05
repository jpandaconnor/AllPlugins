package co.uk.RandomPanda30.VShop.Events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.uk.RandomPanda30.VShop.B;
import co.uk.RandomPanda30.VShop.Methods.U;

public class EF implements Listener {

	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		for (String s : B.keys) {
			UUID uuid = U.getLastUUID(s);
			if (player.getUniqueId() == uuid) {
				U.setLastName(s, player.getName());
			}
		}
	}
}