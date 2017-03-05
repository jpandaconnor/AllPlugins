package co.uk.randompanda30.party.object;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Created by panda on 23/04/16.
 */
public class Timeout {

    public UUID player;
    public PartyOB party;

    public int id;

    public Timeout(UUID player, PartyOB party) {
        this.player = player;
        this.party = party;
        go();

        TEMP.timesouts.add(this);
    }

    public void go() {
        id = Bukkit.getScheduler().scheduleSyncDelayedTask(Party.getPlugin(), () -> {
            if(TEMP.parties.contains(party)) {
                party.kickPlayer(player);
                if(TEMP.timesouts.contains(this)) {
                    TEMP.timesouts.remove(this);
                }
            }
        }, 6000L);
    }

    public UUID getPlayer() {
        return player;
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(id);
    }
}