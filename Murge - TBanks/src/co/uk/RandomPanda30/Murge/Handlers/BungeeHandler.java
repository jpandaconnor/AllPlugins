package co.uk.RandomPanda30.Murge.Handlers;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.Special.BungeeCollection;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeHandler {

	private static BungeeHandler handle = new BungeeHandler();
	private BungeeCollection collection = new BungeeCollection();

	public static BungeeHandler getHandle() {
		return handle;
	}

	public void sendToLobby(final Player player, boolean delay) {
		if (delay) {
			MurgeData
					.getPlugin()
					.getServer()
					.getScheduler()
					.scheduleSyncDelayedTask(MurgeData.getPlugin(),
							new Runnable() {
								@Override
								public void run() {
									ByteArrayDataOutput out = ByteStreams
											.newDataOutput();
									out.writeUTF("Connect");
									out.writeUTF(collection.getLobby());
									player.sendPluginMessage(
											MurgeData.getPlugin(),
											"BungeeCord", out.toByteArray());
								}
							}, collection.getSendDelay() * 20);
		} else {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF(collection.getLobby());
			player.sendPluginMessage(MurgeData.getPlugin(), "BungeeCord",
					out.toByteArray());
		}
	}
}