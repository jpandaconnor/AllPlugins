package co.uk.RandomPanda30.ExpIntercept.Boosters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import co.uk.RandomPanda30.ExpIntercept.ExpIntercept;
import co.uk.RandomPanda30.ExpIntercept.ExpInterceptData;
import co.uk.RandomPanda30.ExpIntercept.Methods.StringMethods;

public class Booster {

	public UUID uuid;
	public String name;
	public int time;
	public int multi;
	public long start;

	public int taskid;

	public Booster (UUID uuid, String name, int time, int multi, long start) {
		this.uuid = uuid;
		this.name = name;
		this.time = time;
		this.multi = multi;
		this.start = start;
	}

	private void startTimer() {
		ExpIntercept.current = this;
		try {
			ExpInterceptData.configC.save(ExpInterceptData.config);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ExpIntercept.booster = true;

		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
				"&B&l" + name + "'s Global booster has been activated"));

		taskid = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
				ExpInterceptData.getPlugin(), new Runnable() {
					@Override
					public void run() {
						stop();
					}
				}, 60 * time * 20);
	}

	public void start() {
		if (!ExpIntercept.booster) {
			startTimer();
			ExpIntercept.current = this;
		} else {
			addToQueue();
		}
	}

	public void stop() {
		ExpIntercept.booster = false;
		ExpIntercept.current = null;
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
				"&B&l" + name + "'s Global booster has expired"));

		if (ExpIntercept.list.contains(this)) {
			ExpIntercept.list.remove(this);
		}

		Bukkit.getServer().getScheduler().cancelTask(taskid);

		if (getNext() != null) {
			Booster booster = getNext();
			booster.start();
		} else {
			Bukkit.getServer().getScheduler().cancelTask(taskid);
		}
	}

	public void dump() {
		ExpInterceptData.configC.set("DUMPINFO",
				StringMethods.compileDumpInformation(this,
						Calendar.getInstance().getTimeInMillis()));
		try {
			ExpInterceptData.configC.save(ExpInterceptData.config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addToQueue() {
		ExpIntercept.list.add(this);

		Bukkit.broadcastMessage(
				ChatColor.translateAlternateColorCodes('&', "&B&l" + name
						+ "'s Global booster has been added to the queue"));
	}

	public UUID getUUID() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public int getTime() {
		return time;
	}

	public int getMulti() {
		return multi;
	}

	public long getStart() {
		return start;
	}

	public static boolean isActiveQueue() {
		return !ExpIntercept.list.isEmpty();
	}

	public static boolean isQueue() {
		return ExpInterceptData.configC.contains("BOOSTER.QUEUE");
	}

	public static boolean isDumped() {
		return ExpInterceptData.configC.contains("BOOSTER.DUMPED");
	}

	public static void removeDump() {
		ExpInterceptData.configC.set("DUMPINFO", null);
		try {
			ExpInterceptData.configC.save(ExpInterceptData.config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadQueue() {
		if (isQueue()) {
			List<String> keys = (List<String>) ExpInterceptData.configC
					.get("BOOSTER.QUEUE");
			for (String s : keys) {
				String[] key = StringMethods.decompileBoosterInformation(s);
				ExpIntercept.list.add(new Booster(UUID.fromString(key[0]),
						key[1], Integer.parseInt(key[2]),
						Integer.parseInt(key[3]), Long.parseLong(key[4])));
			}
		}
	}

	public static void unloadQueue() {
		ArrayList<String> m = new ArrayList<String>();
		if (isActiveQueue()) {
			if (!ExpIntercept.list.isEmpty()) {
				for (Booster b : ExpIntercept.list) {
					m.add(StringMethods.compileBoosterInformation(b.getUUID(),
							b.getName(), b.getTime(), b.getMulti(),
							b.getStart()));
				}
				ExpInterceptData.configC.set("BOOSTER.QUEUE", m);
				try {
					ExpInterceptData.configC.save(ExpInterceptData.config);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Booster getNext() {
		return (!ExpIntercept.list.isEmpty() ? ExpIntercept.list.get(0) : null);
	}
}