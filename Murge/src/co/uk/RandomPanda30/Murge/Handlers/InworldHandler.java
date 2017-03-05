package co.uk.RandomPanda30.Murge.Handlers;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class InworldHandler {

	private static InworldHandler instance = new InworldHandler();

	public static InworldHandler getHandler() {
		return instance;
	}

	public ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getWorld().equals(MurgeData.getWorld())) {
				players.add(player);
			}
		}
		return players;
	}

	public void sendActionBar(String string) {
		for (Player player : getPlayers()) {
			TitleHandler.getHandler().sendActionBar(player, string);
		}
	}

	public void sendSound(Sound sound, float arg1, float arg2) {
		for (Player player : getPlayers()) {
			player.playSound(player.getLocation(), sound, arg1, arg2);
		}
	}

	@SuppressWarnings("deprecation")
	public void sendSound(String sound, float arg1, float arg2) {
		for (Player player : getPlayers()) {
			player.playSound(player.getLocation(), sound, arg1, arg2);
		}
	}

	public void sendMessage(String message) {
		for (Player player : getPlayers()) {
			player.sendMessage(StringMethods.formatMessage(message));
		}
	}

	public void sendCountdown(String message, boolean play) {
		if (play) {
			sendSound(Sound.ORB_PICKUP, 1, 1);
		}
		sendMessage(message);
	}

	public void sendTitle(String title, String subtitle, int fadeI, int stay,
			int fadeO) {
		for (Player player : getPlayers()) {
			TitleHandler.getHandler().sendTitle(player, title, subtitle, fadeI,
					stay, fadeO);
		}
	}

	public void sendScoreboard() {
		for (Player player : getPlayers()) {
			ScoreboardHandler.getHandler().doScoreboard(player);
		}
	}

	public void resetScoreboard() {
		for (Player player : getPlayers()) {
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			player.setScoreboard(board);
		}
	}

	@SuppressWarnings("unchecked")
	public void sendEndStats() {
		ArrayList<String> messages = (ArrayList<String>) Murge.mMap
				.getStat(MessagesValues.ENDSTATS);
		Entry<UUID, Integer> maxEntry = null;
		for (Entry<UUID, Integer> entry : StatsHandler.getPlayerKill()
				.entrySet()) {
			if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}
		}

		for (String s : messages) {
			s = s.replace("%purgeno",
					Integer.toString(StatsHandler.getPurgeNo()));
			s = s.replace("%no", Integer.toString(StatsHandler.getKillNo()));
			s = s.replace("%player", (maxEntry == null ? "No one" : Bukkit
					.getPlayer(maxEntry.getKey()).getName()));
			sendMessage(s);
		}
	}
}