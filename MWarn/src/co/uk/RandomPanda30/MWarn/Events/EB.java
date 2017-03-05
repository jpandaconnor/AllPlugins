package co.uk.RandomPanda30.MWarn.Events;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.S;
import co.uk.RandomPanda30.MWarn.Methods.W;
import co.uk.RandomPanda30.MWarn.Methods.WB;
import co.uk.RandomPanda30.MWarn.Methods.Y;

public class EB implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		W.checkBans(player);
		if (W.isBanned(player)) {
			String reason = "";
			String time = "";
			String finalReason = "";
			String finalTime = "";

			B.getKeys();
			Set<String> numeralKeys = null;
			ArrayList<Integer> no = new ArrayList<Integer>();
			if (B.keys.contains(player.getUniqueId().toString())) {
				if (B.dataC.get(player.getUniqueId().toString() + ".bans") != null) {
					numeralKeys = B.dataC.getConfigurationSection(
							player.getUniqueId().toString() + ".bans").getKeys(
							false);
					for (String s : numeralKeys) {
						no.add(Integer.parseInt(s));
					}
				}
			}

			for (int i : no) {
				if (WB.getState(
						player.getUniqueId().toString() + ".bans." + i
								+ ".state").equals(S.ACTIVE)) {
					reason = B.dataC.getString(player.getUniqueId().toString()
							+ ".bans." + i + ".reason");
					time = B.dataC.getString(player.getUniqueId().toString()
							+ ".bans." + i + ".time");
					finalReason = Y.formatMessage((String) B.messagesC
							.get("BAN.REASON") + reason);
				}
			}

			long cTime = Calendar.getInstance().getTimeInMillis();
			long finTime = Long.parseLong(time) - cTime;

			long days = 0;
			long hours = 0;
			long minutes = 0;
			long seconds = 0;

			days = finTime / 1000 / 60 / 60 / 24;
			hours = (finTime / 1000 / 60 / 60) - (days * 24);
			minutes = (finTime / 1000 / 60) - (hours * 60) - (days * 24 * 60);
			seconds = (finTime / 1000) - (minutes * 60) - (hours * 60 * 60)
					- (days * 24 * 60 * 60);

			if (days != 0) {
				finalTime += " %A" + days
						+ (String) B.messagesC.get("BAN.DAYS");
			}

			if (hours != 0) {
				finalTime += " %A" + hours
						+ (String) B.messagesC.get("BAN.HOURS");
			}

			if (minutes != 0) {
				finalTime += " %A" + minutes
						+ (String) B.messagesC.get("BAN.MINUTES");
			}

			if (seconds != 0) {
				finalTime += " %A" + seconds
						+ (String) B.messagesC.get("BAN.SECONDS");
			}

			finalReason += Y.formatMessage("&u"
					+ (String) B.messagesC.get("BAN.LENGTH") + "%A" + finalTime
					+ "%N.");

			event.disallow(Result.KICK_BANNED, finalReason);
		} else {
			event.allow();
			if ((Boolean) B.configC.get("CLEARWARNINGS.ONUNBAN") == true) {
				W.removeAllActiveWarnings(player);
			} else {
				W.checkWarnings(player);
			}
		}
	}
}