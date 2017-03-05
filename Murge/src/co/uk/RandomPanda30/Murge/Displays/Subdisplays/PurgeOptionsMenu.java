package co.uk.RandomPanda30.Murge.Displays.Subdisplays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.PurgeOptionsItems;

public class PurgeOptionsMenu {

	public static void openPurgeOptionsMenu(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 45,
				"Purge options settings");

		inventory.setItem(0, PurgeOptionsItems.obtainWeatherEnabled());
		inventory.setItem(9, PurgeOptionsItems.obtainUseVault());
		inventory.setItem(18, PurgeOptionsItems.obtainUseMySQL());

		inventory.setItem(2, PurgeOptionsItems.obtainBreakingDuringPurge());
		inventory.setItem(11, PurgeOptionsItems.obtainPlacingDuringPurge());

		inventory.setItem(4, PurgeOptionsItems.obtainPVPDuringPrep());
		inventory.setItem(13, PurgeOptionsItems.obtainDeathBeforePurge());
		inventory.setItem(22, PurgeOptionsItems.obtainMobCountAsCombat());

		inventory.setItem(6, PurgeOptionsItems.obtainUsingBungee());
		inventory.setItem(15, PurgeOptionsItems.obtainSendOnLeave());
		inventory.setItem(24, PurgeOptionsItems.obtainSendOnDeath());

		inventory.setItem(29, PurgeOptionsItems.obtainRegenOres());
		inventory.setItem(38, PurgeOptionsItems.obtainRegenTrees());

		inventory.setItem(8, PurgeOptionsItems.obtainBlockInProgress());

		inventory.setItem(43, DefaultItems.obtainBack());
		inventory.setItem(44, DefaultItems.obtainExit());

		player.openInventory(inventory);
	}
}