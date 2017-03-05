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
 * Created by panda on 24/04/16.
 */
public class CommandInvite extends Command {

    public CommandInvite() {
        super("party invite <Player name>", "", "Invites a player to your party", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        // args[0] = invite
        // args[1] = name

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.PARTY_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if(!PlayerData.inParty(uuid)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_NOTINPARTY.value, player);
            return true;
        }

        PartyOB party = PlayerData.getParty(uuid);

        if(party.getPlayers().size() == 7) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_INVITE_TOOBIG.value, player);
            return true;
        }

        String target = args[1];
        if (Bukkit.getServer().getPlayer(target) != null) {
            Player ptk = Bukkit.getPlayer(target);
            if(PlayerData.inParty(ptk.getUniqueId())) {
                Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_INVITE_ALREADYIN.value, player);
                return true;
            }
            if(ptk.isOnline()) {
                party.invitePlayer(ptk, player);
            }
        }
        return true;
    }
}