package co.uk.RandomPanda30.Murge.Events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.BroadcastCollection;
import co.uk.RandomPanda30.Murge.Collection.MiscCollection;
import co.uk.RandomPanda30.Murge.Collection.ValueCollection;
import co.uk.RandomPanda30.Murge.Collection.Commands.Fixwb;
import co.uk.RandomPanda30.Murge.Collection.Commands.Start;
import co.uk.RandomPanda30.Murge.Collection.Commands.Stop;
import co.uk.RandomPanda30.Murge.Collection.Player.EconCollection;
import co.uk.RandomPanda30.Murge.Collection.Regeneration.OreCollection;
import co.uk.RandomPanda30.Murge.Collection.Special.BungeeCollection;
import co.uk.RandomPanda30.Murge.Collection.Special.MySQLCollection;
import co.uk.RandomPanda30.Murge.Collection.World.LocationCollection;
import co.uk.RandomPanda30.Murge.Collection.World.TimeCollection;
import co.uk.RandomPanda30.Murge.Collection.World.WorldBorderCollection;
import co.uk.RandomPanda30.Murge.Collection.World.WorldCollection;
import co.uk.RandomPanda30.Murge.Displays.MainMenu;
import co.uk.RandomPanda30.Murge.Displays.SettingsMenu;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.BroadcastOptionsMenu;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.CommandsMenu;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.KitsOptionsMenu;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.PurgeOptionsMenu;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.PurgeValuesMenu;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.RegenTimesMenu;
import co.uk.RandomPanda30.Murge.Displays.Subdisplays.WorldBorderMenu;
import co.uk.RandomPanda30.Murge.Executor.DayExecutor;
import co.uk.RandomPanda30.Murge.Executor.PurgeExecutor;
import co.uk.RandomPanda30.Murge.Handlers.EconHandler;
import co.uk.RandomPanda30.Murge.Handlers.InworldHandler;
import co.uk.RandomPanda30.Murge.Handlers.PlayerDataHandler;
import co.uk.RandomPanda30.Murge.Handlers.ScoreboardHandler;
import co.uk.RandomPanda30.Murge.Handlers.SetupHandler;
import co.uk.RandomPanda30.Murge.Handlers.WorldBorderHandler;
import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Items.EditLocationItems;
import co.uk.RandomPanda30.Murge.Items.PurgeButtons;
import co.uk.RandomPanda30.Murge.Items.Inventories.MainMenuItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.SettingsMenuItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.BroadcastOptionsItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.CommandsMenuItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.PurgeOptionsItems;
import co.uk.RandomPanda30.Murge.Items.Inventories.Subinventories.WorldBorderItems;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnInventoryClickEvent implements Listener {

	public WorldBorderCollection wb = new WorldBorderCollection();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		int slot = event.getSlot();

		UUID uuid = player.getUniqueId();

		if (event.getCurrentItem() == null) {
			return;
		}

		if (event.getCurrentItem().getType().equals(Material.AIR)) {
			return;
		}

		for (String s : MurgeData.getInventoryNames()) {
			if (event.getInventory().getName().contains(s)) {
				event.setCancelled(true);
			}
		}

		if (StatsHandler.inSpectators(uuid)) {
			event.setCancelled(true);
		}

		if (item.equals(MainMenuItems.obtainSettings())) {
			SettingsMenu.openSettingsMenu(player);
		}

		if (item.equals(SettingsMenuItems.obtainKits())) {
			KitsOptionsMenu.openKitsMenu(player);
		}

		if (item.equals(DefaultItems.obtainExit())) {
			player.closeInventory();
		}

		if (item.equals(SettingsMenuItems.obtainPurgeValues())) {
			PurgeValuesMenu.openPurgeValuesMenu(player);
		}

		if (item.equals(SettingsMenuItems.obtainCommandsOptions())) {
			CommandsMenu.openCommandsMenu(player);
		}

		if (item.equals(SettingsMenuItems.obtainPurgeOptions())) {
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(SettingsMenuItems.obtainRegen())) {
			RegenTimesMenu.openRegenTimes(player);
		}

		if (item.equals(SettingsMenuItems.obtainBroadcastOptions())) {
			BroadcastOptionsMenu.openBroadcastOptionsMenu(player);
		}

		/*
		 * 
		 * Top is ore; bottom is tree
		 */

		if (item.getItemMeta().hasDisplayName()
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			String oreTime = TimeCollection.getCollection()
					.getOreRegenerationTimer();
			String[] oreSplitter = { oreTime };

			String treeTime = TimeCollection.getCollection()
					.getTreeRegenerationTimer();
			String[] treeSplitter = { treeTime };

			String finalt = "";
			long days = 0;
			long hours = 0;
			long minutes = 0;
			long seconds = 0;

			if (item.getItemMeta().hasDisplayName()
					&& item.getItemMeta().getDisplayName().contains("Day")) {
				if (item.getItemMeta().getDisplayName().contains("-1")) {
					switch (slot) {
					case 0:
						if (oreTime.contains("d")) {
							oreSplitter = oreSplitter[0].split("d");
							days = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("h")) {
							oreSplitter = oreSplitter[0].split("h");
							hours = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("m")) {
							oreSplitter = oreSplitter[0].split("m");
							minutes = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("s")) {
							oreSplitter = oreSplitter[0].split("s");
							seconds = new Long(oreSplitter[0]);
						}

						if ((days - 1) < 0) {
							days = 0;
						} else {
							days = days - 1;
						}

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection().setOreRegenerationTimer(
								finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					case 18:
						if (treeTime.contains("d")) {
							treeSplitter = treeSplitter[0].split("d");
							days = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("h")) {
							treeSplitter = treeSplitter[0].split("h");
							hours = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("m")) {
							treeSplitter = treeSplitter[0].split("m");
							minutes = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("s")) {
							treeSplitter = treeSplitter[0].split("s");
							seconds = new Long(treeSplitter[0]);
						}

						if ((days - 1) < 0) {
							days = 0;
						} else {
							days = days - 1;
						}

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection()
								.setTreeRegenerationTimer(finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					default:
						break;
					}
				} else {
					switch (slot) {
					case 8:
						if (oreTime.contains("d")) {
							oreSplitter = oreSplitter[0].split("d");
							days = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("h")) {
							oreSplitter = oreSplitter[0].split("h");
							hours = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("m")) {
							oreSplitter = oreSplitter[0].split("m");
							minutes = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("s")) {
							oreSplitter = oreSplitter[0].split("s");
							seconds = new Long(oreSplitter[0]);
						}

						days = days + 1;

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection().setOreRegenerationTimer(
								finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					case 26:
						if (treeTime.contains("d")) {
							treeSplitter = treeSplitter[0].split("d");
							days = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("h")) {
							treeSplitter = treeSplitter[0].split("h");
							hours = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("m")) {
							treeSplitter = treeSplitter[0].split("m");
							minutes = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("s")) {
							treeSplitter = treeSplitter[0].split("s");
							seconds = new Long(treeSplitter[0]);
						}

						days = days + 1;

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection()
								.setTreeRegenerationTimer(finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					default:
						break;
					}
				}
			} else if (item.getItemMeta().getDisplayName().contains("Hour")) {
				if (item.getItemMeta().getDisplayName().contains("-1")) {
					switch (slot) {
					case 0:
						if (oreTime.contains("d")) {
							oreSplitter = oreSplitter[0].split("d");
							days = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("h")) {
							oreSplitter = oreSplitter[0].split("h");
							hours = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("m")) {
							oreSplitter = oreSplitter[0].split("m");
							minutes = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("s")) {
							oreSplitter = oreSplitter[0].split("s");
							seconds = new Long(oreSplitter[0]);
						}

						if ((hours - 1) < 0) {
							hours = 0;
						} else {
							hours = hours - 1;
						}

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection().setOreRegenerationTimer(
								finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					case 19:
						if (treeTime.contains("d")) {
							treeSplitter = treeSplitter[0].split("d");
							days = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("h")) {
							treeSplitter = treeSplitter[0].split("h");
							hours = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("m")) {
							treeSplitter = treeSplitter[0].split("m");
							minutes = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("s")) {
							treeSplitter = treeSplitter[0].split("s");
							seconds = new Long(treeSplitter[0]);
						}

						if ((hours - 1) < 0) {
							hours = 0;
						} else {
							hours = hours - 1;
						}

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection()
								.setTreeRegenerationTimer(finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					default:
						break;
					}
				} else {
					switch (slot) {
					case 7:
						if (oreTime.contains("d")) {
							oreSplitter = oreSplitter[0].split("d");
							days = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("h")) {
							oreSplitter = oreSplitter[0].split("h");
							hours = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("m")) {
							oreSplitter = oreSplitter[0].split("m");
							minutes = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("s")) {
							oreSplitter = oreSplitter[0].split("s");
							seconds = new Long(oreSplitter[0]);
						}

						hours = hours + 1;

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection().setOreRegenerationTimer(
								finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					case 25:
						if (treeTime.contains("d")) {
							treeSplitter = treeSplitter[0].split("d");
							days = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("h")) {
							treeSplitter = treeSplitter[0].split("h");
							hours = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("m")) {
							treeSplitter = treeSplitter[0].split("m");
							minutes = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("s")) {
							treeSplitter = treeSplitter[0].split("s");
							seconds = new Long(treeSplitter[0]);
						}

						hours = hours + 1;

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection()
								.setTreeRegenerationTimer(finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					default:
						break;
					}
				}
			} else if (item.getItemMeta().getDisplayName().contains("Minute")) {
				if (item.getItemMeta().getDisplayName().contains("-1")) {
					switch (slot) {
					case 2:
						if (oreTime.contains("d")) {
							oreSplitter = oreSplitter[0].split("d");
							days = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("h")) {
							oreSplitter = oreSplitter[0].split("h");
							hours = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("m")) {
							oreSplitter = oreSplitter[0].split("m");
							minutes = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("s")) {
							oreSplitter = oreSplitter[0].split("s");
							seconds = new Long(oreSplitter[0]);
						}

						if ((minutes - 1) < 0) {
							minutes = 0;
						} else {
							minutes = minutes - 1;
						}

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection().setOreRegenerationTimer(
								finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					case 20:
						if (treeTime.contains("d")) {
							treeSplitter = treeSplitter[0].split("d");
							days = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("h")) {
							treeSplitter = treeSplitter[0].split("h");
							hours = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("m")) {
							treeSplitter = treeSplitter[0].split("m");
							minutes = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("s")) {
							treeSplitter = treeSplitter[0].split("s");
							seconds = new Long(treeSplitter[0]);
						}

						if ((minutes - 1) < 0) {
							minutes = 0;
						} else {
							minutes = minutes - 1;
						}

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection()
								.setTreeRegenerationTimer(finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					default:
						break;
					}
				} else {
					switch (slot) {
					case 6:
						if (oreTime.contains("d")) {
							oreSplitter = oreSplitter[0].split("d");
							days = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("h")) {
							oreSplitter = oreSplitter[0].split("h");
							hours = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("m")) {
							oreSplitter = oreSplitter[0].split("m");
							minutes = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("s")) {
							oreSplitter = oreSplitter[0].split("s");
							seconds = new Long(oreSplitter[0]);
						}

						minutes = minutes + 1;

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection().setOreRegenerationTimer(
								finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					case 24:
						if (treeTime.contains("d")) {
							treeSplitter = treeSplitter[0].split("d");
							days = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("h")) {
							treeSplitter = treeSplitter[0].split("h");
							hours = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("m")) {
							treeSplitter = treeSplitter[0].split("m");
							minutes = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("s")) {
							treeSplitter = treeSplitter[0].split("s");
							seconds = new Long(treeSplitter[0]);
						}

						minutes = minutes + 1;

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection()
								.setTreeRegenerationTimer(finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					default:
						break;
					}
				}
			} else if (item.getItemMeta().hasDisplayName()
					&& item.getItemMeta().getDisplayName().contains("Second")) {
				if (item.getItemMeta().getDisplayName().contains("-1")) {
					switch (slot) {
					case 3:
						if (oreTime.contains("d")) {
							oreSplitter = oreSplitter[0].split("d");
							days = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("h")) {
							oreSplitter = oreSplitter[0].split("h");
							hours = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("m")) {
							oreSplitter = oreSplitter[0].split("m");
							minutes = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("s")) {
							oreSplitter = oreSplitter[0].split("s");
							seconds = new Long(oreSplitter[0]);
						}

						if ((seconds - 1) < 0) {
							seconds = 0;
						} else {
							seconds = seconds - 1;
						}

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection().setOreRegenerationTimer(
								finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					case 21:
						if (treeTime.contains("d")) {
							treeSplitter = treeSplitter[0].split("d");
							days = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("h")) {
							treeSplitter = treeSplitter[0].split("h");
							hours = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("m")) {
							treeSplitter = treeSplitter[0].split("m");
							minutes = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("s")) {
							treeSplitter = treeSplitter[0].split("s");
							seconds = new Long(treeSplitter[0]);
						}

						if ((seconds - 1) < 0) {
							seconds = 0;
						} else {
							seconds = seconds - 1;
						}

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection()
								.setTreeRegenerationTimer(finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					default:
						break;
					}
				} else {
					switch (slot) {
					case 5:
						if (oreTime.contains("d")) {
							oreSplitter = oreSplitter[0].split("d");
							days = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("h")) {
							oreSplitter = oreSplitter[0].split("h");
							hours = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("m")) {
							oreSplitter = oreSplitter[0].split("m");
							minutes = new Long(oreSplitter[0]);
							if (oreSplitter.length == 2) {
								oreSplitter[0] = oreSplitter[1];
							}
						}

						if (oreTime.contains("s")) {
							oreSplitter = oreSplitter[0].split("s");
							seconds = new Long(oreSplitter[0]);
						}

						seconds = seconds + 1;

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection().setOreRegenerationTimer(
								finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					case 23:
						if (treeTime.contains("d")) {
							treeSplitter = treeSplitter[0].split("d");
							days = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("h")) {
							treeSplitter = treeSplitter[0].split("h");
							hours = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("m")) {
							treeSplitter = treeSplitter[0].split("m");
							minutes = new Long(treeSplitter[0]);
							if (treeSplitter.length == 2) {
								treeSplitter[0] = treeSplitter[1];
							}
						}

						if (treeTime.contains("s")) {
							treeSplitter = treeSplitter[0].split("s");
							seconds = new Long(treeSplitter[0]);
						}

						seconds = seconds + 1;

						if (days != 0) {
							finalt += Long.toString(days) + "d";
						}

						if (hours != 0) {
							finalt += Long.toString(hours) + "h";
						}

						if (minutes != 0) {
							finalt += Long.toString(minutes) + "m";
						}

						if (seconds != 0) {
							finalt += Long.toString(seconds) + "s";
						}

						TimeCollection.getCollection()
								.setTreeRegenerationTimer(finalt);
						RegenTimesMenu.openRegenTimes(player);
						break;
					default:
						break;
					}
				}
			}
		}

		if (item.equals(WorldBorderItems.obtainClosing())) {
			if (!WorldBorderCollection.getCollection().closes()) {
				WorldBorderCollection.getCollection().setCloses(true);
			} else {
				WorldBorderCollection.getCollection().setCloses(false);
			}
			WorldBorderMenu.openWorldBorderSettings(player);
		}

		if (item.equals(CommandsMenuItems.obtainStart())) {
			if (!Start.getCollection().isEnabled()) {
				Start.getCollection().setEnabled(true);
			} else {
				Start.getCollection().setEnabled(false);
			}
			CommandsMenu.openCommandsMenu(player);
		}

		if (item.equals(CommandsMenuItems.obtainStop())) {
			if (!Stop.getCollection().isEnabled()) {
				Stop.getCollection().setEnabled(true);
			} else {
				Stop.getCollection().setEnabled(false);
			}
			CommandsMenu.openCommandsMenu(player);
		}

		if (item.equals(CommandsMenuItems.obtainFixwb())) {
			if (!Fixwb.getCollection().isEnabled()) {
				Fixwb.getCollection().setEnabled(true);
			} else {
				Fixwb.getCollection().setEnabled(false);
			}
			CommandsMenu.openCommandsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainUseVault())) {
			if (!EconHandler.getHandler().setupEcon()) {
				StringMethods.sendMessage(
						(String) Murge.mMap.getStat(MessagesValues.NOVAULT),
						player);
			} else {
				if (!EconCollection.getCollection().usingVault()) {
					EconCollection.getCollection().setVault(true);
					PurgeOptionsMenu.openPurgeOptionsMenu(player);
				} else {
					EconCollection.getCollection().setVault(false);
					PurgeOptionsMenu.openPurgeOptionsMenu(player);
				}
			}
			for (Player players : InworldHandler.getHandler().getPlayers()) {
				ScoreboardHandler.getHandler().doScoreboard(players);
			}
		}

		if (item.equals(PurgeButtons.getStart())) {
			if (!MurgeData.isPurge()) {
				PurgeExecutor pe = new PurgeExecutor();
				pe.execute();
			}
		}

		if (item.equals(PurgeButtons.getStop())) {
			if (MurgeData.isPurge()) {
				DayExecutor de = new DayExecutor();
				de.execute();
				Murge.purgeChecker.cancel();
			}
		}

		if (item.equals(PurgeOptionsItems.obtainBlockInProgress())) {
			if (!MiscCollection.getCollection().isJoinBlockInProgress()) {
				MiscCollection.getCollection().setJoinBlockInProgress(true);
			} else {
				MiscCollection.getCollection().setJoinBlockInProgress(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainSendOnLeave())) {
			if (!BungeeCollection.getCollection().isSendOnLeave()) {
				BungeeCollection.getCollection().setSendOnLeave(true);
			} else {
				BungeeCollection.getCollection().setSendOnLeave(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainSendOnDeath())) {
			if (!BungeeCollection.getCollection().isSendOnDeath()) {
				BungeeCollection.getCollection().setSendOnDeath(true);
			} else {
				BungeeCollection.getCollection().setSendOnDeath(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainUsingBungee())) {
			if (!BungeeCollection.getCollection().isEnabled()) {
				BungeeCollection.getCollection().setEnable(true);
			} else {
				BungeeCollection.getCollection().setEnable(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainRegenOres())) {
			if (!OreCollection.getCollection().isEnabled()) {
				OreCollection.getCollection().setEnabled(true);
			} else {
				OreCollection.getCollection().setEnabled(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainRegenTrees())) {
			if (!OreCollection.getCollection().isEnabled()) {
				OreCollection.getCollection().setEnabled(true);
			} else {
				OreCollection.getCollection().setEnabled(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainUseMySQL())) {
			SetupHandler setup = new SetupHandler(player);
			if (!MySQLCollection.getCollection().isMySQL()) {
				MySQLCollection.getCollection().setMySQL(true);
				if (MySQLCollection.getCollection().configured()) {
					Bukkit.broadcastMessage(Boolean.toString(MySQLCollection
							.getCollection().configured()));
					setup.setup();
					player.closeInventory();
				} else {
					Bukkit.broadcastMessage(Boolean.toString(MySQLCollection
							.getCollection().configured()));
					PurgeOptionsMenu.openPurgeOptionsMenu(player);
				}
				Bukkit.broadcastMessage(Boolean.toString(MySQLCollection
						.getCollection().configured()));
			} else {
				MySQLCollection.getCollection().setMySQL(false);
				PurgeOptionsMenu.openPurgeOptionsMenu(player);
			}
		}

		if (item.equals(PurgeOptionsItems.obtainDeathBeforePurge())) {
			if (!MiscCollection.shouldRespawnAsSpectator()) {
				MiscCollection.setShouldRespawnAsSpectator(true);
			} else {
				MiscCollection.setShouldRespawnAsSpectator(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(BroadcastOptionsItems.obtainBroadcastPlayerLeave())) {
			if (!BroadcastCollection.broadcastLoggedOut()) {
				BroadcastCollection.setBroadcastLoggedOut(true);
			} else {
				BroadcastCollection.setBroadcastLoggedOut(false);
			}
			BroadcastOptionsMenu.openBroadcastOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainWeatherEnabled())) {
			if (!WorldCollection.getCollection().isWeatherEnabled()) {
				WorldCollection.getCollection().setWeatherEnabled(true);
			} else {
				WorldCollection.getCollection().setWeatherEnabled(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainPVPDuringPrep())) {
			if (!MiscCollection.isPVPAllowed()) {
				MiscCollection.setIsPVPAllowed(true);
			} else {
				MiscCollection.setIsPVPAllowed(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainBreakingDuringPurge())) {
			if (!MiscCollection.allowBlockBreak()) {
				MiscCollection.setAllowBlockBreak(true);
			} else {
				MiscCollection.setAllowBlockBreak(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainPlacingDuringPurge())) {
			if (!MiscCollection.allowBlockPlace()) {
				MiscCollection.setAllowBlockPlace(true);
			} else {
				MiscCollection.setAllowBlockPlace(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}

		if (item.equals(PurgeOptionsItems.obtainMobCountAsCombat())) {
			if (!MiscCollection.mobDamageCountAsCombat()) {
				MiscCollection.setMobDamageCountAsCombat(true);
			} else {
				MiscCollection.setMobDamageCountAsCombat(false);
			}
			PurgeOptionsMenu.openPurgeOptionsMenu(player);
		}
		// INVENTORY NAMES HERE

		if (item.equals(DefaultItems.obtainBack())) {
			switch (event.getInventory().getName()) {
			case "World border settings":
				SettingsMenu.openSettingsMenu(player);
				break;
			case "Murge control panel":
				if (!player.hasPermission("murge.admin")) {
					MainMenu.openMainMenu(player);
				} else {
					MainMenu.openMainMenuA(player);
				}
				break;
			case "Purge value settings":
				SettingsMenu.openSettingsMenu(player);
				break;
			case "Purge options settings":
				SettingsMenu.openSettingsMenu(player);
				break;
			case "Broadcast options settings":
				SettingsMenu.openSettingsMenu(player);
				break;
			case "Commands options settings":
				SettingsMenu.openSettingsMenu(player);
				break;
			case "Kits menu":
				SettingsMenu.openSettingsMenu(player);
				break;
			case "Regen times":
				SettingsMenu.openSettingsMenu(player);
				break;
			}
		}

		// Clean up anything else;

		if (item.equals(SettingsMenuItems.obtainLocations())) {
			if (MurgeData.getEditingLocation() != null) {
				StringMethods.sendMessage((String) ((String) Murge.mMap
						.getStat(MessagesValues.LOCATIONS_ALREADYEDITING))
						.replace("%player", MurgeData.getEditingLocation()
								.getName().toString()), player);
				player.closeInventory();
				return;
			}
			PlayerDataHandler pdh = new PlayerDataHandler(player.getGameMode(),
					player.getInventory().getContents(), player.getInventory()
							.getArmorContents(), player.getExp(),
					player.getLevel(), player.getHealth(),
					player.getFoodLevel(), player.getActivePotionEffects());
			StatsHandler.addPlayerData(uuid, pdh);
			player.setGameMode(GameMode.CREATIVE);
			for (PotionEffect pe : player.getActivePotionEffects()) {
				player.removePotionEffect(pe.getType());
			}
			player.getInventory().clear();
			player.getInventory().setHelmet(new ItemStack(Material.AIR));
			player.getInventory().setChestplate(new ItemStack(Material.AIR));
			player.getInventory().setLeggings(new ItemStack(Material.AIR));
			player.getInventory().setBoots(new ItemStack(Material.AIR));
			player.setAllowFlight(true);
			player.setFlying(true);
			player.closeInventory();

			if (!LocationCollection.isSpawnSet()) {
				player.getInventory().setItem(0,
						EditLocationItems.obtainSpawnLocation());
			} else {
				player.sendBlockChange(LocationCollection.getSpawn(),
						Material.STAINED_CLAY, (byte) 5);
			}

			if (!LocationCollection.isSpectatorSpawnSet()) {
				player.getInventory().setItem(1,
						EditLocationItems.obtainSpectatorLocation());
			} else {
				player.sendBlockChange(LocationCollection.getSpectatorSpawn(),
						Material.STAINED_CLAY, (byte) 11);
			}

			MurgeData.setEditingLocation(player);

			player.getInventory()
					.setItem(8, EditLocationItems.obtainExitEdit());
			StringMethods.sendMessage((String) Murge.mMap
					.getStat(MessagesValues.LOCATIONS_INEDIT), player);

			if (StatsHandler.inPlayers(uuid)) {
				StatsHandler.removePlayer(uuid);
			}
		}

		// Sub items here
		if (item.equals(SettingsMenuItems.obtainWorldBorder())) {
			WorldBorderMenu.openWorldBorderSettings(player);
		}

		if (item.equals(WorldBorderItems.obtainStatus())) {
			boolean c = wb.isEnabled();
			if (c) {
				wb.setEnabled(false);
				WorldBorderHandler.createWorldBorder(player.getLocation(),
						60000000, 5);
			} else {
				wb.setEnabled(true);
				wb.setLocation(player.getLocation());
				WorldBorderHandler.createWorldBorder(WorldBorderCollection
						.getCollection().getCentre(), WorldBorderCollection
						.getCollection().getSize(), WorldBorderCollection
						.getCollection().getDamage());
			}
			WorldBorderMenu.openWorldBorderSettings(player);
		}

		if (item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.contains("Money on kill")
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			if (item.getItemMeta().getDisplayName().contains("-10")) {
				int s = ValueCollection.getCollection().getValueOnKill();
				int newS = 0;
				if ((s - 10) <= 0) {
					newS = 10;
				} else {
					newS = s - 10;
				}
				ValueCollection.getCollection().setValueOnKill(newS);
			} else if (item.getItemMeta().getDisplayName().contains("-1")) {
				int s = ValueCollection.getCollection().getValueOnKill();
				int newS = 0;
				if ((s - 1) <= 0) {
					newS = 11;
				} else {
					newS = s - 1;
				}
				ValueCollection.getCollection().setValueOnKill(newS);
			} else if (item.getItemMeta().getDisplayName().contains("+10")) {
				ValueCollection.getCollection().setValueOnKill(
						ValueCollection.getCollection().getValueOnKill() + 10);
			} else {
				ValueCollection.getCollection().setValueOnKill(
						ValueCollection.getCollection().getValueOnKill() + 1);
			}
			PurgeValuesMenu.openPurgeValuesMenu(player);
		}

		if (item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName().contains("Size")
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			if (item.getItemMeta().getDisplayName().contains("-10")) {
				double s = wb.getSize();
				double newS = 0;
				if ((s - 10) <= 0) {
					newS = 10.0;
				} else {
					newS = s - 10;
				}
				wb.setSize(newS);
			} else {
				double s = wb.getSize();
				double newS = s + 10;
				wb.setSize(newS);
			}
			WorldBorderHandler.createWorldBorder(WorldBorderCollection
					.getCollection().getCentre(), WorldBorderCollection
					.getCollection().getSize(), WorldBorderCollection
					.getCollection().getDamage());
			WorldBorderMenu.openWorldBorderSettings(player);
		}

		if (item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName().contains("Damage")
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			if (item.getItemMeta().getDisplayName().contains("-1")) {
				int s = wb.getDamage();
				int newS = 0;
				if ((s - 1) <= 0) {
					newS = 1;
				} else {
					newS = s - 1;
				}
				wb.setDamage(newS);
			} else {
				int s = wb.getDamage();
				int newS = s + 1;
				wb.setDamage(newS);
			}
			WorldBorderHandler.createWorldBorder(WorldBorderCollection
					.getCollection().getCentre(), WorldBorderCollection
					.getCollection().getSize(), WorldBorderCollection
					.getCollection().getDamage());
			WorldBorderMenu.openWorldBorderSettings(player);
		}

		if (item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName().contains("Time")
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			if (item.getItemMeta().getDisplayName().contains("-1")) {
				int s = WorldBorderCollection.getCollection().getCloseTime();
				int newS = 0;
				if ((s - 1) <= 0) {
					newS = 1;
				} else {
					newS = s - 1;
				}
				WorldBorderCollection.getCollection().setCloseTime(newS);
			} else {
				int s = WorldBorderCollection.getCollection().getCloseTime();
				int newS = s + 1;
				WorldBorderCollection.getCollection().setCloseTime(newS);
			}
			WorldBorderMenu.openWorldBorderSettings(player);
		}

		if (item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.contains("Time before purge")
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			if (item.getItemMeta().getDisplayName().contains("-1")) {
				int s = TimeCollection.getCollection().getTimeBeforePurge();
				int newS = 0;
				if ((s - 1) <= 0) {
					newS = 1;
				} else {
					newS = s - 1;
				}
				TimeCollection.getCollection().setTimeBeforePurge(newS);
			} else {
				int s = TimeCollection.getCollection().getTimeBeforePurge();
				int newS = s + 1;
				TimeCollection.getCollection().setTimeBeforePurge(newS);
			}
			PurgeValuesMenu.openPurgeValuesMenu(player);
		}

		if (item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.contains("Purge duration")
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			if (item.getItemMeta().getDisplayName().contains("-1")) {
				int s = TimeCollection.getCollection().getPurgeDuration();
				int newS = 0;
				if ((s - 1) <= 0) {
					newS = 1;
				} else {
					newS = s - 1;
				}
				TimeCollection.getCollection().setPurgeDuration(newS);
			} else {
				int s = TimeCollection.getCollection().getPurgeDuration();
				int newS = s + 1;
				TimeCollection.getCollection().setPurgeDuration(newS);
			}
			PurgeValuesMenu.openPurgeValuesMenu(player);
		}

		if (item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName().contains("Blocks")
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			if (item.getItemMeta().getDisplayName().contains("-10")) {
				int s = WorldBorderCollection.getCollection().getCloseLimit();
				int newS = 0;
				if ((s - 10) <= 0) {
					newS = 10;
				} else {
					newS = s - 10;
				}
				WorldBorderCollection.getCollection().setCloseLimit(newS);
			} else {
				int s = WorldBorderCollection.getCollection().getCloseLimit();
				int newS = s + 10;
				WorldBorderCollection.getCollection().setCloseLimit(newS);
			}
			WorldBorderMenu.openWorldBorderSettings(player);
		}

		if (item.getItemMeta().hasDisplayName()
				&& item.getItemMeta().getDisplayName()
						.contains("Combat cooldown")
				&& item.getType().equals(Material.GOLD_NUGGET)) {
			if (item.getItemMeta().getDisplayName().contains("-1")) {
				int s = TimeCollection.getCollection().getFightCooldown();
				int newS = 0;
				if ((s - 1) <= 0) {
					newS = 1;
				} else {
					newS = s - 1;
				}
				TimeCollection.getCollection().setFightCooldown(newS);
			} else {
				int s = TimeCollection.getCollection().getFightCooldown();
				int newS = s + 1;
				TimeCollection.getCollection().setFightCooldown(newS);
			}
			PurgeValuesMenu.openPurgeValuesMenu(player);
		}

		if (event.getInventory().getName().equals("Adjust flight speed")) {
			if (!StatsHandler.inFlySpeed(uuid)) {
				StatsHandler.addFlySpeed(uuid, 1);
			}
			if (item.getItemMeta().getDisplayName().contains("1")) {
				StatsHandler.replaceFlySpeed(uuid, 1);
				player.setFlySpeed(1 / 10.0f);
				StringMethods.sendMessage(((String) Murge.mMap
						.getStat(MessagesValues.SPEED)).replace("%no",
						Integer.toString(1)), player);
			} else if (item.getItemMeta().getDisplayName().contains("2")) {
				StatsHandler.replaceFlySpeed(uuid, 2);
				player.setFlySpeed(2 / 10.0f);
				StringMethods.sendMessage(((String) Murge.mMap
						.getStat(MessagesValues.SPEED)).replace("%no",
						Integer.toString(2)), player);
			} else if (item.getItemMeta().getDisplayName().contains("3")) {
				StatsHandler.replaceFlySpeed(uuid, 3);
				player.setFlySpeed(3 / 10.0f);
				StringMethods.sendMessage(((String) Murge.mMap
						.getStat(MessagesValues.SPEED)).replace("%no",
						Integer.toString(3)), player);
			} else if (item.getItemMeta().getDisplayName().contains("4")) {
				StatsHandler.replaceFlySpeed(uuid, 4);
				player.setFlySpeed(4 / 10.0f);
				StringMethods.sendMessage(((String) Murge.mMap
						.getStat(MessagesValues.SPEED)).replace("%no",
						Integer.toString(4)), player);
			} else {
				StatsHandler.replaceFlySpeed(uuid, 5);
				player.setFlySpeed(5 / 10.0f);
				StringMethods.sendMessage(((String) Murge.mMap
						.getStat(MessagesValues.SPEED)).replace("%no",
						Integer.toString(5)), player);
			}
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
			player.closeInventory();
		}
	}
}