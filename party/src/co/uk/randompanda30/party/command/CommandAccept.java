package co.uk.randompanda30.party.command;

import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.command.handle.Command;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.data.PlayerData;
import co.uk.randompanda30.party.object.Invite;
import co.uk.randompanda30.party.object.PartyOB;
import co.uk.randompanda30.party.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by panda on 25/04/16.
 */
public class CommandAccept extends Command {

    public CommandAccept() {
        super("party accept", "", "Accepts a party invite", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        // args[0] accept

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.PARTY_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!TEMP.invites.containsKey(player.getUniqueId())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_ACCEPT_NOINVITEPENDING.value, player);
            return true;
        }

        if (PlayerData.inParty(uuid)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_ALREADYIN.value, player);
            return true;
        }

        Invite invite = TEMP.invites.get(player.getUniqueId());
        PartyOB party = invite.getParty();
        party.addPlayer(uuid);

        Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_ACCEPT_JOINED.value, player);

        new ArrayList<>(party.getPlayers()).stream().filter(uuids -> Bukkit.getServer().getPlayer(uuids) != null
                && Bukkit.getServer().getPlayer(uuids).isOnline()).forEach(uuids ->
                Dispatch.sendMessage(Messages.MessagesValues.PARTY_ACCEPT_HASJOINED.value.toString()
                        .replace("%player", player.getName()), Bukkit.getServer()
                        .getPlayer(uuids)));
        invite.stop();
        return true;
    }
}