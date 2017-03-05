package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Handlers.EncryptionHandler;
import co.uk.RandomPanda30.Murge.Handlers.SetupHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.Beans.Lists;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnAsyncPlayerChatEvent implements Listener {

	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		if (MurgeData.getSetup() != null) {
			SetupHandler sh = MurgeData.getSetup();
			if (event.getRecipients().contains(sh.getPlayer())) {
				event.getRecipients().remove(sh.getPlayer());
			}

			if (sh.getPlayer().equals(event.getPlayer())) {
				String message = event.getMessage();

				switch (sh.getStage()) {
				case HOST:
					sh.next();
					sh.setHost(message);
					StringMethods.sendMessage(
							(String) Murge.mMap.getStat(
									MessagesValues.MYSQLSETUP_DATABASE),
							sh.getPlayer());
					break;
				case DATABASE:
					sh.next();
					sh.setDatabase(message);
					StringMethods.sendMessage(
							(String) Murge.mMap
									.getStat(MessagesValues.MYSQLSETUP_USER),
							sh.getPlayer());
					break;
				case USER:
					sh.next();
					sh.setUser(message);
					StringMethods.sendMessage(
							(String) Murge.mMap
									.getStat(MessagesValues.MYSQLSETUP_PASS),
							sh.getPlayer());
					break;
				case PASSWORD:
					sh.next();
					try {
						sh.setPassword(
								EncryptionHandler.getHandle().encrypt(message));
					} catch (Exception e) {
						e.printStackTrace();
					}
					StringMethods.sendMessage(
							(String) Murge.mMap
									.getStat(MessagesValues.MYSQLSETUP_PORT),
							sh.getPlayer());
					break;
				case PORT:
					sh.setPort(Integer.parseInt(message));
					sh.exit();
					break;
				}
				event.setCancelled(true);
			}
		}

		if (MurgeData.isPurge()) {
			if ((Boolean) Murge.cMap.getStat(ConfigValues.CHAT_SEPERATE)) {
				if (Lists.players.contains(uuid)) {
					for (UUID id : Lists.players) {
						Bukkit.getServer().getPlayer(id)
								.sendMessage(event.getMessage());
					}
					event.setCancelled(true);
				} else {
					for (UUID id : Lists.spectating) {
						Bukkit.getServer().getPlayer(id)
								.sendMessage(event.getMessage());
					}
					event.setCancelled(true);
				}
			}
		}
	}
}