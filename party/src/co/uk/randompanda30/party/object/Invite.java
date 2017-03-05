package co.uk.randompanda30.party.object;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.config.Messages;
import co.uk.randompanda30.party.string.Dispatch;

/**
 * Created by panda on 24/04/16.
 */
public class Invite implements Listener {

    Party plugin;
    PartyOB party;

    int task;
    private Player target;
    private Player sender;

    public Invite(Player target, Player sender, PartyOB party, Party plugin) {
        this.target = target;
        this.sender = sender;
        this.party = party;
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        start();
    }

    public void start() {
        TEMP.invites.put(target.getUniqueId(), this);
        Dispatch.sendMessage(Messages.MessagesValues.PARTY_INVITE_INVITED.value.toString()
                .replace("%name", Bukkit.getPlayer(party.getLeader()).getName()), target);
        Dispatch.sendMessage(Messages.MessagesValues.PARTY_INVITE_INVITEDPLAYER.value.toString()
                .replace("%player", target.getName()), sender);
        task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (TEMP.invites.containsKey(target.getUniqueId())) {
                TEMP.invites.remove(target.getUniqueId());
            }

            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_INVITE_TIMEDOUT.value, sender);
            Dispatch.sendMessage((String) Messages.MessagesValues.PARTY_INVITE_TIMEDOUT.value, target);
        }, 60 * 2 * 20);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
        if (TEMP.invites.containsValue(this)) {
            TEMP.invites.remove(this);
        }
    }

    public PartyOB getParty() {
        return party;
    }
    
    public Player getTarget() {
    	return target;
    }
    
    public Player getSender() {
    	return sender;
    }
}