package co.uk.RandomPanda30.DailyRewardsPlus.Events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.DailyRewardsPlus.B;
import co.uk.RandomPanda30.DailyRewardsPlus.Items.I;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.N;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.O;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.P;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.R;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.S;

public class EB implements Listener {

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		if (event.getCurrentItem() == null) {
			return;
		}

		if (event.getCurrentItem().getType().equals(Material.AIR)) {
			return;
		}

		for (String s : invs()) {
			if (event.getInventory().getName().contains(s)) {
				event.setCancelled(true);
			}
		}

		if (event.getCurrentItem().equals(I.obtainExitButton())) {
			player.closeInventory();
		}

		if (event.getCurrentItem().equals(I.obtainMainButton(player))) {
			if (event.getCurrentItem().getItemMeta().hasDisplayName()
					&& event.getCurrentItem()
							.getItemMeta()
							.getDisplayName()
							.equals(P.formatMessage((String) B.itemsC
									.get("CLAIMNOW.NAME")))) {
				int space = 0;
				for (ItemStack content : player.getInventory().getContents()) {
					if (content == null) {
						space++;
					}
				}

				if ((Boolean) B.configC.get("RANDOMITEMS.ENABLED")) {
					int no = (Integer) B.configC.get("RANDOMITEMS.NO");
					Random random = new Random();
					ArrayList<ItemStack> isList = new ArrayList<ItemStack>(
							Arrays.asList(N.loadInv()));
					ArrayList<ItemStack> newIsList = new ArrayList<ItemStack>();
					for (int i = 1; i < no; i++) {
						newIsList
								.add(isList.get(random.nextInt(isList.size())));
					}
					if (space >= newIsList.size()) {
						for (ItemStack is : newIsList) {
							player.getInventory().addItem(is);
						}
					} else {
						P.sendMessage(
								(String) B.messagesC.get("CRITICAL.INVFULL"),
								player);
						return;
					}
					R.resetTime(player);
					if (!player.hasPermission("dr.admin")) {
						O.openMenu(player);
					} else {
						O.openAdminMenu(player);
					}
				} else {
					if (space >= N.loadInv().length) {
						for (ItemStack is : N.loadInv()) {
							player.getInventory().addItem(is);
						}
					} else {
						P.sendMessage(
								(String) B.messagesC.get("CRITICAL.INVFULL"),
								player);
						return;
					}
					R.resetTime(player);
					if (!player.hasPermission("dr.admin")) {
						O.openMenu(player);
					} else {
						O.openAdminMenu(player);
					}
				}

				for (String s : S.getCommandsList()) {
					if (s != null) {
						player.performCommand(s);
					}
				}

				for (String s : S.getConsoleCommandsList()) {
					if (s != null) {
						Bukkit.getServer().dispatchCommand(
								Bukkit.getConsoleSender(), s);
					}
				}
			}
		}

		if (event.getCurrentItem().equals(I.obtainEditItemsButton())) {
			O.openItemInventory(player);
		}
	}

	public static ArrayList<String> invs() {
		ArrayList<String> i = new ArrayList<String>();
		i.add("Daily rewards");
		return i;
	}
}