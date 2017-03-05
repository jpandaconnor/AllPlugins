package co.uk.RandomPanda30.DailyRewardsPlus.Items;

import java.util.ArrayList;
import java.util.Calendar;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.DailyRewardsPlus.B;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.P;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.Q;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.R;

@SuppressWarnings("unchecked")
public class I {

	public static ItemStack obtainExitButton() {
		return Q.createItem((String) B.itemsC.get("EXITBUTTON.NAME"),
				Material.REDSTONE_BLOCK, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainEditItemsButton() {
		return Q.createItem((String) B.itemsC.get("EDITITEMS.NAME"),
				Material.BOOK, 1, 0, new ArrayList<String>(), false);
	}

	public static ItemStack obtainMainButton(Player player) {
		ItemStack is = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
		ItemMeta im = is.getItemMeta();
		if (R.getTime(player) > System.currentTimeMillis()) {
			im.setDisplayName(P.formatMessage((String) B.itemsC
					.get("TIME.NAME")));
			ArrayList<String> lores = (ArrayList<String>) B.itemsC
					.get("TIME.LORES");
			ArrayList<String> newLores = new ArrayList<String>();

			String finalTime = "";

			long time = Calendar.getInstance().getTimeInMillis();

			long hours = 0;
			long minutes = 0;
			long seconds = 0;

			long tt = time - R.getTime(player);
			hours = tt / (1000 * 60 * 60);
			minutes = (tt % (1000 * 60 * 60)) / (1000 * 60);
			seconds = ((tt % (1000 * 60 * 60)) % (1000 * 60)) / 1000;

			if (hours != 0) {
				finalTime += "%A" + hours + "%Nh";
			}

			if (minutes != 0) {
				finalTime += " %A" + minutes + "%Nm";
			}

			if (seconds != 0) {
				finalTime += " %A" + seconds + "%Ns";
			}

			for (String lore : lores) {
				newLores.add(P.formatMessage((lore).replaceAll("'tijd'",
						finalTime.replaceAll("-", ""))));
			}
			im.setLore(newLores);
			is.setItemMeta(im);
		} else {
			im.setDisplayName(P.formatMessage((String) B.itemsC
					.get("CLAIMNOW.NAME")));
			ArrayList<String> lores = (ArrayList<String>) B.itemsC
					.get("CLAIMNOW.LORES");
			ArrayList<String> newLores = new ArrayList<String>();
			for (String lore : lores) {
				newLores.add(P.formatMessage(lore));
			}
			im.setLore(newLores);
			is.setItemMeta(im);
		}
		return is;
	}
}