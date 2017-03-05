package co.uk.randompanda30.party.command;

import co.uk.randompanda30.party.command.handle.Command;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.data.PlayerData;
import co.uk.randompanda30.party.object.PartyOB;
import co.uk.randompanda30.party.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by panda on 23/04/16.
 */
public class CommandInfo extends Command {

    public CommandInfo() {
        super("party info", "", "Allows you to see information about your party", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        // args[0] = info

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.PARTY_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if(!PlayerData.inParty(uuid)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_NOTINPARTY.value, player);
            return true;
        }

        PartyOB party = PlayerData.getParty(uuid);

        Dispatch.sendMessage("%NParty - ", player);
        Dispatch.sendMessage("%NLeader: " + Bukkit.getPlayer(party.getLeader()).getName(), player);
        Dispatch.sendMessage("%NMembers:", player);

        for(UUID id : party.getPlayers()) {
            Dispatch.sendMessage("  %N- %A" + Bukkit.getPlayer(id).getName(), player);
        }

        return true;
    }
}