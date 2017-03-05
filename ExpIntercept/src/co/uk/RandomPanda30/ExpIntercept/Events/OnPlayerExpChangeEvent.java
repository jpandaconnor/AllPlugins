package co.uk.RandomPanda30.ExpIntercept.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import co.uk.RandomPanda30.ExpIntercept.ExpIntercept;
import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;
import co.uk.RandomPanda30.ExpIntercept.Methods.IO;

public class OnPlayerExpChangeEvent implements Listener {

	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent event) {
		int xp = event.getAmount();

		if (!ExpIntercept.booster) {
			int divdedNo = (Integer) ExpInterceptData.configC
					.get("DIVDEDAMOUNT");
			if (!IO.isNegative(divdedNo)) {
				event.setAmount(((divdedNo / 100) * xp) + xp);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setAmount((int) (xp - (newDiv * xp)));
			}
		} else {
			int divdedNo = ExpIntercept.current.getMulti();
			if (!IO.isNegative(divdedNo)) {
				event.setAmount(((divdedNo / 100) * xp) + xp);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setAmount((int) (xp - (newDiv * xp)));
			}
		}
	}
}