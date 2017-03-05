package co.uk.RandomPanda30.VShop.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.VShop.Files.Items;
import co.uk.RandomPanda30.VShop.Files.Messages;
import co.uk.RandomPanda30.VShop.Items.I;
import co.uk.RandomPanda30.VShop.Methods.R;
import co.uk.RandomPanda30.VShop.Methods.U;
import co.uk.RandomPanda30.VShop.Methods.W;
import co.uk.RandomPanda30.VShop.Methods.X;

public class ED implements Listener {

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Inventory inventory = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();

		if (item == null) {
			return;
		}

		if (item.getType().equals(Material.AIR)) {
			return;
		}

		if (inventory.getName().startsWith("\u00A7r")) {
			event.setCancelled(true);
			if (item.equals(I.obtainExit())) {
				R.closeInventory(player);
			}

			if (item.equals(I.obtainBuy())) {
				U.buyPlot(
						inventory.getName().replaceAll("\u00A7r", ""),
						player,
						U.getPlotPrice(inventory.getName().replaceAll(
								"\u00A7r", "")));
				R.closeInventory(player);
				X.sendMessage(
						(String) Messages.VSHOPS_BOUGHT.value.toString()
								.replaceAll(
										"'name'",
										inventory.getName().replaceAll(
												"\u00A7r", "")), player);
			}

			if (item.equals(I.obtainSell())) {
				U.sellPlot(inventory.getName().replaceAll("\u00A7r", ""),
						player);
				R.closeInventory(player);
				X.sendMessage((String) Messages.VSHOPS_SOLD.value, player);
			}

			if (item.getType().equals(Material.REDSTONE_TORCH_ON)
					&& item.getItemMeta().hasDisplayName()
					&& item.getItemMeta()
							.getDisplayName()
							.equals(X
									.formatMessage((String) Items.ITEM_UPDATETIME_NAME.value))) {
				if (W.hasEnough(
						player,
						U.getPlotPrice(inventory.getName().replaceAll(
								"\u00A7r", "")))) {
					R.closeInventory(player);
					U.resetTime(inventory.getName().replaceAll("\u00A7r", ""));
					R.openOwnerMenu(player,
							inventory.getName().replaceAll("\u00A7r", ""));
					W.withdrawMoney(
							U.getPlotPrice(inventory.getName().replaceAll(
									"\u00A7r", "")), player);
				} else {
					X.sendMessage(
							(String) Messages.CRITICAL_CANNOTAFFORD.value,
							player);
				}
			}
		}
	}
}