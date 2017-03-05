package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.TEMP;
import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.invite.Invite;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandDeny extends Command {

    public CommandDeny() {
        super("guild deny", "", "Denies a guild invite", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!TEMP.invites.containsKey(player.getUniqueId())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_ACCEPT_NOINVITEPENDING.value, player);
            return true;
        }

        Invite invite = TEMP.invites.get(player.getUniqueId());
        String guild = invite.getGuild();

        GuildOB guildOB = new GuildOB(guild);

        Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_DENY_YOUHAVEDENIED.value.toString()
                .replace("%guild", guild), player);

        for (String s : new ArrayList<>(guildOB.getMembers())) {
            if (Bukkit.getServer().getPlayer(s) != null && Bukkit.getServer().getPlayer(s).isOnline()) {
                Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_DENY_HASNOTJOINED.value.toString()
                        .replace("%player", player.getName()), Bukkit.getServer().getPlayer(s));
            }
        }
        invite.stop();
        return true;
    }
}