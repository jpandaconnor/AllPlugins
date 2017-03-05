package co.uk.RandomPanda30.KnightCrates;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.KnightCrates.Commands.CommandCrate;
import co.uk.RandomPanda30.KnightCrates.Commands.CommandCrateinv;
import co.uk.RandomPanda30.KnightCrates.Commands.CommandXyfx;
import co.uk.RandomPanda30.KnightCrates.Events.OnBlockBreakEvent;
import co.uk.RandomPanda30.KnightCrates.Events.OnBlockPlaceEvent;
import co.uk.RandomPanda30.KnightCrates.Events.OnInventoryClickEvent;
import co.uk.RandomPanda30.KnightCrates.Events.OnInventoryCloseEvent;
import co.uk.RandomPanda30.KnightCrates.Events.OnPlayerDropItemEvent;
import co.uk.RandomPanda30.KnightCrates.Events.OnPlayerInteractEvent;
import co.uk.RandomPanda30.KnightCrates.Objects.Key;

public class KnightCrates extends JavaPlugin {

	public static KnightCrates plugin;

	public static Key iron;
	public static Key gold;
	public static Key diamond;

	public static String tag = "&F[&dKnightCrates&F] ";

	public static ArrayList<Location> locations = new ArrayList<>();

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		plugin = this;

		new CommandCrate(this);
		new CommandCrateinv(this);
		new CommandXyfx(this);

		new OnBlockPlaceEvent(this);
		new OnBlockBreakEvent(this);
		new OnPlayerDropItemEvent(this);
		new OnPlayerInteractEvent(this);
		new OnInventoryClickEvent(this);
		new OnInventoryCloseEvent(this);

		if (getConfig().contains("Locations")) {
			locations = (ArrayList<Location>) getConfig().getList("Locations");
		}

		iron = new Key((ArrayList<ItemStack>) getConfig().getList("Iron"));
		gold = new Key((ArrayList<ItemStack>) getConfig().getList("Gold"));
		diamond = new Key(
				(ArrayList<ItemStack>) getConfig().getList("Diamond"));

		KnightCrates.sendMessage(tag + "&AIs being enabled", null);
	}

	@Override
	public void onDisable() {

		getConfig().set("Iron", iron.getItems());
		getConfig().set("Gold", gold.getItems());
		getConfig().set("Diamond", diamond.getItems());

		getConfig().set("Locations", locations);
		saveConfig();

		KnightCrates.sendMessage(tag + "&CIs being disabled", null);

		plugin = null;
	}

	public static void sendMessage(String message, Player player) {
		if (player == null) {
			Bukkit.getConsoleSender().sendMessage(format(message));
		} else {
			player.sendMessage(format(message));
		}
	}

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}