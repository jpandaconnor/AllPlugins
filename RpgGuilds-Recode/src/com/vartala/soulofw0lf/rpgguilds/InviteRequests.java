package com.vartala.soulofw0lf.rpgguilds;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class InviteRequests {

	public RpgGuilds plugin;

	public UUID sender;
	public UUID receiver;

	public long ticks;

	public InviteRequests (UUID sender, UUID reciever, RpgGuilds plugin) {
		this.sender = sender;
		this.receiver = reciever;
		this.ticks = 2 * 60 * 20;
		this.plugin = plugin;

		start();

		GuildHandler.requests.add(this);
	}

	public void start() {
		Bukkit.getServer().getScheduler()
				.scheduleSyncDelayedTask(RpgGuilds.plugin, new Runnable() {
					@Override
					public void run() {
						if (plugin.getConfig().getString(
								"Pending." + receiver.toString()) == null)
							return;
						if (Bukkit.getPlayer(sender) != null) {
							Player pSender = Bukkit.getPlayer(sender);
							pSender.sendMessage(
									"The invite request has timed out!");
						}

						if (Bukkit.getPlayer(receiver) != null) {
							Player pSender = Bukkit.getPlayer(receiver);
							pSender.sendMessage(
									"The invite request has timed out!");
						}

						plugin.getConfig().set("Pending." + receiver.toString(),
								null);
						plugin.saveConfig();
						GuildHandler.requests.remove(this);
					}
				}, ticks);
	}

	public void cut() {
		plugin.getConfig().set("Pending." + receiver.toString(), null);
		plugin.saveConfig();
	}
}
