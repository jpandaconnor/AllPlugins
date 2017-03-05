package co.uk.RandomPanda30.MineRP.Methods;

import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.MineRP.MineRP;
import co.uk.RandomPanda30.MineRP.MineRPData;
import co.uk.RandomPanda30.MineRP.Handlers.PlayerHandler;

public class PayTimer extends BukkitRunnable {
	
	public PayTimer(MineRP plugin) {
		MineRPData.plugin = plugin;
	}
	
	public void run() {
		for(PlayerHandler ph : MineRPData.players) {
			
		}
	}

}
