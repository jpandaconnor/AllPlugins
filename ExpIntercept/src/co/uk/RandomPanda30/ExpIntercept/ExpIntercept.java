package co.uk.RandomPanda30.ExpIntercept;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.ExpIntercept.Boosters.Booster;
import co.uk.RandomPanda30.ExpIntercept.Commands.CommandBooster;
import co.uk.RandomPanda30.ExpIntercept.Commands.CommandGxplevel;
import co.uk.RandomPanda30.ExpIntercept.Events.Events;
import co.uk.RandomPanda30.ExpIntercept.Methods.ConfigMethods;
import co.uk.RandomPanda30.ExpIntercept.Methods.StringMethods;

public class ExpIntercept extends JavaPlugin {

	public static ArrayList<Booster> list = new ArrayList<Booster>();
	public static boolean booster = false;

	public static Booster current = null;

	ArrayList<String> sm;
	ArrayList<String> shm;

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		ExpInterceptData.plugin = this;
		ExpInterceptData.initDataFile();

		ConfigMethods.initConfig();
		ConfigMethods.initMessages();

		Events.registerEvents();

		sm = (ArrayList<String>) ExpInterceptData.messagesC.get("SM");
		shm = (ArrayList<String>) ExpInterceptData.messagesC.get("SHM");

		ExpInterceptData.plugin.getCommand("gxplevel")
				.setExecutor(new CommandGxplevel());
		ExpInterceptData.plugin.getCommand("booster")
				.setExecutor(new CommandBooster());

		if (ExpInterceptData.configC.get("DUMPINFO") != null) {
			String[] splits = StringMethods.decompileDumpInformation(
					(String) ExpInterceptData.configC.get("DUMPINFO"));

			long start = Long.parseLong(splits[4]);
			long dump = Long.parseLong(splits[5]); // Gets the already lapsed
													// time
			long finalTime = dump - start;

			int minutes = (int) ((finalTime / (1000 * 60)) % 60);
			minutes = minutes + 1;

			Booster boost = new Booster(UUID.fromString(splits[0]), splits[1],
					minutes, Integer.parseInt(splits[3]),
					Long.parseLong(splits[4]));
			boost.start();

			Booster.removeDump();
		}

		Booster.loadQueue();
		if (Booster.isActiveQueue()) {
			Booster booster = list.get(0);
			booster.start();
		}

		for (String s : sm) {
			StringMethods.sendMessage(s, null);
		}
	}

	@Override
	public void onDisable() {

		if (booster) {
			current.dump();
		}

		Booster.unloadQueue();

		for (String s : shm) {
			StringMethods.sendMessage(s, null);
		}

		ExpInterceptData.plugin = null;
	}
}