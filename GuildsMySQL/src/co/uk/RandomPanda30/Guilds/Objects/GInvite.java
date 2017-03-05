package co.uk.RandomPanda30.Guilds.Objects;

import org.bukkit.Bukkit;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Config.Messages.MessagesValues;
import co.uk.RandomPanda30.Guilds.Util.PermUtil;
import co.uk.RandomPanda30.Guilds.Util.Basics.MUtil;

public class GInvite {

	public Guilds plugin;
	public String guildSender;
	public String guildInvitee;

	public GInvite (String guildInvitee, String guildSender, Guilds plugin) {
		this.guildSender = guildSender;
		this.guildInvitee = guildInvitee;
		this.plugin = plugin;
		start();
	}

	@SuppressWarnings("deprecation")
	public void start() {
		String inviteeList = PermUtil.getPlayersList(guildInvitee);
		String senderList = PermUtil.getPlayersList(guildSender);

		String[] il = inviteeList.split("$");
		String[] sl = senderList.split("$");

		for (String s : sl) {
			if (Bukkit.getPlayer(s.split(";")[0]) != null) {
				if (Bukkit.getPlayer(s.split(";")[0]).isOnline()) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_GINVITE_INVITED.value,
							Bukkit.getPlayer(s.split(";")[0]));
				}
			}
		}

		for (String s : il) {
			if (Bukkit.getPlayer(s.split(";")[0]) != null) {
				if (Bukkit.getPlayer(s.split(";")[0]).isOnline()) {
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_GINVITE_YOUINVITED.value,
							Bukkit.getPlayer(s.split(";")[0]));
				}
			}
		}

		Data.ginvites.put(guildInvitee, this);

		final String[] newIl = il;

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				if (Data.ginvites.containsKey(guildInvitee)) {
					Data.ginvites.remove(guildInvitee);
					for (String s : newIl) {
						if (Bukkit.getPlayer(s.split(";")[0]) != null) {
							if (Bukkit.getPlayer(s.split(";")[0]).isOnline()) {
								MUtil.sendMessage(
										(String) MessagesValues.COMMAND_GINVITE_EXPIRED.value,
										Bukkit.getPlayer(s.split(";")[0]));
							}
						}
					}
				}
			}
		}, 60 * 2 * 20);
	}
}