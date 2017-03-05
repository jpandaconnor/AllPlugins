package co.uk.RandomPanda30.MineRP.Handlers;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.MineRP.MineRPData;

public class NameTagHandler {

	@SuppressWarnings("unused")
	public static void setNameTag(Player player) {
		PlayerHandler object = new PlayerHandler(null, null, 0, null);
		for (PlayerHandler ph : MineRPData.players) {
			if (ph.getPlayer().equals(player)) {
				object = ph;
			}
		}
	}
}