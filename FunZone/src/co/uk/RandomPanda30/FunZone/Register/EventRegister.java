package co.uk.RandomPanda30.FunZone.Register;

import org.bukkit.Bukkit;

import co.uk.RandomPanda30.FunZone.FunZone;
import co.uk.RandomPanda30.FunZone.Actions.DoubleJump;

public class EventRegister {

	public FunZone plugin;

	public EventRegister (FunZone plugin) {
		this.plugin = plugin;
	}

	/*
	 * Just a general method to register all events here and keep it nice and
	 * clean ;P
	 */

	public void registerEvents() {
		Bukkit.broadcastMessage("test");
		plugin.getServer().getPluginManager()
				.registerEvents(new DoubleJump(plugin), plugin);
	}
}