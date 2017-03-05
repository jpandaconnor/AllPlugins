package co.uk.RandomPanda30.ExpIntercept.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import co.uk.RandomPanda30.ExpIntercept.ExpIntercept;
import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;
import co.uk.RandomPanda30.ExpIntercept.Methods.IO;

public class OnBlockBreakEvent implements Listener {

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		int xp = event.getExpToDrop();
		if (!ExpIntercept.booster) {
			int divdedNo = (Integer) ExpInterceptData.configC
					.get("DIVDEDAMOUNT");
			if (!IO.isNegative(divdedNo)) {
				event.setExpToDrop(((divdedNo / 100) * xp) + xp);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setExpToDrop((int) (xp - (newDiv * xp)));
			}
		} else {
			int divdedNo = ExpIntercept.current.getMulti();
			if (!IO.isNegative(divdedNo)) {
				event.setExpToDrop(((divdedNo / 100) * xp) + xp);
			} else {
				int div = divdedNo * -1;
				double newDiv = (div / 100.0);
				event.setExpToDrop((int) (xp - (newDiv * xp)));
			}
		}
	}
}