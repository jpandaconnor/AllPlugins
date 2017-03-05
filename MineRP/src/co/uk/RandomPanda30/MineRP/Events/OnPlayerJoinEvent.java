package co.uk.RandomPanda30.MineRP.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import co.uk.RandomPanda30.MineRP.Job;
import co.uk.RandomPanda30.MineRP.MineRPData;
import co.uk.RandomPanda30.MineRP.Handlers.MessageHandler;
import co.uk.RandomPanda30.MineRP.Handlers.PlayerHandler;
import co.uk.RandomPanda30.MineRP.Handlers.ScoreboardHandler;
import co.uk.RandomPanda30.MineRP.Methods.DataCollection;

public class OnPlayerJoinEvent implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Location spawnPoint = MineRPData.world.getSpawnLocation();

		if (DataCollection.firstTime(player)) {
			MessageHandler.sendMessage(
					(String) MineRPData.messagesC.get("WELCOME.MESSAGE")
							.toString()
							.replaceAll("'player'", player.getName()), player);
			DataCollection.addNewPlayer(player);
		}

		event.setJoinMessage(MessageHandler
				.formatMessage((String) MineRPData.messagesC
						.get("JOINED.MESSAGE").toString()
						.replaceAll("'player'", player.getName())));

		PlayerHandler ph = new PlayerHandler(player,
				DataCollection.getName(player),
				DataCollection.getMoney(player), Job.CITIZEN);
		MineRPData.players.add(ph);
		// NameTagHandler.setNameTag(player);
		ScoreboardHandler.createScoreboard(player);

		player.teleport(spawnPoint);
	}
}