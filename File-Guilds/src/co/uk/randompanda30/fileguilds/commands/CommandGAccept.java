package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.TEMP;
import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.invite.GInvite;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CommandGAccept extends Command {

    public CommandGAccept() {
        super("guild gaccept", "", "Accepts an ally invite from a guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = gaccept

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canAcceptAlly()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        /*
        If target is in the hashmap ->
            Do stuff here
         */

        /*
        Target - this class

        Create guild ob - Target

        Get invite ob - Target + Sender
         */

        if (!TEMP.ginvites.containsKey(guild.getName())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_GACCEPT_NOINVITEPENDING.value, player);
            return true;
        }

        GInvite invite = TEMP.ginvites.get(guild.getName());

        GuildOB senderGuild = invite.getSender();


        // Target adds sender
        guild.allyGuild(guild.getName());

        // Sender adds target
        senderGuild.allyGuild(senderGuild.getName());

        for (String s : new ArrayList<>(guild.getMembers())) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_GACCEPT_JOINED.value.toString()
                        .replace("%guild", senderGuild.getName()), Bukkit.getPlayer(UUID.fromString(s)));
            }
        }

        for (String s : new ArrayList<>(senderGuild.getMembers())) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                Dispatch.sendMessage(Messages.MessagesValues.GUILD_GACCEPT_JOINED.value.toString()
                        .replace("%guild", guild.getName()), Bukkit.getPlayer(UUID.fromString(s)));
            }
        }

        invite.stop();
        return true;
    }
}