package co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Collection.MiscCollection;
import co.uk.RandomPanda30.Murge.Collection.Player.EconCollection;
import co.uk.RandomPanda30.Murge.Collection.Special.BungeeCollection;
import co.uk.RandomPanda30.Murge.Collection.Special.MySQLCollection;
import co.uk.RandomPanda30.Murge.Collection.World.WorldCollection;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class PurgeOptionsItems {

	public static ItemStack obtainWeatherEnabled() {
		boolean c = WorldCollection.getCollection().isWeatherEnabled();
		String title = "";
		if (c) {
			title = "%NWeather: %GOn";
		} else {
			title = "%NWeather: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould weather be allowed on the");
		lores.add("%Tpurge world?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainPVPDuringPrep() {
		boolean c = MiscCollection.isPVPAllowed();
		String title = "";
		if (c) {
			title = "%NPVP before purge: %GOn";
		} else {
			title = "%NPVP before purge: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould PVP be allowed before the");
		lores.add("%Tpurge starts?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainBreakingDuringPurge() {
		boolean c = MiscCollection.allowBlockBreak();
		String title = "";
		if (c) {
			title = "%NBlock break during purge: %GOn";
		} else {
			title = "%NBlock break during purge: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould players be allowed to break");
		lores.add("%Tblocks during the purge?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainPlacingDuringPurge() {
		boolean c = MiscCollection.allowBlockPlace();
		String title = "";
		if (c) {
			title = "%NBlock place during purge: %GOn";
		} else {
			title = "%NBlock place during purge: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould players be allowed to place");
		lores.add("%Tblocks during the purge?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainMobCountAsCombat() {
		boolean c = MiscCollection.mobDamageCountAsCombat();
		String title = "";
		if (c) {
			title = "%NMob damaged counts as combat: %GOn";
		} else {
			title = "%NMob damaged counts as combat: %BOff";
		}

		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould damage from mobs put the player");
		lores.add("%Tinto the combat log?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainUseVault() {
		boolean c = EconCollection.getCollection().usingVault();
		String title = "";
		if (c) {
			title = "%NVault: %GOn";
		} else {
			title = "%NVault: %BOff";
		}
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould the plugin use Vault to handle");
		lores.add("%Tthe economy?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainDeathBeforePurge() {
		boolean c = MiscCollection.shouldRespawnAsSpectator();
		String title = "";
		if (c) {
			title = "%NMove to spectate on death before purge: %GOn";
		} else {
			title = "%NMove to spectate on death before purge: %BOff";
		}

		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould the player be moved into spectate mode");
		lores.add("%Twhen they die before the purge startsa?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainStrikeLightning() {
		boolean c = MiscCollection.isStrikeOnDeath();
		String title = "";
		if (c) {
			title = "%NLightning strike on death: %GOn";
		} else {
			title = "%NLightning strike on death: %BOff";
		}

		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TShould lightning strike when a player dies?");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainUseMySQL() {
		boolean c = MySQLCollection.getCollection().isMySQL();
		String title = "";
		if (c) {
			title = "%NMySQL: %GOn";
		} else {
			title = "%NMySQL: %BOff";
		}

		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TUse MySQL for all stats handling?");
		lores.add(" ");
		lores.add("%EWARNING: %TIf this is the first time MySQL");
		lores.add("%Thas been enabled, you will be asked to insert");
		lores.add("%Tthe MySQL information.");
		lores.add("  ");
		lores.add("%TIf you just want to reconfigure MySQL, then do");
		lores.add("%A/murge mysql %Tto edit the current information");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainUsingBungee() {
		boolean c = BungeeCollection.getCollection().isEnabled();
		String title = "";
		if (c) {
			title = "%NBungee: %GOn";
		} else {
			title = "%NBungee: %BOff";
		}

		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TUse Bungee to teleport players to other servers");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainSendOnLeave() {
		boolean c = BungeeCollection.getCollection().isSendOnLeave();
		String title = "";
		if (c) {
			title = "%NSend on leave: %GOn";
		} else {
			title = "%NSend on leave: %BOff";
		}

		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TSend a player to the lobby server when they");
		lores.add("%Tuse the leave item in spectate mode");
		lores.add(" ");
		lores.add("%TIf this is turned off, player's will be simply");
		lores.add("%Tbe kicked when they use the leave tool");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}

	public static ItemStack obtainSendOnDeath() {
		boolean c = BungeeCollection.getCollection().isSendOnDeath();
		String title = "";
		if (c) {
			title = "%NSend on death: %GOn";
		} else {
			title = "%NSend on death: %BOff";
		}

		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TSend a player to the lobby server when they");
		lores.add("%Tdie.");
		lores.add(" ");
		lores.add("%TIf this is turned off, the player will go into");
		lores.add("%Tspectate mode instead of being sent back to the lobby");
		return ItemMethods
				.createItem(title, Material.STONE_BUTTON, 1, 0, lores);
	}
}