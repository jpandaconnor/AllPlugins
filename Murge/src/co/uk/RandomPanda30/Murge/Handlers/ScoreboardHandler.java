package co.uk.RandomPanda30.Murge.Handlers;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.StringCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.DeathsCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.EconCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.KillsCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.SurvivalsCollection;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;

public class ScoreboardHandler {

	private static ScoreboardHandler instance = new ScoreboardHandler();

	public static ScoreboardHandler getHandler() {
		return instance;
	}

	@SuppressWarnings("deprecation")
	public void doScoreboard(Player player) {
		UUID uuid = player.getUniqueId();
		if (!MurgeData.isPurge()) {
			if (StatsHandler.inPlayers(uuid)) {
				Scoreboard board = Bukkit.getScoreboardManager()
						.getNewScoreboard();
				Objective obj = board.registerNewObjective("scoreboard",
						"dummy");
				obj.setDisplaySlot(DisplaySlot.SIDEBAR);
				ScoreboardManager sm = Bukkit.getServer()
						.getScoreboardManager();
				player.setScoreboard(sm.getNewScoreboard());
				String isPurge = (MurgeData.isPurge() ? "        %B&lActive      "
						: "      %G&lInactive      ");
				obj.setDisplayName(StringMethods.formatMessage(isPurge));

				Score gap0 = obj.getScore(" ");
				gap0.setScore(6);

				String inBalance = StringMethods.formatMessage("%N&lMoney: %A")
						+ StringCollection.getCollection().getMoneyString()
						+ Double.toString(EconCollection.getCollection()
								.getBalance(uuid));
				Score inBalanceScore = obj.getScore(Bukkit
						.getOfflinePlayer(inBalance));
				inBalanceScore.setScore(5);

				Score gap1 = obj.getScore("  ");
				gap1.setScore(4);

				String inKills = StringMethods
						.formatMessage("%N&lTotal Kills: %A"
								+ Integer.toString(KillsCollection
										.getCollection().getValue(uuid)));
				Score playerKillsScore = obj.getScore(Bukkit
						.getOfflinePlayer(inKills));
				playerKillsScore.setScore(3);

				String inDeaths = StringMethods
						.formatMessage("%N&lTotal Deaths: %A"
								+ Integer.toString(DeathsCollection
										.getCollection().getValue(uuid)));
				Score inDeathsScore = obj.getScore(Bukkit
						.getOfflinePlayer(inDeaths));
				inDeathsScore.setScore(2);

				Score gap2 = obj.getScore("   ");
				gap2.setScore(1);

				String inPurgesSurvived = StringMethods
						.formatMessage("%N&lPurges survived: %A"
								+ Integer.toString(SurvivalsCollection
										.getCollection().getValue(uuid)));
				Score inPurgesSurvivedScore = obj.getScore(Bukkit
						.getOfflinePlayer(inPurgesSurvived));
				inPurgesSurvivedScore.setScore(0);

				player.setScoreboard(board);
			}
		} else {
			if (StatsHandler.inPlayers(uuid)) {
				Scoreboard board = Bukkit.getScoreboardManager()
						.getNewScoreboard();
				Objective obj = board.registerNewObjective("scoreboard",
						"dummy");
				obj.setDisplaySlot(DisplaySlot.SIDEBAR);

				Team players = board.registerNewTeam("Players");

				players.addPlayer(player);
				players.setDisplayName("Players");
				players.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);

				String isPurge = (MurgeData.isPurge() ? "        %B&lActive      "
						: "      %G&lInactive      ");
				obj.setDisplayName(StringMethods.formatMessage(isPurge));

				Score gap0 = obj.getScore(" ");
				gap0.setScore(3);

				String inBalance = StringMethods.formatMessage("%N&lMoney: %A"
						+ StringCollection.getCollection().getMoneyString()
						+ Double.toString(EconCollection.getCollection()
								.getBalance(uuid)));
				Score inBalanceScore = obj.getScore(Bukkit
						.getOfflinePlayer(inBalance));
				inBalanceScore.setScore(2);

				Score gap1 = obj.getScore("  ");
				gap1.setScore(1);

				Integer kills = (StatsHandler.getPlayerKill().get(uuid) == null ? 0
						: StatsHandler.getPlayerKill().get(uuid));
				String inKills = StringMethods.formatMessage("%N&lKills: %A"
						+ Integer.toString(kills));
				Score playerKillsScore = obj.getScore(Bukkit
						.getOfflinePlayer(inKills));
				playerKillsScore.setScore(0);

				player.setScoreboard(board);
			} else {
				Scoreboard board = Bukkit.getScoreboardManager()
						.getNewScoreboard();
				Objective obj = board.registerNewObjective("scoreboard",
						"dummy");
				obj.setDisplaySlot(DisplaySlot.SIDEBAR);

				Team spectators = board.registerNewTeam("Spectators");

				spectators.addPlayer(player);
				spectators.setDisplayName("Spectators");
				spectators
						.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OWN_TEAM);

				String isPurge = (MurgeData.isPurge() ? "        %B&lActive      "
						: "      %G&lInactive      ");
				obj.setDisplayName(StringMethods.formatMessage(isPurge));

				Score gap0 = obj.getScore(" ");
				gap0.setScore(6);

				String inBalance = StringMethods.formatMessage("%N&lMoney: %A")
						+ StringCollection.getCollection().getMoneyString()
						+ Double.toString(EconCollection.getCollection()
								.getBalance(uuid));
				Score inBalanceScore = obj.getScore(Bukkit
						.getOfflinePlayer(inBalance));
				inBalanceScore.setScore(5);

				Score gap1 = obj.getScore("  ");
				gap1.setScore(4);

				String inKills = StringMethods
						.formatMessage("%N&lTotal Kills: %A"
								+ Integer.toString(KillsCollection
										.getCollection().getValue(uuid)));
				Score playerKillsScore = obj.getScore(Bukkit
						.getOfflinePlayer(inKills));
				playerKillsScore.setScore(3);

				String inDeaths = StringMethods
						.formatMessage("%N&lTotal Deaths: %A"
								+ Integer.toString(DeathsCollection
										.getCollection().getValue(uuid)));
				Score inDeathsScore = obj.getScore(Bukkit
						.getOfflinePlayer(inDeaths));
				inDeathsScore.setScore(2);

				Score gap2 = obj.getScore("   ");
				gap2.setScore(1);

				String inPurgesSurvived = StringMethods
						.formatMessage("%N&lPurges survived: %A"
								+ Integer.toString(SurvivalsCollection
										.getCollection().getValue(uuid)));
				Score inPurgesSurvivedScore = obj.getScore(Bukkit
						.getOfflinePlayer(inPurgesSurvived));
				inPurgesSurvivedScore.setScore(0);

				player.setScoreboard(board);
			}
		}
	}

	public void onlineInit() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			StatsHandler.addPlayer(player.getUniqueId());
			ScoreboardHandler.getHandler().doScoreboard(player);
		}
	}
}