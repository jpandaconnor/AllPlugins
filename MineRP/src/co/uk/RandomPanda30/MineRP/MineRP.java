package co.uk.RandomPanda30.MineRP;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import co.uk.RandomPanda30.MineRP.Events.Events;
import co.uk.RandomPanda30.MineRP.Handlers.ConfigMethods;
import co.uk.RandomPanda30.MineRP.Handlers.MessageHandler;
import co.uk.RandomPanda30.MineRP.Handlers.PlayerHandler;
import co.uk.RandomPanda30.MineRP.Handlers.WorldHandler;
import co.uk.RandomPanda30.MineRP.Jobs.Citizen;
import co.uk.RandomPanda30.MineRP.Methods.DataCollection;
import co.uk.RandomPanda30.MineRP.Methods.ScoreboardTimer;

public class MineRP extends JavaPlugin {

	BukkitTask checker;

	@Override
	public void onEnable() {
		MineRPData.plugin = this;
		MineRPData.initDataFile();

		ConfigMethods.initConfig();
		ConfigMethods.initMessages();
		ConfigMethods.initData();

		Citizen.initCitizenFile();

		MessageHandler.startupMessages();

		WorldHandler.loadWorld();

		Events.registerEvents();

		checker = new ScoreboardTimer(this).runTaskTimer(this, 0, 10);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			PlayerHandler ph = new PlayerHandler(player,
					DataCollection.getName(player),
					DataCollection.getMoney(player), Job.CITIZEN);
			MineRPData.players.add(ph);
		}
	}

	@Override
	public void onDisable() {
		MessageHandler.shutDownMessages();

		MineRPData.plugin = null;
	}
}