package co.uk.RandomPanda30.DailyRewards.Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.DailyRewards.DR;
import co.uk.RandomPanda30.DailyRewards.Utils.ItemUtil;
import net.md_5.bungee.api.ChatColor;

public class MainMenu implements Listener {

	static String nadmin = "Daily rewards menu";
	static String admin = "Daily rewards admin menu";

	String claimreward = "Claim your daily reward";

	static boolean canClaim = false;

	public static void openPlayerMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, nadmin);

		ItemStack is = new ItemStack(Material.BOOK, 1);
		ItemMeta im = is.getItemMeta();

		ArrayList<String> lores = new ArrayList<>();
		if (!DR.getPlugin().getConfig()
				.contains(player.getUniqueId().toString())) {
			canClaim = true;
			im.setDisplayName(
					ChatColor.translateAlternateColorCodes('&', "&AClaim!"));
			lores.add(ChatColor.translateAlternateColorCodes('&', "&FClaim your reward now"));
		} else {
			if (DR.getPlugin().getConfig()
					.contains(player.getUniqueId().toString() + ".TimeLeft")) {
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
						"&CCannot claim!"));
				String time = DR.getPlugin().getConfig().getString(
						player.getUniqueId().toString() + ".TimeLeft");

				String[] t = { time };

				int days = 0;
				int hours = 0;
				int minutes = 0;
				int seconds = 0;

				if (!time.contains("d") && !time.contains("h")
						&& !time.contains("m") && !time.contains("s")) {
					time = "0";
				} else {
					if (time.contains("d")) {
						t = t[0].split("d");
						days = Integer.parseInt(t[0]);
						if (t.length == 2) {
							t[0] = t[1];
						}
					}

					if (time.contains("h")) {
						t = t[0].split("h");
						hours = Integer.parseInt(t[0]);
						if (t.length == 2) {
							t[0] = t[1];
						}
					}

					if (time.contains("m")) {
						t = t[0].split("m");
						minutes = Integer.parseInt(t[0]);
						if (t.length == 2) {
							t[0] = t[1];
						}
					}

					if (time.contains("s")) {
						t = t[0].split("s");
						seconds = Integer.parseInt(t[0]);
					}

					String finaltime = "";
					if (days != 0) {
						finaltime += " &B" + days + " &Fdays";
					}

					if (hours != 0) {
						finaltime += " &B" + hours + " &Fhours";
					}

					if (minutes != 0) {
						finaltime += " &B" + minutes + " &Fminutes";
					}

					if (seconds != 0) {
						finaltime += " &B" + seconds + " &Fseconds";
					}

					lores.add(finaltime);
				}
			} else {
				canClaim = true;
				im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
						"&AClaim!"));
				lores.add(ChatColor.translateAlternateColorCodes('&', "&FClaim your reward now"));
			}
		}
		
		
		im.setLore(lores);
		is.setItemMeta(im);
		
		inventory.setItem(0, is);

		// inventory.setItem(0, ItemUtil.createItem(nadmin, Material.TORCH,
		// amount, type, lores));
		player.openInventory(inventory);
	}

	public static void openAdminMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 18, admin);

		player.openInventory(inventory);
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {

	}
}