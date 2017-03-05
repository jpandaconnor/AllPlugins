package co.uk.RandomPanda30.CityRP.Scoreboard;

import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Config;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Messages;
import co.uk.RandomPanda30.CityRP.Misc.Job;
import co.uk.RandomPanda30.CityRP.Objects.Player;
import co.uk.RandomPanda30.CityRP.Resources.MessageUtil;

public class ScoreboardHandler {

	public static void updateScoreboard(Player player) {
		int range = (Integer) Config.PLAYER_RANGE.value;

		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();

		Objective ob = board.registerNewObjective("health", "dummy");
		ob.setDisplaySlot(DisplaySlot.SIDEBAR);

		String title = (String) Messages.SCOREBOARD_TITLE.value;
		title = title.replace("%player", player.getName());
		title = title.replace("%job", player.getJob().toString());

		ob.setDisplayName(MessageUtil.format(title));

		Score score = ob.getScore("  ");
		score.setScore(6);

		Score score7 = ob.getScore(MessageUtil.format(
				(String) Messages.SCOREBOARD_MONEY.value.toString().replace(
						"%money", Integer.toString(player.getMoney()))));
		score7.setScore(5);

		String lookingAt = "";
		Job lookingAtJob = null;

		Block[] bs = player.getPlayer()
				.getLineOfSight((Set<Material>) null, range)
				.toArray(new Block[0]);
		List<Entity> near = player.getPlayer().getNearbyEntities(range, range,
				range);

		for (Block b : bs) {
			for (Entity e : near) {
				if (e.getLocation().distance(b.getLocation()) < 2) {
					if (e instanceof Player) {
						Player newP = new Player(null, null, 0, null, false);
						org.bukkit.entity.Player pl = (org.bukkit.entity.Player) e;
						for (Player checkP : CityRPData.players) {
							if (checkP.getPlayer().equals(pl)) {
								newP = checkP;
							}
						}

						Score scoreB = ob.getScore(" ");
						scoreB.setScore(4);

						lookingAt = pl.getName();
						lookingAtJob = newP.getJob();
						Score score5 = ob.getScore(MessageUtil.format(
								(String) Messages.SCOREBOARD_LOOKINGAT.value));

						score5.setScore(3);
						Score score4 = ob.getScore(MessageUtil
								.format((String) Messages.SCOREBOARD_NAME.value)
								+ lookingAt);

						score4.setScore(2);
						Score score6 = ob.getScore(MessageUtil
								.format((String) Messages.SCOREBOARD_JOB.value
										+ lookingAtJob.toString()));

						score6.setScore(1);
						Score score8 = ob.getScore(MessageUtil
								.format((String) Messages.SCOREBOARD_BALANCE.value
										.toString().replace("%money", Integer
												.toString(newP.getMoney()))));
						score8.setScore(0);
					}else{
						return;
					}
				}
			}
		}
		player.getPlayer().setScoreboard(board);
	}
	
	public static void removeScoreboard(Player player) {
		
	}

}