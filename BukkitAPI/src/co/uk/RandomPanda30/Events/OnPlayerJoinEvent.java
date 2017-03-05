package co.uk.RandomPanda30.Events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.uk.RandomPanda30.Handlers.Misc_Handlers;
import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Items.HubSpawn;
import co.uk.RandomPanda30.Items.SpawnCompass;
import co.uk.RandomPanda30.Main.Main;

public class OnPlayerJoinEvent implements Listener {

	public OnPlayerJoinEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Main.playersOnline.add(1);
		Player Player = e.getPlayer();
		if (!Player.hasPlayedBefore()) {
			// Default stuff here
			Player.getInventory().clear();
			SpawnCompass.spawnCompass(Player);
			HubSpawn.hubSpawn(Player);
			Player.setGameMode(GameMode.ADVENTURE);
			
			 if(Player.isFlying() == true) {
				 Player.setFlying(false);
			 }

			// End
			e.setJoinMessage(null);
			Bukkit.broadcastMessage(Text_Handlers.Btag.toString()
					+ "§BWelcome " + Player.getName().toString()
					+ " to the Server");
			Bukkit.getConsoleSender().sendMessage(
					Text_Handlers.tag.toString() + "New player "
							+ Player.getName().toString()
							+ " has joined the server");

			Main.members.put("Memebers", Player);

		} else {
			if (Player.getName().equals(Misc_Handlers.RandomPanda30)) {
				Bukkit.broadcastMessage(Misc_Handlers.Dev_RandomPanda30
						.toString() + "§Fjoined the game");
				Player.setGameMode(GameMode.CREATIVE);
				Player.setOp(true);
				Player.setBanned(false);
				for (Player ps : Bukkit.getOnlinePlayers()) {
					Location psLoc = ps.getLocation();
					ps.playSound(psLoc, Sound.LEVEL_UP, 1, 1);
				}
				if (Player.isFlying() == false) {
					Player.setFlying(true);
				}
				SpawnCompass.spawnCompass(Player);
				e.setJoinMessage(null);
				
				Main.devs.put("Devs", Bukkit
						.getPlayer(Misc_Handlers.RandomPanda30.toString()));
				Main.playersOnline.add(1);

			} else {
				if (Player.getName().equals(Misc_Handlers.riley2465)) {
					Bukkit.broadcastMessage(Misc_Handlers.Admin_riley2465
							.toString() + "§Fhas jonined the game");
					Player.setGameMode(GameMode.CREATIVE);
					for (Player ps : Bukkit.getOnlinePlayers()) {
						Location psLoc = ps.getLocation();
						ps.playSound(psLoc, Sound.LEVEL_UP, 1, 1);
					}
					if (Player.isFlying() == false) {
						Player.setFlying(true);
					}
					SpawnCompass.spawnCompass(Player);
					e.setJoinMessage(null);

					Main.admins.put("Admins", Bukkit
							.getPlayer(Misc_Handlers.riley2465.toString()));
					Main.playersOnline.add(1);

				} else {
					if (Player.getName().equals(Misc_Handlers.Danmeal_)) {
						Bukkit.broadcastMessage(Misc_Handlers.Documentor_Danmeal_
								.toString() + "§Fhas joined the game");
						Player.setGameMode(GameMode.CREATIVE);
						for (Player ps : Bukkit.getOnlinePlayers()) {
							Location psLoc = ps.getLocation();
							ps.playSound(psLoc, Sound.LEVEL_UP, 1, 1);
						}
						if (Player.isFlying() == false) {
							Player.setFlying(true);
						}
						SpawnCompass.spawnCompass(Player);
						e.setJoinMessage(null);
						Main.documentors.put("Documentors", Bukkit
								.getPlayer(Misc_Handlers.Danmeal_.toString()));
						Main.playersOnline.add(1);

					} else {
						// Default stuff here
						Player.getInventory().clear();
						SpawnCompass.spawnCompass(Player);
						HubSpawn.hubSpawn(Player);
						Player.setGameMode(GameMode.ADVENTURE);
						
						 if(Player.isFlying() == true) {
							 Player.setFlying(false);
						 }

						// End
						e.setJoinMessage(null);
						Bukkit.getConsoleSender().sendMessage(
								Text_Handlers.tag.toString()
										+ Player.getName().toString()
										+ " has joined");
						Main.playersOnline.add(1);
					}
				}
			}
		}
	}
}
