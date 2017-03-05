package co.uk.RandomPanda30.Murge.Methods;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.ConfigValues;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class TimerMethods {

	public final StatsHandler stats = new StatsHandler();

	public static void startTimer(final UUID victim, final UUID attacker) {
		Integer rawTime = (Integer) Murge.cMap
				.getStat(ConfigValues.FIGHTCOOLDOWN);
		Long time = new Long(rawTime);
		MurgeData.getPlugin().getServer().getScheduler()
				.scheduleSyncDelayedTask(MurgeData.plugin, new Runnable() {
					@Override
					public void run() {
						if (!StatsHandler.inCombatLog(victim)) {
							return;
						}
						StatsHandler.removeCombatLog(victim);
						Player v = Bukkit.getPlayer(victim);
						Player a = Bukkit.getPlayer(attacker);
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.OUTCOMBAT), v);
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.OUTCOMBAT), a);
					}
				}, time * 20);
	}
}