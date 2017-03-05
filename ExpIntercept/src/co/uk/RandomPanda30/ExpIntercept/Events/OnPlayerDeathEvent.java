package co.uk.RandomPanda30.ExpIntercept.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import co.uk.RandomPanda30.ExpIntercept.ExpIntercept;
import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;
import co.uk.RandomPanda30.ExpIntercept.Methods.IO;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerDeathEvent implements Listener {

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		int xp = event.getDroppedExp();
		if (!ExpIntercept.booster) {
			int divdedNo = (Integer) ExpInterceptData.configC
					.get("DIVDEDAMOUNT");
			if (!IO.isNegative(divdedNo)) {
				event.setDroppedExp(((divdedNo / 100) * xp) + xp);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setDroppedExp((int) (xp - (newDiv * xp)));
			}
		} else {
			int divdedNo = ExpIntercept.current.getMulti();
			if (!IO.isNegative(divdedNo)) {
				event.setDroppedExp(((divdedNo / 100) * xp) + xp);
				Bukkit.broadcastMessage(ChatColor.RED
						+ Integer.toString(ExpIntercept.current.getMulti()));
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setDroppedExp((int) (xp - (newDiv * xp)));
				Bukkit.broadcastMessage(ChatColor.RED
						+ Integer.toString(ExpIntercept.current.getMulti()));
			}
		}
	}
}