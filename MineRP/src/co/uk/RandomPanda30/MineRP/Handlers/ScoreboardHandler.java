package co.uk.RandomPanda30.MineRP.Handlers;

import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import co.uk.RandomPanda30.MineRP.Job;
import co.uk.RandomPanda30.MineRP.MineRPData;
import co.uk.RandomPanda30.MineRP.Methods.DataCollection;

public class ScoreboardHandler {

	public static void createScoreboard(Player player) {
		updateScoreboard();
	}

	public static void updateScoreboard() {
		int range = 10;
		for (PlayerHandler ph : MineRPData.players) {
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();

			Objective ob = board.registerNewObjective("health", "dummy");
			ob.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = (String) MineRPData.messagesC
					.get("SCOREBOARD.TITLE");
			title = title.replaceAll("'player'", ph.getName());
			title = title.replaceAll("'job'",
					DataCollection.getJobColour(ph.getJob())
							+ ph.getJob().toString());

			ob.setDisplayName(MessageHandler.formatMessage(title));
			Score score = ob.getScore("  ");
			score.setScore(6);

			Score score7 = ob.getScore(MessageHandler
					.formatMessage((String) MineRPData.messagesC
							.get("SCOREBOARD.MONEY")
							.toString()
							.replaceAll("'money'",
									Integer.toString(ph.getMoney()))));
			score7.setScore(5);

			String lookingAt = "";
			Job lookingAtJob = null;

			Block[] bs = ph.getPlayer()
					.getLineOfSight((Set<Material>) null, range)
					.toArray(new Block[0]);
			List<Entity> near = ph.getPlayer().getNearbyEntities(range, range,
					range);
			for (Block b : bs) {
				for (Entity e : near) {
					if (e.getLocation().distance(b.getLocation()) < 2) {
						if (e instanceof Player) {
							PlayerHandler newPh = new PlayerHandler(null, null,
									0, null);
							Player player2 = (Player) e;
							for (PlayerHandler checkPh : MineRPData.players) {
								if (checkPh.getPlayer().equals(player2)) {
									newPh = checkPh;
								}
							}

							Score scoreB = ob.getScore(" ");
							scoreB.setScore(4);

							lookingAt = player2.getName();
							lookingAtJob = newPh.getJob();
							Score score5 = ob
									.getScore(MessageHandler
											.formatMessage((String) MineRPData.messagesC
													.get("SCOREBOARD.LOOKINGAT")));
							score5.setScore(3);
							Score score4 = ob
									.getScore(MessageHandler
											.formatMessage((String) MineRPData.messagesC
													.get("SCOREBOARD.NAME"))
											+ lookingAt);
							score4.setScore(2);
							Score score6 = ob
									.getScore(MessageHandler.formatMessage((String) MineRPData.messagesC
											.get("SCOREBOARD.JOB")
											+ DataCollection.getJobColour(ph
													.getJob())
											+ lookingAtJob.toString()));
							score6.setScore(1);
							Score score8 = ob
									.getScore(MessageHandler
											.formatMessage((String) MineRPData.messagesC
													.get("SCOREBOARD.BALANCE")
													.toString()
													.replaceAll(
															"'money'",
															Integer.toString(newPh
																	.getMoney()))));
							score8.setScore(0);
						} else {
							return;
						}
					}
				}
			}
			ph.getPlayer().setScoreboard(board);
		}
	}

	public static void removeScoreboard(Player player) {

	}

}