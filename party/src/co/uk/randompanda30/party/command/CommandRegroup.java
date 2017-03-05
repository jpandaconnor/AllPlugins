package co.uk.randompanda30.party.command;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.command.handle.Command;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.data.PlayerData;
import co.uk.randompanda30.party.object.PartyOB;
import co.uk.randompanda30.party.object.RegroupTimeout;
import co.uk.randompanda30.party.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by panda on 26/04/16.
 */
public class CommandRegroup extends Command {

    public CommandRegroup() {
        super("party regroup", "", "Teleports all party members to the leader", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid =  player.getUniqueId();

        // args[0] = regroup

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.PARTY_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if(!PlayerData.inParty(uuid)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_NOTINPARTY.value, player);
            return true;
        }

        if(TEMP.regroups.containsKey(uuid)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_REGROUP_ALREADYDONE.value, player);
            return true;
        }

        PartyOB party = PlayerData.getParty(uuid);

        if(!uuid.equals(party.getLeader())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_KICK_NOTLEADER.value, player);
            return true;
        }

        new ArrayList<>(party.getPlayersOB()).stream().filter(p -> !p.equals(player)).forEach(p -> {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_REGROUP_SUMMONED.value, p);
            p.teleport(player.getLocation());
        });

        new RegroupTimeout(uuid, Party.getPlugin());
        return true;
    }
}