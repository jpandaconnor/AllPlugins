package co.uk.randompanda30.party.object;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Created by panda on 26/04/16.
 */
public class RegroupTimeout {

    Party plugin;
    UUID leader;

    int task;

    public RegroupTimeout(UUID leader, Party plugin) {
        this.plugin = plugin;
        this.leader = leader;

        start();
    }

    public void start() {
        TEMP.regroups.put(leader, this);

        task = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (TEMP.regroups.containsKey(leader)) {
                TEMP.regroups.remove(leader);
            }
        }, 18000L);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
        if (TEMP.regroups.containsValue(this)) {
            TEMP.regroups.remove(this);
        }
    }
}