package co.uk.randompanda30.party.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.data.PlayerData;
import co.uk.randompanda30.party.object.Invite;
import co.uk.randompanda30.party.object.PartyOB;
import co.uk.randompanda30.party.object.Timeout;
import co.uk.randompanda30.party.string.Dispatch;

/**
 * Created by panda on 23/04/16.
 */
public class OnPlayerQuitEvent implements Listener {

    Party plugin;

    public OnPlayerQuitEvent() {
        this.plugin = Party.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if(PlayerData.inParty(uuid)) {
            PartyOB party = PlayerData.getParty(uuid);
            new Timeout(uuid, party);
            
            if(TEMP.invites.containsKey(uuid)) {
            	Invite invite = TEMP.invites.get(uuid);
            	
            	if(invite.getTarget().equals(player)) {
            		invite.stop();
            		Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_INVITE_TARGETLEFT.value, invite.getSender());
            	}
            	
            	if(invite.getSender().equals(player)) {
            		invite.stop();
            		Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_INVITE_SENDERLEFT.value, invite.getTarget());
            	}
            }
        }
    }
}