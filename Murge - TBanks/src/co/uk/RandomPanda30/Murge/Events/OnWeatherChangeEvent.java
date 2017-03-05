package co.uk.RandomPanda30.Murge.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.World.WorldCollection;

public class OnWeatherChangeEvent implements Listener {

	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent event) {
		if (event.getWorld().equals(MurgeData.getWorld())) {
			if (!WorldCollection.getCollection().isWeatherEnabled()) {
				event.setCancelled(true);
			}
		}
	}
}