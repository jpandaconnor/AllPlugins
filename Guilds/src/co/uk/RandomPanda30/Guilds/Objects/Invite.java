package co.uk.RandomPanda30.Guilds.Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Guilds.Data;
import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Config.Messages.MessagesValues;
import co.uk.RandomPanda30.Guilds.Util.Basics.MUtil;

public class Invite {

	public Guilds plugin;
	public String guild;
	public Player guildSender;
	public Player guildInvitee;

	public Invite (String invitedGuild, Player sender, Player invitee,
			Guilds plugin) {
		this.guild = invitedGuild;
		this.guildSender = sender;
		this.guildInvitee = invitee;
		this.plugin = plugin;
		start();
	}

	public void start() {
		MUtil.sendMessage(
				(String) MessagesValues.COMMAND_INVITE_INVITED.value.toString()
						.replace("%player", guildInvitee.getName()),
				guildSender);
		MUtil.sendMessage(
				(String) MessagesValues.COMMAND_INVITE_YOUINVITED.value
						.toString().replace("%guild", guild),
				guildInvitee);
		Data.invites.put(guildInvitee, this);
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				if (Data.invites.containsKey(guildInvitee)) {
					Data.invites.remove(guildInvitee);
					MUtil.sendMessage(
							(String) MessagesValues.COMMAND_INVITE_EXPIRED.value,
							guildInvitee);
				}
			}
		}, 60 * 2 * 20);
	}
}