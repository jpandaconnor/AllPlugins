package co.uk.RandomPanda30.Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.CMD.CMDac;
import co.uk.RandomPanda30.Events.OnInventoryClickEvent;
import co.uk.RandomPanda30.MiscText.StartText;
import co.uk.RandomPanda30.MiscText.Tag;

import com.earth2me.essentials.Essentials;

public class Main extends JavaPlugin {

	public static Main plugin;

	public static Essentials ess = (Essentials) Bukkit.getServer()
			.getPluginManager().getPlugin("Essentials");

	public final OnInventoryClickEvent ic = new OnInventoryClickEvent(this);

	public void onEnable() {

		Bukkit.getConsoleSender().sendMessage(Tag.tag + StartText.starting);

		if (ess != null) {
			Bukkit.getConsoleSender().sendMessage(Tag.tag + StartText.nullEss);
			return;
		} else {
			Bukkit.getConsoleSender().sendMessage(
					Tag.tag + StartText.loadingEss);
		}

		getServer().getPluginManager().registerEvents(this.ic, this);

		getCommand("ac").setExecutor(new CMDac(this));

	}

	public void onDisable() {

	}

}
