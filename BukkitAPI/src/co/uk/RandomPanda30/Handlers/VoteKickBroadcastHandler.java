package co.uk.RandomPanda30.Handlers;

import org.bukkit.Bukkit;

import co.uk.RandomPanda30.Main.Main;

public class VoteKickBroadcastHandler {

	public VoteKickBroadcastHandler (Main plugin) {
		Main.plugin = plugin;
	}

	public static void broadcastResult() {
		Bukkit.broadcastMessage(Text_Handlers.Btag.toString()
				+ "§5Vote Results");
		Bukkit.broadcastMessage("§AYes - " + Main.voteKicks.size());
		Bukkit.broadcastMessage("§CNo - " + Main.noKick.size());
	}
}
