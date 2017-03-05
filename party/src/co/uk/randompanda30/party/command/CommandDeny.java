package co.uk.randompanda30.party.command;

import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.command.handle.Command;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.object.Invite;
import co.uk.randompanda30.party.object.PartyOB;
import co.uk.randompanda30.party.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by panda on 25/04/16.
 */
public class CommandDeny extends Command {

    public CommandDeny() {
        super("party deny", "", "Denies a party invite", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.PARTY_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!TEMP.invites.containsKey(player.getUniqueId())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_ACCEPT_NOINVITEPENDING.value, player);
            return true;
        }

        Invite invite = TEMP.invites.get(player.getUniqueId());
        PartyOB party = invite.getParty();

        Dispatch.sendMessage(Messages.MessagesValues.PARTY_DENY_DENIED.value.toString(), player);

        new ArrayList<>(party.getPlayers()).stream().filter(uuid -> Bukkit.getServer().getPlayer(uuid) != null &&
                Bukkit.getServer().getPlayer(uuid).isOnline()).forEach(uuid -> {
            Dispatch.sendMessage(Messages.MessagesValues.PARTY_DENY_HASJOINED.value.toString()
                    .replace("%player", player.getName()), Bukkit.getServer().getPlayer(uuid));
        });
        invite.stop();
        return true;
    }
}