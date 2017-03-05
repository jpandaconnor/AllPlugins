package co.uk.RandomPanda30.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import co.uk.RandomPanda30.Main.Main;

public class OnWeatherChangeEvent implements Listener {

	public OnWeatherChangeEvent (Main plugin) {
		Main.plugin = plugin;
	}

	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent e) {
		if (!e.isCancelled()) {
			if (Main.plugin.getConfig().getBoolean("RainOn") == false) {
				if (e.toWeatherState() && e.getWorld().getName().equals("Hub")) {
					e.setCancelled(true);
				}
			} else {
				e.setCancelled(false);
			}
		}
	}

}
