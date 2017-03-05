package co.uk.RandomPanda30.MineRP.Handlers;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import co.uk.RandomPanda30.MineRP.MineRPData;

public class WorldHandler {

	public static void loadWorld() {
		MessageHandler.sendMessage((String) MineRPData.messagesC.get("STARTUP.LOADINGWORLD"), null);
			String world = (String) MineRPData.configC.get("RP.WORLD");
			WorldCreator wc = new WorldCreator(world);
			wc.createWorld();
			MineRPData.world = Bukkit.getServer().getWorld(world);
	}
}