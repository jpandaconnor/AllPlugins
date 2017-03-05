package co.uk.RandomPanda30.DShops.Items;

import java.util.ArrayList;
import java.util.Calendar;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DShops.B;
import co.uk.RandomPanda30.DShops.Methods.L;
import co.uk.RandomPanda30.DShops.Methods.P;
import co.uk.RandomPanda30.DShops.Methods.PA;
import co.uk.RandomPanda30.DShops.Methods.Q;

@SuppressWarnings("unchecked")
public class IE {

	public static ItemStack obtainForRentButton(String location) {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("FORRENTINV.FORRENT.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		for (String s : lores) {
			newLores.add(L.formatMessage(s.replaceAll("'price'", Integer
					.toString((P.isCustomPrice(location) ? P
							.getCustomPrice(location) : PA.getRentPrice())))));
		}
		return Q.createItem((String) B.itemsC.get("FORRENTINV.FORRENT.NAME"),
				Material.EMERALD_BLOCK, 1, 0, newLores, false);
	}

	public static ItemStack obtainPayRentButton(String doorName) {
		ArrayList<String> lores = (ArrayList<String>) B.itemsC
				.get("RENTEDINV.PAYRENT.LORES");
		ArrayList<String> newLores = new ArrayList<String>();
		String finalTime = "";

		String time = (String) B.doorsC.get(doorName + ".time");

		long days = 0;
		long hours = 0;
		long minutes = 0;
		long seconds = 0;

		long cTime = Calendar.getInstance().getTimeInMillis();
		long finTime = Long.parseLong(time) - cTime;

		days = finTime / 1000 / 60 / 60 / 24;
		hours = (finTime / 1000 / 60 / 60) - (days * 24);
		minutes = (finTime / 1000 / 60) - (hours * 60) - (days * 24 * 60);
		seconds = (finTime / 1000) - (minutes * 60) - (hours * 60 * 60)
				- (days * 24 * 60 * 60);

		if (days != 0) {
			finalTime += "%A" + days + "%Nd";
		}

		if (hours != 0) {
			finalTime += " %A" + hours + "%Nh";
		}

		if (minutes != 0) {
			finalTime += " %A" + minutes + "%Nm";
		}

		if (seconds != 0) {
			finalTime += " %A" + seconds + "%Ns";
		}

		for (String s : lores) {
			newLores.add(L.formatMessage(s).replaceAll("'time'", finalTime));
		}
		return Q.createItem((String) B.itemsC.get("RENTEDINV.PAYRENT.NAME"),
				Material.REDSTONE_TORCH_ON, 1, 0, newLores, false);
	}

	public static ItemStack obtainPaperDetails() {
		return Q.createItem(
				(String) B.itemsC.get("FORRENTINV.PAPERDETAILS.NAME"),
				Material.PAPER, 1, 0, new ArrayList<String>(), false);
	}
}