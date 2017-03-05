package co.uk.RandomPanda30.CityRP.Tasks;

import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.CityRP.CityRP;
import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Objects.Player;
import co.uk.RandomPanda30.CityRP.Scoreboard.ScoreboardHandler;

public class Server extends BukkitRunnable {

	public CityRP plugin;

	public Server (CityRP plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		for (Player player : CityRPData.players) {
			ScoreboardHandler.updateScoreboard(player);
		}
	}
}