package co.uk.RandomPanda30.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Main.Main;

public class VoteKickHandler {

	public VoteKickHandler (Main plugin) {
		Main.plugin = plugin;
	}

	public static void checkVotes() {
		if (Main.voteKicks.size() == 10) {
			Main.voteKicks.clear();
			Player kicked = Main.toKick.get("toKick");
			kicked.kickPlayer(Text_Handlers.voteKicked.toString());
			Bukkit.broadcastMessage(Text_Handlers.Btag.toString() + "§F "
					+ kicked.getName().toString()
					+ "§Bhas been vote kicked from the server");
			Main.voteKicks.clear();
			Main.toKick.clear();
		} else if (Main.noKick.size() == 10) {
			Main.noKick.clear();
			Player noKicked = Main.toKick.get("toKick");
			Bukkit.broadcastMessage(Text_Handlers.Btag.toString()
					+ "§BVote kick was cancelled because at least 10 people voted §cNo");
			noKicked.sendMessage(Text_Handlers.Btag.toString()
					+ "§BYou have not been kicked");
			Main.toKick.clear();
			Main.voteKicks.clear();
		}
		Main.plugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						Player delayedKick = Main.toKick.get("toKick");
						delayedKick.sendMessage(Text_Handlers.Btag.toString()
								+ "§BNot enough votes were placed, you haven't kicked");
						Bukkit.broadcastMessage(Text_Handlers.Btag.toString()
								+ "§BPlayer "
								+ delayedKick.getName().toString()
								+ " §Bhasn't been kicked from the server due to a lack of votes");
						Main.toKick.clear();
						Main.voteKicks.clear();
					}
				}, 6000L);
	}
}