package co.uk.RandomPanda30.ERHub.Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import co.uk.RandomPanda30.ERHub.ERHubData;

@SuppressWarnings("unchecked")
public class OnPlayerJoinEvent implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ArrayList<String> worlds = (ArrayList<String>) ERHubData.configC
				.get("WORLDS");
		Bukkit.broadcastMessage(Bukkit.getServer().getName());
		Bukkit.broadcastMessage(Bukkit.getServerId());

		for (String s : worlds) {
			if (player.getLocation().getWorld().getName().equals(s)) {
				player.getActivePotionEffects().clear();
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,
						999999999, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
						999999999, 3));
			}
		}
	}
}