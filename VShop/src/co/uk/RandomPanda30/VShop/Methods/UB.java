package co.uk.RandomPanda30.VShop.Methods;

import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.VShop.A;
import co.uk.RandomPanda30.VShop.B;

public class UB extends BukkitRunnable {

	public UB (A plugin) {
		A.plugin = plugin;
	}

	public void run() {
		for (String s : B.keys) {
			String preT = B.shopsC.getString(s + ".owner.time");
			if (preT != "n/a") {
				long t = Long.parseLong(B.shopsC.getString(s + ".owner.time"));
				if (Calendar.getInstance().getTimeInMillis() > t) {
					Player player = Bukkit.getServer().getPlayer(
							U.getLastUUID(s));
					U.sellPlot(s, player);
				}
			}
		}
	}
}