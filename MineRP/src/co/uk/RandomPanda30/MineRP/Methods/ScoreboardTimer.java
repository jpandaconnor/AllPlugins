package co.uk.RandomPanda30.MineRP.Methods;

import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.MineRP.MineRP;
import co.uk.RandomPanda30.MineRP.MineRPData;
import co.uk.RandomPanda30.MineRP.Handlers.ScoreboardHandler;

public class ScoreboardTimer extends BukkitRunnable {

	public ScoreboardTimer (MineRP plugin) {
		MineRPData.plugin = plugin;
	}

	public void run() {
		ScoreboardHandler.updateScoreboard();
	}
}