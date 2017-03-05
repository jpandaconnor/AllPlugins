package co.uk.RandomPanda30.DailyRewardsPlus.Methods;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.DailyRewardsPlus.B;

public class R {

	public static long getTime(Player player) {
		long time = 0;
		if (B.dataC.contains(player.getUniqueId().toString())) {
			time = Long.parseLong(B.dataC.getString(
					player.getUniqueId().toString() + ".time").replaceAll("",
					""));
		}
		return time;
	}

	public static void resetTime(Player player) {
		B.dataC.set(
				player.getUniqueId().toString() + ".time",
				Long.toString(System.currentTimeMillis()
						+ (24 * 1000 * 60 * 60)));
		try {
			B.dataC.save(B.data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}