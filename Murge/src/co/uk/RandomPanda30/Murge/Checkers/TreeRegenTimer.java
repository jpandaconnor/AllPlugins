package co.uk.RandomPanda30.Murge.Checkers;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Regeneration.TreeCollection;
import co.uk.RandomPanda30.Murge.Handlers.InworldHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class TreeRegenTimer extends BukkitRunnable {

	public TreeRegenTimer (Murge plugin) {
		MurgeData.plugin = plugin;
	}

	@Override
	public void run() {
		TreeCollection.getCollection().checkData();
		for (Player player : InworldHandler.getHandler().getPlayers()) {
			StringMethods.sendMessage(
					(String) Murge.mMap.getStat(MessagesValues.REGEN_MAYLAG),
					player);
		}
	}
}