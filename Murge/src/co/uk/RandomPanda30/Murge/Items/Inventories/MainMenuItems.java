package co.uk.RandomPanda30.Murge.Items.Inventories;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Collection.StringCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.DeathsCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.EconCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.KillsCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.SurvivalsCollection;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.ItemsValues;

@SuppressWarnings("unchecked")
public class MainMenuItems {

	public static ItemStack obtainHead(Player player) {
		UUID uuid = player.getUniqueId();
		ArrayList<String> lores = (ArrayList<String>) Murge.iMap
				.getStat(ItemsValues.HEAD_LORES);
		ArrayList<String> newLores = new ArrayList<String>();
		for (String string : lores) {
			string = string.replace(
					"%money",
					StringCollection.getCollection().getMoneyString()
							+ Double.toString(EconCollection.getCollection()
									.getBalance(uuid)));
			string = string.replace(
					"%totalKills",
					Integer.toString(KillsCollection.getCollection().getValue(
							uuid)));
			string = string.replace(
					"%totalDeaths",
					Integer.toString(DeathsCollection.getCollection().getValue(
							uuid)));
			string = string.replace("%purgesSurvived", Integer
					.toString(SurvivalsCollection.getCollection()
							.getValue(uuid)));
			newLores.add(StringMethods.formatMessage(string));
		}
		return ItemMethods.createItem(((String) Murge.iMap
				.getStat(ItemsValues.HEAD_NAME)).replace("%player",
				player.getName()), Material.SKULL_ITEM, 1, 0, newLores);
	}

	public static ItemStack obtainDescription() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.DETAILS_NAME), Material.PAPER, 1, 0,
				(ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.DETAILS_LORES));
	}

	public static ItemStack obtainSettings() {
		return ItemMethods.createItem((String) Murge.iMap
				.getStat(ItemsValues.SETTINGS_CONTROLPANELNAME),
				Material.BREWING_STAND_ITEM, 1, 0,
				(ArrayList<String>) Murge.iMap
						.getStat(ItemsValues.SETTINGS_CONTROLPANELLORES));
	}
}